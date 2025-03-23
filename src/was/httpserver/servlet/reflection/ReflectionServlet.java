package was.httpserver.servlet.reflection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

public class ReflectionServlet implements HttpServlet {

  private final List<Object> controllers;

  public ReflectionServlet(List<Object> controllers) {
    this.controllers = controllers;
  }

  @Override
  public void service(HttpRequest request, HttpResponse response)
      throws IOException {
    String path = request.getPath();
    for (Object controller : controllers) {
      Class<?> aClass = controller.getClass();
      Method[] declaredMethods = aClass.getDeclaredMethods();
      for (Method declaredMethod : declaredMethods) {
        String name = declaredMethod.getName();
        if (path.equals("/" + name)) {
          invoke(controller, declaredMethod, request, response);
          return;
        }
      }
    }

    //if we can't find
    throw new PageNotFoundException("request= " + path);
  }

  private static void invoke(Object controller, Method declaredMethod, HttpRequest
      request, HttpResponse response) {
    try {
      declaredMethod.invoke(controller, request, response);
    } catch (InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
