package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }

    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(beforeList.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1-update", "test2-update", "test3-update"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after, before);
    Assert.assertEquals(afterList.size(), beforeList.size());

  }

}
