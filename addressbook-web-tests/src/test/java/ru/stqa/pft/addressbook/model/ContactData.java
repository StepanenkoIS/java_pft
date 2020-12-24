package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("ContactData")
public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @Expose
  private String address;
  private String allPhones;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  @Expose
  private String group;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  private String allEmails;
  private File photo;
  @Expose
  private String photoUrl;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }
  public ContactData withPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
    return this;
  }

  public String getEmail() {
    return email;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getAllEmails() {
    return allEmails;
  }

  public String getPhoneHome() {
    return homePhone;
  }
  public String getPhoneMobile() {
    return mobilePhone;
  }
  public String getPhoneWork() {
    return workPhone;
  }
  public String getAllPhones() {
    return allPhones;
  }

  public String getGroup() {
    return group;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getAddress() {
    return address;
  }
  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withPhoneMobile(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }
  public ContactData withPhoneHome(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public ContactData withPhoneWork(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withAllPhoneHome(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData data = (ContactData) o;
    return id == data.id &&
            Objects.equals(firstName, data.firstName) &&
            Objects.equals(lastName, data.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", group='" + group + '\'' +
            '}';
  }


}
