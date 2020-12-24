package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> groups = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCSV(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXML(groups, new File(file));
    } else if (format.equals("json")) {
      saveAsJSON(groups, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for(ContactData contact:contacts) {
      writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 1; i < count + 1; i++) {
      contacts.add(new ContactData()
              .withFirstName(String.format("Ivan %s", i))
              .withLastName(String.format("Oblomov %s", i))
              .withAddress(String.format("123134, Россия, г. Волгоград, ул. Ленина, д.%s", i))
              .withEmail(String.format("boss-%s@addresbook.ru", i))
              .withEmail2(String.format("admin-%s@addresbook.ru", i))
              .withEmail3(String.format("info-%s@addresbook.ru", i))
              .withPhoneHome(String.format("8 (800) 00%s", i))
              .withPhoneMobile(String.format("+7-890-1254%s", i))
              .withPhoneWork(String.format("(900)-980%s", i))
              .withGroup("test 1")
              .withPhotoUrl("src/test/resources/stru.png"));
    }
    return contacts;
  }



}

