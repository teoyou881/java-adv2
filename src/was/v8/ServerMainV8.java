package was.v8;

import java.io.IOException;
import java.util.List;
import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV2;

public class ServerMainV8 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    List<Object> controllers = List.of(new SiteControllerV8(), new SearchControllerV8());
    AnnotationServletV2 annotationServlet = new AnnotationServletV2(controllers);

    ServletManager servletManager = new ServletManager();
    servletManager.setDefaultServlet(annotationServlet);
    servletManager.add("/favicon.ico", new DiscardServlet());

    HttpServer httpServer = new HttpServer(PORT, servletManager);
    httpServer.start();
  }

}
