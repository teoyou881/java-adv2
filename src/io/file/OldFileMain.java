package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileMain {

  public static void main(String[] args) throws IOException {
    File file = new File("temp/example.txt");
    File directory = new File("temp/exampleDir");

    // 1. exists(): Check if the file or directory exists
    System.out.println("File exists: " + file.exists());

    // 2. createNewFile(): Create a new file
    boolean created = file.createNewFile();
    System.out.println("File created: " + created);

    // 3. mkdir(): Create a new directory
    boolean dirCreated = directory.mkdir();
    System.out.println("Directory created: " + dirCreated);

    // 4. delete(): Delete a file or directory
    // boolean deleted = file.delete();
    // System.out.println("File deleted: " + deleted);

    // 5. isFile(): Check if it's a file
    System.out.println("Is file: " + file.isFile());

    // 6. isDirectory(): Check if it's a directory
    System.out.println("Is directory: " + directory.isDirectory());

    // 7. getName(): Get the name of the file or directory
    System.out.println("File name: " + file.getName());

    // 8. length(): Get the file size in bytes
    System.out.println("File size: " + file.length() + " bytes");

    // 9. renameTo(File dest): Rename or move the file
    File newFile = new File("temp/newExample.txt");
    boolean renamed = file.renameTo(newFile);
    System.out.println("File renamed: " + renamed);

    // 10. lastModified(): Get the last modified time
    long lastModified = newFile.lastModified();
    System.out.println("Last modified: " + new Date(lastModified));
  }
}
