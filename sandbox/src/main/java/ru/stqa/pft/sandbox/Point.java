package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static double distance(Point p1, Point p2) {
    double dist = Math.sqrt((Math.pow((p2.x - p1.x), 2)) + Math.pow((p2.y - p1.y), 2));
    return dist;
  }

  public static void main(String[] args) {
    Point point1 = new Point(5,2);
    Point point2 = new Point(10,4);
    System.out.println(Point.distance(point1,point2));
    Point point3 = new Point(12,34);
    Point point4 = new Point(-1,4);
    System.out.println(Point.distance(point3,point4));
  }

}