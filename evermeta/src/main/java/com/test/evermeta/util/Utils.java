package com.test.evermeta.util;

public class Utils {
  private Utils() {}

  public static String normalize(String keyword) {
    if (keyword == null) return "";
    return keyword.trim().toLowerCase().replaceAll("[^a-z0-9_]", "");
  }
}
