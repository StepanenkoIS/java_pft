package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test (enabled = false)
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoHomePage();

    List<ContactData> beforeList = app.getContactHelper().getContactList();
    ContactData contactData = new ContactData("Ivan", "Oblomov", "Saratov", "test1");
    app.getContactHelper().createContact(contactData, true);
    List<ContactData> afterList = app.getContactHelper().getContactList();
    Assert.assertEquals(afterList.size(), beforeList.size()+1);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    beforeList.add(afterList.get(afterList.size()-1));

    Assert.assertEquals(beforeList, afterList);
  }
}

