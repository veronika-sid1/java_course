package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint() {
    Point point1 = new Point(5,2);
    Point point2 = new Point(10,4);
    Point point3 = new Point(12,34);
    Point point4 = new Point(-1,4);
    Assert.assertEquals(Point.distance(point1,point2),5.385164807134504);
    Assert.assertEquals(Point.distance(point3,point4),32.69556544854363);
    Assert.assertNotSame(Point.distance(point1,point2), Point.distance(point3,point4));
    Assert.assertTrue(Point.distance(point1,point2) > 2);
    Assert.assertFalse(Point.distance(point3,point4) == 0);
  }
}
