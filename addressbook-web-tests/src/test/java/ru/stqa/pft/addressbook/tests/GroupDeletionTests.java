package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> beforeList = app.group().all();
    GroupData deleteGroup = beforeList.iterator().next();

    app.group().delete(deleteGroup);

    Set<GroupData> afterList = app.group().all();
    beforeList.remove(deleteGroup);

    Assert.assertEquals(beforeList, afterList);
  }



}
