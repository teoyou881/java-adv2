package was.v6;

import java.io.IOException;
import java.util.List;
import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.reflection.ReflectionServlet;
import was.v5.servlet.HomeServlet;

public class ServerMainV6 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    List<Object> controllers = List.of(new SiteControllerV6(), new SearchControllerV6());
    ReflectionServlet reflectionServlet = new ReflectionServlet(controllers);

    ServletManager servletManager = new ServletManager();
    servletManager.setDefaultServlet(reflectionServlet);
    servletManager.add("/", new HomeServlet());
    servletManager.add("/favicon.ico", new DiscardServlet());

    HttpServer httpServer = new HttpServer(PORT, servletManager);
    httpServer.start();
  }

}
