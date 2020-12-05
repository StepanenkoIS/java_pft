package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion() throws Exception {

    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Oblomov", "Saratov", "test1"), true);
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deletContact();
    app.getContactHelper().alertDeleteContact();
    app.getNavigationHelper().gotoHomePage();

  }


}
