package was.httpserver.servlet.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

public class AnnotationServletV2 implements HttpServlet {

  private final List<Object> controllers;

  public AnnotationServletV2(List<Object> controllers) {
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
    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();

    //request, response
    Object[] args = new Object[parameterTypes.length];
    for (int i = 0; i < parameterTypes.length; i++) {
      System.out.println("parameterTypes = " + Arrays.toString(parameterTypes));
      if (parameterTypes[i] == HttpRequest.class) {
        args[i] = request;
        System.out.println("i = " + i);
      } else if (parameterTypes[i] == HttpResponse.class) {
        args[i] = response;
        System.out.println("i = " + i);
      } else {
        throw new IllegalArgumentException("Unsupported parameter type: " + parameterTypes[i]);
      }
    }
    
    try {
      declaredMethod.invoke(controller, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }

  }
}

