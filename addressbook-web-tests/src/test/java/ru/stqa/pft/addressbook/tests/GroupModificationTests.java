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
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> beforeList = app.group().all();
    GroupData modifiedGroup = beforeList.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test1-update")
            .withHeader("test2-update")
            .withFooter("test3-update");

    app.group().modify(modifiedGroup);

    Set<GroupData> afterList = app.group().all();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(modifiedGroup);
    beforeList.add(group);


    Assert.assertEquals(beforeList, afterList);

  }

}
