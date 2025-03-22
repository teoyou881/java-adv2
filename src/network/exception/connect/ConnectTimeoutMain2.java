package network.exception.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMain2 {

  // Windows: about 21 seconds
  // Linux: between 75 and 180 seconds, macOS test: 75 seconds
  // java.net.ConnectException: Operation timed out

  public static void main(String[] args) throws IOException {
    long start = System.currentTimeMillis();

    try {
      Socket socket = new Socket();
      /*
      * If you create a Socket using the no-argument constructor, it does not initiate a connection immediately.
      Instead, you must explicitly call the connect() method.

      In the connect() method:
      The first argument is an InetSocketAddress containing the hostname and port.
      The second argument specifies the connection timeout in milliseconds.
      The socket attempts to connect only when the connect() method is called.
      * */
      socket.connect(new InetSocketAddress("192.168.1.250", 45678), 3000);
    } catch (SocketTimeoutException e) {
      // ConnectException: Operation timed out
      e.printStackTrace();
    }

    long end = System.currentTimeMillis();
    System.out.println("end = " + (end - start));
  }
}
