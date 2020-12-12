package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() throws Exception {
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().deleteContact(deleteContact);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(deleteContact);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deleteContact)));
  }
}
