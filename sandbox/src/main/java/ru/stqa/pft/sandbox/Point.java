package ru.stqa.pft.sandbox;

public class Point {

  public double x1;
  public double y1;
  public double x2;
  public double y2;

  public static double distance(Point p1, Point p2) {
    double dist = Math.sqrt((Math.pow((p2.x2 - p1.x1), 2)) + Math.pow((p2.y2 - p1.y1), 2));
    return dist;
  }

  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x1 = 5;
    p1.y1 = 2;
    p2.x2 = 10;
    p2.y2 = 4;
    System.out.println("Расстояние между точками (" + p1.x1 + "," + p1.y1 + ") и (" + p2.x2 + "," + p2.y2 + ") = " + distance(p1, p2));
  }

}
