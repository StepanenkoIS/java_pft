package ru.srqa.pft.sandbox;

public class Equality {
  public static void main(String[] args) {
    String s1 = "firefox 2";
    String s2 = "firefox " + 2;

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
    System.out.println();

    String s3 = new String(s1);

    System.out.println(s1 == s3);
    System.out.println(s1.equals(s3));

  }
}
