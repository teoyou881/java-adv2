package was.httpserver;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import was.httpserver.servlet.InternalErrorServlet;
import was.httpserver.servlet.NotFoundServlet;

public class ServletManager {

  private final Map<String, HttpServlet> servletMap = new HashMap<>();
  private HttpServlet defaultServlet;
  private HttpServlet notFoundErrorServlet = new NotFoundServlet();
  private HttpServlet internalErrorServlet = new InternalErrorServlet();

  public ServletManager() {
  }

  public void add(String path, HttpServlet servlet) {
    servletMap.put(path, servlet);
  }

  public void setDefaultServlet(HttpServlet defaultServlet) {
    this.defaultServlet = defaultServlet;
  }

  public void setNotFoundErrorServlet(HttpServlet notFoundErrorServlet) {
    this.notFoundErrorServlet = notFoundErrorServlet;
  }

  public void setInternalErrorServlet(HttpServlet internalErrorServlet) {
    this.internalErrorServlet = internalErrorServlet;
  }

  public void execute(HttpRequest request, HttpResponse response) throws
      IOException, InvocationTargetException, IllegalAccessException {
    try {
      HttpServlet servlet = servletMap.getOrDefault(request.getPath(), defaultServlet);
      if (servlet == null) {
        throw new PageNotFoundException("request url= " + request.getPath());
      }
      servlet.service(request, response);
    } catch (PageNotFoundException e) {
      e.printStackTrace();
      notFoundErrorServlet.service(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      internalErrorServlet.service(request, response);
    }
  }
}