package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

  public void selectContact() {
    click(By.xpath("(//input[@name='selected[]'])"));
  }

  public void selectContact(int id) {
    click(By.id(String.valueOf(id)));
  }

  public void deletContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void createContact(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData,b);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }


  public List<ContactData> getContactList() {
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
      ContactData contactData = new ContactData(id, firstName, lastName, address);
      listContactData.add(contactData);
    }
    return listContactData;
  }
}
