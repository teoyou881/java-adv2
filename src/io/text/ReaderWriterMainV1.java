package io.text;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ReaderWriterMainV1 {

  public static void main(String[] args) throws IOException {
    String writeString = "ABC";
    // Convert characters to bytes using UTF-8 encoding
    byte[] writeBytes = writeString.getBytes(UTF_8);
    System.out.println("write String: " + writeString);
    System.out.println("write bytes: " + Arrays.toString(writeBytes));

    // Write to file
    FileOutputStream fos = new FileOutputStream(FILE_NAME);
    fos.write(writeBytes);
    fos.close();

    // Read from file
    FileInputStream fis = new FileInputStream(FILE_NAME);
    byte[] readBytes = fis.readAllBytes();
    fis.close();

    // Convert bytes to string using UTF-8 decoding
    String readString = new String(readBytes, UTF_8);
    System.out.println("read bytes: " + Arrays.toString(writeBytes));
    System.out.println("read String: " + readString);
  }
}
