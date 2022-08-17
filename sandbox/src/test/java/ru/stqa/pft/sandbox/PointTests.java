package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint() {
    Point point1 = new Point(5,2);
    Point point2 = new Point(10,4);
    Assert.assertEquals(point1.distance(point2),5.385164807134504);
    Assert.assertTrue(point1.distance(point2) > 2);
  }

  @Test
  public void testPoint1() {
    Point point3 = new Point(12,34);
    Point point4 = new Point(-1,4);
    Assert.assertEquals(point3.distance(point4),32.69556544854363);
    Assert.assertFalse(point3.distance(point4) == 0);
  }
}
