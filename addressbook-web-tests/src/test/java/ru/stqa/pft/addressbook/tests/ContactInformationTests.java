package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Ivan")
              .withLastName("Oblomov")
              .withAddress("Saratov")
              .withGroup("test1")
              .withPhoneHome("8 (800) 00")
              .withPhoneMobile("+7-897-1254")
              .withPhoneWork("(900)-98")
              .withAddress("123134, Россия, г. Волгоград, ул. Ленина, д.5")
              .withEmail("boss@addresbook.ru")
              .withEmail2("admin@addresbook.ru")
              .withEmail3("info@addresbook.ru"), true);
    }
  }
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditFrom)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditFrom.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditFrom)));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactInformationTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails (ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
