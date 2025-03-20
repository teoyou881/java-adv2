package network.tcp.v2;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV2 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("Server started");

    // Create a server socket and listen on the specified port
    ServerSocket serverSocket = new ServerSocket(PORT);
    log("Server socket created - Listening on port: " + PORT);

    // Accept a client connection (blocking)
    Socket socket = serverSocket.accept();
    log("Socket connected: " + socket);

    // Create input and output streams
    DataInputStream input = new DataInputStream(socket.getInputStream());
    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

    while (true) {
      // Receive message from the client (blocking)
      String received = input.readUTF();
      log("Client -> Server: " + received);

      // If client sends "exit", terminate the server
      if (received.equals("exit")) {
        break;
      }

      // Send response to the client
      String toSend = received + " World!";
      output.writeUTF(toSend);
      log("Client <- Server: " + toSend);
    }

    // Close resources
    log("Closing connection: " + socket);
    input.close();
    output.close();
    socket.close();
    serverSocket.close();
  }
}
