package was.v2;

import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpRequestHandlerV2 implements Runnable {

  private final Socket socket;

  public HttpRequestHandlerV2(Socket socket) {
    this.socket = socket;
  }

  private static String requestToString(BufferedReader reader) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      if (line.isEmpty()) {
        break;
      }
      sb.append(line).append("\n");
    }
    return sb.toString();
  }

  @Override
  public void run() {
    try {
      process();
    } catch (Exception e) {
      log(e);
    }
  }

  private void process() {
    try (socket;
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),
            StandardCharsets.UTF_8)); PrintWriter writer = new PrintWriter(socket.getOutputStream(),
        false, StandardCharsets.UTF_8);) {

      String requestString = requestToString(reader);
      if (requestString.contains("/favicon.ico")) {
        log("request favicon.ico");
        return;
      }

      log("http request info");
      System.out.println(requestString);

      log("creating response");
      Thread.sleep(3000);
      responseToClient(writer);
      log("response sent");
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void responseToClient(PrintWriter writer) {
    String body = "<h1> Hello Wolrd</h1>";
    int length = body.getBytes(StandardCharsets.UTF_8).length;

    //creating http header
    StringBuilder sb = new StringBuilder();
    sb.append("HTTP/1.1 200 OK\r\n");
    sb.append("Content-Type: text/html\r\n");
    sb.append("Content-Length: " + length + "\r\n");
    sb.append("\r\n");
    sb.append(body);
    log("print http response");
    System.out.println(sb.toString());

    writer.println((sb));
    writer.flush();
  }

}
