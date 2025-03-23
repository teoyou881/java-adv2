package was.httpserver;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class HttpRequestHandler implements Runnable {

  private final Socket socket;
  private final ServletManager servletManager;

  public HttpRequestHandler(Socket socket, ServletManager servletManager) {
    this.socket = socket;
    this.servletManager = servletManager;
  }

  @Override
  public void run() {
    try {
      process(socket);
    } catch (Exception e) {
      log(e);
      e.printStackTrace();
    }
  }

  private void process(Socket socket)
      throws IOException, InvocationTargetException, IllegalAccessException {
    try (socket; BufferedReader reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream(),
            UTF_8)); PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {

      HttpRequest request = new HttpRequest(reader);
      HttpResponse response = new HttpResponse(writer);

      log("HTTP request: " + request);
      servletManager.execute(request, response);
      response.flush();
      log("HTTP response completed");
    }
  }
}
