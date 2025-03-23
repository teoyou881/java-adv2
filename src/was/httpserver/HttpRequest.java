package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

  private final Map<String, String> queryParameters = new HashMap<String, String>();
  private final Map<String, String> headers = new HashMap<String, String>();
  private String method;
  private String path;

  public HttpRequest(BufferedReader br) throws IOException {
    parseRequestLine(br);
    parseHeaders(br);

    //dealing with message body later
  }

  private void parseHeaders(BufferedReader br) throws IOException {
    String line;

    while ((line = br.readLine()) != null && !line.isEmpty()) {
      String[] headerParts = line.split(":");

      // use trim(). remove space
      headers.put(headerParts[0].trim(), headerParts[1].trim());
    }
  }

  private void parseRequestLine(BufferedReader br) throws IOException {
    String requestLine = br.readLine();
    if (requestLine == null) {
      throw new IOException("Invalid request line");
    }
    String[] parts = requestLine.split(" ");
    if (parts.length != 3) {
      throw new IOException("Invalid request line: " + requestLine);
    }

    method = parts[0];
    String[] pathParts = parts[1].split("\\?");
    path = pathParts[0];
    if (pathParts.length > 1) {
      parseQueryParameters(pathParts[1]);
    }
  }

  private void parseQueryParameters(String queryString) {
    for (String param : queryString.split("&")) {
      String[] keyValue = param.split("=");
      String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
      String value =
          keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
      queryParameters.put(key, value);
    }

  }

  public String getMethod() {
    return method;
  }

  public String getPath() {
    return path;
  }

  public String getParameter(String name) {
    return queryParameters.get(name);
  }

  public String getHeader
      (String name) {
    return headers.get(name);
  }

  @Override
  public String toString() {
    return "HttpRequest{" +
        "queryParameters=" + queryParameters +
        ", headers=" + headers +
        ", method='" + method + '\'' +
        ", path='" + path + '\'' +
        '}';
  }
}
