package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {


  private int id;
  private String firstName;
  private String lastName;
  private String address;
  private String group;

  public ContactData(String firstName, String lastName, String address, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.group = group;
  }

  public ContactData(int id, String firstName, String lastName, String address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
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

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData data = (ContactData) o;
    return id == data.id &&
            Objects.equals(firstName, data.firstName) &&
            Objects.equals(lastName, data.lastName) &&
            Objects.equals(address, data.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address);
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
