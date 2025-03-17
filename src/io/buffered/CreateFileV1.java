package io.buffered;

import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFileV1 {

  public static void main(String[] args) throws IOException {

    FileOutputStream fos = new FileOutputStream(FILE_NAME);
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < FILE_SIZE; i++) {
      fos.write(1);
    }
    fos.close();

    long endTime = System.currentTimeMillis();
    System.out.println("file created: " + FILE_NAME);
    System.out.println("file size: " + FILE_SIZE / 1024 / 1024 + "MB");
    System.out.println("time taken: " + (endTime - startTime) + "ms");
  }

}
