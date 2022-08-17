package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2) {
    double dist = Math.sqrt((Math.pow((p2.x - this.x), 2)) + Math.pow((p2.y - this.y), 2));
    return dist;
  }

  public static void main(String[] args) {
    Point point1 = new Point(5,2);
    Point point2 = new Point(10,4);
    System.out.println("Расстояние между точками (" + point1.x + "," + point1.y + ") и (" + point2.x + "," + point2.y + ") = " + point1.distance(point2));
    Point point3 = new Point(12,34);
    Point point4 = new Point(-1,4);
    System.out.println("Расстояние между точками (" + point3.x + "," + point3.y + ") и (" + point4.x + "," + point4.y + ") = " + point3.distance(point4));
  }

}