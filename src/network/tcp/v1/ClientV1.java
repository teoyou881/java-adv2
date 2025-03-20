package network.tcp.v1;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientV1 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Client started");

    // Connect to the server
    Socket socket = new Socket("localhost", PORT);
    DataInputStream input = new DataInputStream(socket.getInputStream());
    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
    log("Socket connected: " + socket);

    // Send message to the server
    String toSend = "Hello";
    output.writeUTF(toSend);
    log("Client -> Server: " + toSend);

    // Receive message from the server
    String received = input.readUTF();
    log("Client <- Server: " + received);

    // Close resources
    log("Closing connection: " + socket);
    input.close();
    output.close();
    socket.close();
  }
}
