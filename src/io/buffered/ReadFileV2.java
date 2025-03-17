package io.buffered;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFileV2 {

  public static void main(String[] args) throws IOException {

    FileInputStream fis = new FileInputStream(FILE_NAME);
    long startTime = System.currentTimeMillis();

    byte[] buffer = new byte[BUFFER_SIZE];
    int fileSize = 0;
    int size;
    while ((size = fis.read(buffer)) != -1) {
      fileSize += size;
    }
    fis.close();

    long endTime = System.currentTimeMillis();
    System.out.println("file created: " + FILE_NAME);
    System.out.println("file size: " + FILE_SIZE / 1024 / 1024 + "MB");
    System.out.println("time taken: " + (endTime - startTime) + "ms");

  }

}
