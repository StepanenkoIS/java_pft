package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();


    GroupData group = new GroupData("test1", "test2", "test3");
    app.getGroupHelper().createGroup(group);

    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after, before + 1);
    Assert.assertEquals(afterList.size(), beforeList.size() + 1);

    int max = 0;
    for (GroupData g: afterList) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    beforeList.add(group);
    Assert.assertEquals(new HashSet<Object>(afterList), new HashSet<Object>(beforeList));
  }

}
