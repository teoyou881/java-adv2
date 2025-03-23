package was.v3;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV3 {

  private final ExecutorService es = Executors.newFixedThreadPool(10);
  private final int port;

  public HttpServerV3(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);
    log("Server started on port: " + port); // "서버 시작 port: ..." → translated
    while (true) {
      Socket socket = serverSocket.accept();
      es.submit(new HttpRequestHandlerV3(socket));
    }
  }
}
