package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectTimeoutMain1 {

  // Windows: about 21 seconds
  // Linux: between 75 and 180 seconds, macOS test: 75 seconds
  // java.net.ConnectException: Operation timed out

  public static void main(String[] args) throws IOException {
    long start = System.currentTimeMillis();

    try {
      Socket socket = new Socket("192.168.1.250", 45678);
    } catch (ConnectException e) {
      // ConnectException: Operation timed out
      e.printStackTrace();
    }

    long end = System.currentTimeMillis();
    System.out.println("end = " + (end - start));
  }
}
