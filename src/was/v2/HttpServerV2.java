package was.v2;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV2 {

  private final ExecutorService ex = Executors.newFixedThreadPool(10);
  private final int port;


  public HttpServerV2(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);
    log("Server started port: " + port);

    while (true) {
      Socket socket = serverSocket.accept();
      ex.submit(new HttpRequestHandlerV2(socket));
    }
  }
}
