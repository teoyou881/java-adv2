package chat.client;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

  private final String host;
  private final int port;
  private Socket socket;
  private DataInputStream input;
  private DataOutputStream output;
  private ReadHandler readHandler;
  private WriteHandler writeHandler;
  private boolean closed = false;

  public Client(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void start() throws IOException {
    log("Client started");
    socket = new Socket(host, port);
    input = new DataInputStream(socket.getInputStream());
    output = new DataOutputStream(socket.getOutputStream());

    readHandler = new ReadHandler(input, this);
    writeHandler = new WriteHandler(output, this);

    Thread readThread = new Thread(readHandler, "readHandler");
    Thread writeThread = new Thread(writeHandler, "writeHandler");
    readThread.start();
    writeThread.start();
  }

  public synchronized void close() {
    if (closed) {
      return;
    }
    writeHandler.close();
    readHandler.close();
    closeAll(socket, input, output);
    closed = true;
    log("Connection closed: " + socket);
  }
}
