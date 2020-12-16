package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void select() {
    click(By.xpath("(//input[@name='selected[]'])"));
  }

  public void selectById(int id) {
    click(By.id(String.valueOf(id)));
  }

  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void deleteContact(ContactData contact) {
    selectById(contact.getId());
    delete();
    alertDeleteContact();
  }

  public void modify(ContactData contact) {
    initModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void alertDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void initModificationById(int id) {
    click(By.xpath(String.format("//a[@href='edit.php?id=%s']", id)));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void create(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData, b);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    WebElement webElements = wd.findElement(By.tagName("table")).findElement(By.tagName("tbody"));
    List<WebElement> listWE = webElements.findElements(By.cssSelector("tr"));
    List<WebElement> listWE2;

    for (int i = 1; i < listWE.size(); i++) {
      listWE2 = listWE.get(i).findElements(By.cssSelector("td"));
      int id = Integer.parseInt(listWE2.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = listWE2.get(1).getText();
      String firstName = listWE2.get(2).getText();
      String address = listWE2.get(3).getText();
      String[] phones = listWE2.get(5).getText().split("\n");
      ContactData contactData = new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(address)
              .withPhoneHome(phones[0])
              .withPhoneMobile(phones[1])
              .withPhoneWork(phones[2]);
      contacts.add(contactData);
    }
    return contacts;
  }

  public List<ContactData> allList() {
    List<ContactData> listContactData = new ArrayList<>();
    WebElement webElements = wd.findElement(By.tagName("table")).findElement(By.tagName("tbody"));
    List<WebElement> listWE = webElements.findElements(By.cssSelector("tr"));
    List<WebElement> listWE2;

    for (int i = 1; i < listWE.size(); i++) {
      listWE2 = listWE.get(i).findElements(By.cssSelector("td"));
      int id = Integer.parseInt(listWE2.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = listWE2.get(1).getText();
      String firstName = listWE2.get(2).getText();
      String address = listWE2.get(3).getText();
      ContactData contactData = new ContactData()
              .withId(id)
              .withFirstName(lastName)
              .withLastName(firstName)
              .withAddress(address);
      listContactData.add(contactData);
    }
    return listContactData;
  }

  public ContactData infoFromEditFrom(ContactData contact) {
    initModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstName)
            .withLastName(lastName)
            .withPhoneHome(home)
            .withPhoneMobile(mobile)
            .withPhoneWork(work);
  }
}
