package ru.srqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.srqa.pft.sandbox.points.Point;

import static ru.srqa.pft.sandbox.points.Point.distance;

public class PointTest {
@Test
  public void distanceTest() {

  Point p1 = new Point(0, 10);
  Point p2 = new Point(0, 0);
  Assert.assertEquals(distance(p1,p2),10.0);

  p1 = new Point(5, 10);
  p2 = new Point(5, -5);
  Assert.assertEquals(distance(p1,p2),15.0);

  p1 = new Point(5, 9);
  p2 = new Point(15, -9);
  Assert.assertEquals(distance(p1,p2),20.591260281974);

  }
}

