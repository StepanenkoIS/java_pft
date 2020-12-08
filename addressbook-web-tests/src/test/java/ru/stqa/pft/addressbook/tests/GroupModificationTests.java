package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification() {


    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }

    List<GroupData> beforeList = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(beforeList.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(beforeList.get(beforeList.size()-1).getId(), "test1-update", "test2-update", "test3-update");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(beforeList.size()-1);
    beforeList.add(group);


    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);

    Assert.assertEquals(beforeList, afterList);

  }

}
