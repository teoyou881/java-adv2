package io.start;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayStreamMain {

  public static void main(String[] args) throws IOException {
    byte[] input = {1, 2, 3};

    // Writing to memory
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(input);

    // Reading from memory
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    byte[] bytes = bais.readAllBytes();

    System.out.println(Arrays.toString(bytes));
  }
}
