package reflection;

import java.lang.reflect.Method;
import reflection.data.BasicData;

public class MethodV1 {

  public static void main(String[] args) {
    Class<BasicData> basicDataClass = BasicData.class;

    System.out.println("=============== methods() ===============");
    // Retrieve all public methods of the class, including those inherited from its parent classes,
    // excluding private ones.
    Method[] methods = basicDataClass.getMethods();
    for (Method method : methods) {
      System.out.println("method = " + method);
    }

    System.out.println("=============== declaredMethods() ===============");
    //Returns all methods that are explicitly declared in the class itself,
    // regardless of their access level. Does not include inherited methods.
    Method[] declaredMethods = basicDataClass.getDeclaredMethods();
    for (Method method : declaredMethods) {
      System.out.println("method = " + method);
    }
  }
}
