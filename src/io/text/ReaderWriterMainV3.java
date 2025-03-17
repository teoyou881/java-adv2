package io.text;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriterMainV3 {

  public static void main(String[] args) throws IOException {
    String writeString = "ABC";
    System.out.println("write String: " + writeString);

    // Write to file
    FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
    fw.write(writeString);
    fw.close();

    // Read from file
    StringBuilder content = new StringBuilder();
    FileReader fr = new FileReader(FILE_NAME, UTF_8);
    int ch;
    while ((ch = fr.read()) != -1) {
      content.append((char) ch);
    }
    fr.close();

    System.out.println("read String: " + content);
  }
}
