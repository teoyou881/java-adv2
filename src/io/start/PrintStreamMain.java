package io.start;

import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamMain {

  public static void main(String[] args) throws IOException {
    PrintStream out = System.out;

    byte[] bytes = "Hi!\n".getBytes();
    out.write(bytes);
    out.println("PrintStreamMain");
    out.close();

  }

}

