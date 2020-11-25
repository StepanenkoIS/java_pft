package ru.srqa.pft.sandbox.points;

public class MainPoint {
  public static void main(String[] args) {
    Point p1 = new Point(5, 9);
    Point p2 = new Point(15, -9);
    System.out.println("Расстояние между точкой А(" +  p1.x + ";" + p1.y+") и точкой В(" +  p2.x + ";" + p2.y+") равно = " + Point.distance(p1, p2));

    p1 = new Point(7,0);
    p2 = new Point(17,-5);
    System.out.println("Расстояние между точкой А(" +  p1.x + ";" + p1.y+") и точкой В(" +  p2.x + ";" + p2.y+") равно = " + Point.distance(p1, p2));

    p1 = new Point(-3,10);
    p2 = new Point(-1,-2);
    System.out.println("Расстояние между точкой А(" +  p1.x + ";" + p1.y+") и точкой В(" +  p2.x + ";" + p2.y+") равно = " + Point.distance(p1, p2));

  }


}

