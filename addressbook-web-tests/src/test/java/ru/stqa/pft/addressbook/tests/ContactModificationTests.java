package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Oblomov", "Saratov", "test1"), true);
    }

    List<ContactData> beforeList = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(beforeList.get(0).getId());
    app.getContactHelper().initContactModification();
    ContactData contactData = new ContactData(beforeList.get(0).getId(), "Grigory", "Pechorin", "SPB");
    app.getContactHelper().fillContactForm(contactData, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

    List<ContactData> afterList = app.getContactHelper().getContactList();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(0);
    beforeList.add(contactData);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);

    Assert.assertEquals(beforeList, afterList);
  }
}
