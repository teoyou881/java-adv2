package was.httpserver.servlet.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

public class AnnotationServletV3 implements HttpServlet {


  private final Map<String, ControllerMethod> pathMap;

  public AnnotationServletV3(List<Object> controllers) {
    this.pathMap = new HashMap<>();
    initializePathMap(controllers);
  }

  private void initializePathMap(List<Object> controllers) {
    for (Object controller : controllers) {
      Method[] declaredMethods = controller.getClass().getDeclaredMethods();
      for (Method declaredMethod : declaredMethods) {
        if (declaredMethod.isAnnotationPresent(Mapping.class)) {
          String path = declaredMethod.getAnnotation(Mapping.class).value();

          //todo
          //check duplicate path
          if (pathMap.containsKey(path)) {
            ControllerMethod controllerMethod = pathMap.get(path);
            throw new IllegalStateException(
                "Duplicate path: " + path + ", method: " + declaredMethod
                    + ", already registered: " + controllerMethod.method);
          }

          //pathMap.putIfAbsent(path, new ControllerMethod(controller, declaredMethod));
          pathMap.put(path, new ControllerMethod(controller, declaredMethod));
        }
      }
    }
  }


  @Override
  public void service(HttpRequest request, HttpResponse response)
      throws IOException, InvocationTargetException, IllegalAccessException {

    String path = request.getPath();

    ControllerMethod controllerMethod = pathMap.get(path);

    if (controllerMethod == null) {
      throw new PageNotFoundException("request: " + path);
    }

    controllerMethod.invoke(request, response);
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

  private static class ControllerMethod {

    private final Object controller;
    private final Method method;

    public ControllerMethod(Object controller, Method method) {
      this.controller = controller;
      this.method = method;
    }


    public void invoke(HttpRequest request, HttpResponse response) {
      Class<?>[] parameterTypes = method.getParameterTypes();

      //request, response
      Object[] args = new Object[parameterTypes.length];
      for (int i = 0; i < parameterTypes.length; i++) {
        if (parameterTypes[i] == HttpRequest.class) {
          args[i] = request;
        } else if (parameterTypes[i] == HttpResponse.class) {
          args[i] = response;
        } else {
          throw new IllegalArgumentException("Unsupported parameter type: " + parameterTypes[i]);
        }
      }

      try {
        method.invoke(controller, args);
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }

    }
  }
}

