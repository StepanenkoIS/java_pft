package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  public static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method method, Object [] p) {

    logger.info("Start test -> " + method.getName() + ", with parameters -> " + Arrays.asList(p));

  }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method method) {
    logger.info("Stop test -> " + method.getName());

  }

  public void verifyGroupListInUI() {
    Groups dbGroups = app.db().groups();
    Groups uiGroups = app.group().all();
    MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups
            .stream().map((g) -> new GroupData()
            .withId(g.getId())
            .withName(g.getName())).collect(Collectors.toSet())));


  }

}