package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Ivan")
              .withLastName("Oblomov")
              .withAddress("Saratov")
              .withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
      Contacts before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData()
              .withId(modifiedContact.getId())
              .withFirstName("Grigory")
              .withLastName("Pechorin")
              .withAddress("SPB");
      app.contact().modify(contact);
      Contacts after = app.contact().all();
      Assert.assertEquals(after.size(), before.size());
      before = before.withOut(modifiedContact);
      before = before.withAdded(contact);

      MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
  }
}
