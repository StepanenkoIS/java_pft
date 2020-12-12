package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> beforeList = app.group().list();
    int index = beforeList.size()-1;
    GroupData group = new GroupData()
            .withId(beforeList.get(index).getId())
            .withName("test1-update")
            .withHeader("test2-update")
            .withFooter("test3-update");

    app.group().modify(index, group);

    List<GroupData> afterList = app.group().list();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(index);
    beforeList.add(group);


    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);

    Assert.assertEquals(beforeList, afterList);

  }

}
