
package ru.srqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTest {
  @Test
  public void testArea() {
    Square square = new Square(5);
    assert square.area() == 25;

    Assert.assertEquals(square.area(),25.0);
  }
}
