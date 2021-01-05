package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
      groups = app.db().groups();
    }

    app.goTo().gotoHomePage();

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Ivan")
              .withLastName("Oblomov")
              .withAddress("Saratov")
              .inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactModification() {
      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData()
              .withId(modifiedContact.getId())
              .withFirstName("Grigory")
              .withLastName("Pechorin")
              .withAddress("SPB")
              .withPhoneHome("8 (800) 00")
              .withPhoneMobile("+7-897-1254")
              .withPhoneWork("(900)-98")
              .withAddress("123134, Россия, г. Волгоград, ул. Ленина, д.5")
              .withEmail("boss@addresbook.ru")
              .withEmail2("admin@addresbook.ru")
              .withEmail3("info@addresbook.ru");
      app.contact().modify(contact);
      Contacts after = app.db().contacts();
      Assert.assertEquals(after.size(), before.size());
      before = before.withOut(modifiedContact);
      before = before.withAdded(contact);

      MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
  }
}
