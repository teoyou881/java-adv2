package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import reflection.data.BasicData;

public class MethodV2 {

  public static void main(String[] args)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    // Static method call - regular method invocation
    BasicData helloInstance = new BasicData();
    helloInstance.call(); // This is a static call unless you change the code.

    // Dynamic method call - using reflection
    Class<? extends BasicData> helloClass = helloInstance.getClass();
    String methodName = "hello";

    // The method name can be changed dynamically
    Method method1 = helloClass.getDeclaredMethod(methodName, String.class);
    Object returnValue = method1.invoke(helloInstance, "hi");
    System.out.println("returnValue = " + returnValue);
  }
}
