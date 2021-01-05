package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactAddGroupTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

    app.goTo().gotoHomePage();
    app.contact().selectGroupNone();
    if (app.contact().checkNumberOfResults() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Ivan")
              .withLastName("Oblomov")
              .withAddress("Saratov"), true);
      app.contact().selectGroupNone();
    }
  }

  @Test
  public void testContactAddGroup() {
    Contacts before = app.contact().all();
    ContactData addContact = before.iterator().next();
    GroupData group = app.db().groups().iterator().next();
    app.contact().contactAddInGroup(addContact, group);
    app.goTo().gotoHomePage();
    app.contact().selectGroupNone();
    Assert.assertEquals(app.contact().checkNumberOfResults(), before.size() - 1);

    Contacts checkContact = app.db().contacts();
    Assert.assertEquals(checkContact
            .stream().filter(c -> c.getId() == addContact.getId())
            .findFirst()
            .get()
            .getGroups().iterator().next().getName(), group.getName());

    app.contact().selectGroupAll();
  }
}
