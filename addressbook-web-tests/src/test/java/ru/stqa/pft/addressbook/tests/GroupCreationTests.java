package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();

    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));

    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after, before + 1);
    Assert.assertEquals(afterList.size(), beforeList.size() + 1);
  }

}
