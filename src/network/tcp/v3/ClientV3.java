package network.tcp.v3;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientV3 {

  public static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Client started");

    // Connect to the server
    Socket socket = new Socket("localhost", PORT);
    DataInputStream input = new DataInputStream(socket.getInputStream());
    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
    log("Socket connected: " + socket);

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter message: ");
      String toSend = scanner.nextLine();

      // Send message to the server
      output.writeUTF(toSend);
      log("Client -> Server: " + toSend);

      if (toSend.equals("exit")) {
        break;
      }

      // Receive message from the server
      String received = input.readUTF();
      log("Client <- Server: " + received);
    }

    // Close resources
    log("Closing connection: " + socket);
    input.close();
    output.close();
    socket.close();
  }
}
