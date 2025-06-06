package io.text;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriterMainV4 {

  private static final int BUFFER_SIZE = 8192;

  public static void main(String[] args) throws IOException {
    String writeString = "ABC\n가나다";
    System.out.println("== Write String ==");
    System.out.println(writeString);

    // Write to file
    FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
    BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
    bw.write(writeString);
    bw.close();

    // Read from file
    StringBuilder content = new StringBuilder();
    FileReader fr = new FileReader(FILE_NAME, UTF_8);
    BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);
    String line;
    while ((line = br.readLine()) != null) {
      content.append(line).append("\n");
    }
    br.close();

    System.out.println("== Read String ==");
    System.out.println(content);
  }
}
