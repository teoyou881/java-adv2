package was.httpserver;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class HttpResponse {

  private final PrintWriter writer;
  private final StringBuilder bodyBuilder = new StringBuilder();
  private int statusCode = 200;
  private String contentType = "text/html; charset=utf-8";

  public HttpResponse(PrintWriter writer) {
    this.writer = writer;
  }

  public void setStatus(int statusCode) {
    this.statusCode = statusCode;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public void writeBody(String body) {
    bodyBuilder.append(body);
  }

  public void flush() {
    int contentLength = bodyBuilder.toString().getBytes(StandardCharsets.UTF_8).length;
    writer.println(
        "HTTP/1.1 " + statusCode + " " + contentLength + " " + getReasonPhrase(statusCode));
    writer.println("Content-Type: " + contentType);
    writer.println("Content-Length: " + contentLength);
    writer.println();
    writer.println(bodyBuilder);
    writer.flush();
  }

  private String getReasonPhrase(int statusCode) {
    switch (statusCode) {
      case 200:
        return "OK";
      case 400:
        return "Bad Request";
      case 404:
        return "Not Found";
      case 500:
        return "Internal Server Error";
      default:
        return "Unknown";
    }
  }
}
