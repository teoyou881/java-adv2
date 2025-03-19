package io.file.text;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ReadTextFileV2 {

  public static final String PATH = "temp/hello2.txt";

  public static void main(String[] args) throws IOException {
    String writeString = "abc\n가나다";
    System.out.println("== Write String ==");
    System.out.println(writeString);

    Path path = Path.of(PATH);

    // Write to file
    Files.writeString(path, writeString, UTF_8);

    // Read from file
    // If the file size is too big
    // outOfMemoryException can occur.
    System.out.println("== Read String ==");
    List<String> lines = Files.readAllLines(path, UTF_8);
    for (int i = 0; i < lines.size(); i++) {
      System.out.println((i + 1) + ": " + lines.get(i));
    }

    // if file size is too big
    try (Stream<String> lineStream = Files.lines(path, UTF_8)) {
      lineStream.forEach(System.out::println);
    }

  }
}