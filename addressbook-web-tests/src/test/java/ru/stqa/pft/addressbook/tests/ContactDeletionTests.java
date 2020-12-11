package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {


  @Test (enabled = false)
  public void testContactDeletion() throws Exception {

    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Oblomov", "Saratov", "test1"), true);
    }
    List<ContactData> beforeList = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(beforeList.get(0).getId());
    app.getContactHelper().deletContact();
    app.getContactHelper().alertDeleteContact();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> afterList = app.getContactHelper().getContactList();
    Assert.assertEquals(afterList.size(), beforeList.size()-1);

    beforeList.remove(0);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);

    Assert.assertEquals(beforeList, afterList);
  }


}
