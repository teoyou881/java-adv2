package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {

  public static void main(String[] args) throws IOException {
    File file = new File("temp/..");

    System.out.println("Path = " + file.getPath());
    // Absolute path
    System.out.println("Absolute path = " + file.getAbsolutePath());
    // Canonical path (resolves ".." and symbolic links)
    System.out.println("Canonical path = " + file.getCanonicalPath());

    // List files and directories
    File[] files = file.listFiles();
    if (files != null) {
      for (File f : files) {
        System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
      }
    } else {
      System.out.println("No files found or directory does not exist.");
    }
  }
}
