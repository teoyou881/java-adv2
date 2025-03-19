package io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class NewFilesPath {

  public static void main(String[] args) throws IOException {
    Path path = Path.of("temp/..");

    System.out.println("Path = " + path);
    // Absolute path
    System.out.println("Absolute path = " + path.toAbsolutePath());
    // Canonical path (resolves ".." and symbolic links)
    System.out.println("Canonical path = " + path.toRealPath());

    // List files and directories
    try (Stream<Path> pathStream = Files.list(path)) {
      List<Path> list = pathStream.toList();
      for (Path p : list) {
        System.out.println((Files.isRegularFile(p) ? "F" : "D") + " | " + p.getFileName());
      }
    } catch (IOException e) {
      System.out.println("Error reading directory: " + e.getMessage());
    }
  }
}
