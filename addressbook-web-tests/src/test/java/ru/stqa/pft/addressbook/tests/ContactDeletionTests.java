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


public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deleteContact = before.iterator().next();
    app.contact().deleteContact(deleteContact);
    app.goTo().gotoHomePage();
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(deleteContact);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deleteContact)));
  }
}
