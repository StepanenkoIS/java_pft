package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

    app.goTo().gotoHomePage();
    Contacts contacts = app.db().contacts();
    if (contacts.size() == 0 || (contacts.stream().filter(c -> c.getGroups().size() != 0).count() == 0)){
      app.contact().create(new ContactData()
              .withFirstName("Ivan")
              .withLastName("Oblomov")
              .withAddress("Saratov")
              .inGroup(app.db().groups().iterator().next()), true);
      app.goTo().gotoHomePage();
    }

  }

  @Test
  public void testContactDeletionFromGroup() {
    Contacts before = app.db().contacts();
    ContactData contact = before.stream().filter(c -> c.getGroups().size() != 0).findFirst().get();
    app.contact().selectFromGroup(contact);
    Contacts beforeResult = app.contact().all();
    app.contact().contactDeletionFromGroup(contact);
    app.goTo().gotoHomePage();
    Assert.assertEquals(app.contact().checkNumberOfResults(), beforeResult.size() - 1);

    Contacts after = app.db().contacts();
    Assert.assertEquals(after
            .stream()
            .filter(c -> c.getId() == contact.getId())
            .iterator()
            .next()
            .getGroups().size(), 0);

    app.contact().selectGroupAll();
  }
}
