package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test(enabled = true)
  public void testContactCreation() throws Exception {
      app.goTo().gotoHomePage();
      Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData()
              .withFirstName("Ivan")
              .withLastName("Oblomov")
              .withAddress("Saratov")
              .withGroup("test1")
              .withPhoto(photo);
      app.contact().create(contact, true);
      Contacts after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);

      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


    public void testCurrentDir() {
      File currentDir = new File(".");
      System.out.println(currentDir.getAbsolutePath());
      File photo = new File("src/test/resources/stru.png");
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());
    }
}

