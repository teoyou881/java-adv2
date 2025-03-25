package was.httpserver.servlet.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

public class AnnotationServletV1 implements HttpServlet {

  private final List<Object> controllers;

  public AnnotationServletV1(List<Object> controllers) {
    this.controllers = controllers;
  }


  @Override
  public void service(HttpRequest request, HttpResponse response)
      throws IOException, InvocationTargetException, IllegalAccessException {

    String path = request.getPath();

    for (Object controller : controllers) {
      Method[] declaredMethods = controller.getClass().getDeclaredMethods();
      for (Method declaredMethod : declaredMethods) {
        if (declaredMethod.isAnnotationPresent(Mapping.class)) {
          Mapping mapping = declaredMethod.getAnnotation(Mapping.class);
          String value = mapping.value();
          if (value.equals(path)) {
            invoke(controller, declaredMethod, request, response);
            return;
          }
        }
      }
    }
    throw new PageNotFoundException("request= " + request);
  }

  private static void invoke(Object controller, Method declaredMethod, HttpRequest request,
      HttpResponse response) {
    try {
      declaredMethod.invoke(controller, request, response);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
