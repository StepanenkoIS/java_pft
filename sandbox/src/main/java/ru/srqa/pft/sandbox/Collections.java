package ru.srqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String [] langs = {"Java", "PHP", "C", "Python"};

    for (String s:langs) {
      System.out.println("Язык -> " + s);
    }

    List<String> list = new ArrayList<>();
    list.add("Java");
    list.add("PHP");
    list.add("C");
    list.add("Python");

    for (String s:list) {
      System.out.println("Язык -> " + s);
    }

    List<String> list1 = Arrays.asList(langs);

    for (int i = 0; i < list1.size(); i++) {
      System.out.println("Язык -> " + list1.get(i));
    }


  }
}
