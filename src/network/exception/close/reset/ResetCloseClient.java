package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

  public static void main(String[] args) throws IOException, InterruptedException {
    Socket socket = new Socket("localhost", 12345);
    log("Socket connected: " + socket);
    InputStream input = socket.getInputStream();
    OutputStream output = socket.getOutputStream();

    // client <- server: FIN
    Thread.sleep(1000); // wait until server calls close()

    // client -> server: PUSH[1]
    output.write(1);

    // client <- server: RST
    Thread.sleep(1000); // wait for RST message

    try {
      // java.net.SocketException: Connection reset will occur!
      int read = input.read();
      System.out.println("read = " + read);
    } catch (SocketException e) {
      e.printStackTrace();
    }

    try {
      output.write(1);
    } catch (SocketException e) {
      // java.net.SocketException: Broken pipe
      e.printStackTrace();
    }
  }
}
