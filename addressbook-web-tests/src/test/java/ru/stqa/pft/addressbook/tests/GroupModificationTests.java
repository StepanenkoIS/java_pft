package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();
    int index = beforeList.size()-1;
    GroupData group = new GroupData(beforeList.get(index).getId(), "test1-update", "test2-update", "test3-update");

    app.getGroupHelper().modifyGroup(index, group);

    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(index);
    beforeList.add(group);


    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);

    Assert.assertEquals(beforeList, afterList);

  }

}
