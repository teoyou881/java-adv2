package io.text;

import static io.text.TextConst.FILE_NAME;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ReaderWriterMainV2 {

  public static void main(String[] args) throws IOException {

    String writeString = "ABC";
    System.out.println("write String: " + writeString);

    FileOutputStream fos = new FileOutputStream(FILE_NAME);
    OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

    osw.write(writeString);
    osw.close();

    FileInputStream fis = new FileInputStream(FILE_NAME);
    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

    StringBuilder sb = new StringBuilder();

    int ch;
    //return char
    while ((ch = isr.read()) != -1) {
      sb.append((char) ch);
    }
    isr.close();
    System.out.println("read String: " + sb.toString());
  }

}
