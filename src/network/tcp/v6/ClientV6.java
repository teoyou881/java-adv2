package network.tcp.v6;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientV6 {

  public static final int PORT = 12345;

  public static void main(String[] args) {
    log("Client started");

    try (
        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
    ) {
      log("Socket connected: " + socket);
      Scanner scanner = new Scanner(System.in);

      while (true) {
        System.out.print("Enter message: ");
        String toSend = scanner.nextLine();

        // Send message to server
        output.writeUTF(toSend);
        log("client -> server: " + toSend);

        if (toSend.equals("exit")) {
          break;
        }

        // Receive message from server
        String received = input.readUTF();
        log("client <- server: " + received);
      }
    } catch (IOException e) {
      log(e);
    }
  }
}
