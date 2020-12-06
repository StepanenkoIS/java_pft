package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }

    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(beforeList.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after, before - 1);
    Assert.assertEquals(afterList.size(), beforeList.size() - 1);

    beforeList.remove(beforeList.size()-1);

    for (int i = 0; i < afterList.size(); i++) {
      Assert.assertEquals(afterList, beforeList);
    }
  }

}
