package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {

  public static void main(String[] args) throws IOException {
    Path file = Path.of("temp/example.txt");
    Path directory = Path.of("temp/exampleDir");

    // 1. exists(): Check if the file or directory exists
    System.out.println("File exists: " + Files.exists(file));

    // 2. createFile(): Create a new file
    try {
      Files.createFile(file);
      System.out.println("File created");
    } catch (FileAlreadyExistsException e) {
      System.out.println(file + " File already exists");
    }

    // 3. createDirectory(): Create a new directory
    try {
      Files.createDirectory(directory);
      System.out.println("Directory created");
    } catch (FileAlreadyExistsException e) {
      System.out.println(directory + " Directory already exists");
    }

    // 4. delete(): Delete a file or directory
    // Files.delete(file);
    // System.out.println("File deleted");

    // 5. isRegularFile(): Check if it's a regular file
    System.out.println("Is regular file: " + Files.isRegularFile(file));

    // 6. isDirectory(): Check if it's a directory
    System.out.println("Is directory: " + Files.isDirectory(directory));

    // 7. getFileName(): Get the name of the file or directory
    System.out.println("File name: " + file.getFileName());

    // 8. size(): Get the file size in bytes
    System.out.println("File size: " + Files.size(file) + " bytes");

    // 9. move(): Rename or move the file
    Path newFile = Paths.get("temp/newExample.txt");
    Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);
    System.out.println("File moved/renamed");

    // 10. getLastModifiedTime(): Get the last modified time
    System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));

    // Additional: readAttributes(): Read basic file attributes at once
    BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
    System.out.println("===== Attributes =====");
    System.out.println("Creation time: " + attrs.creationTime());
    System.out.println("Is directory: " + attrs.isDirectory());
    System.out.println("Is regular file: " + attrs.isRegularFile());
    System.out.println("Is symbolic link: " + attrs.isSymbolicLink());
    System.out.println("Size: " + attrs.size());
  }
}
