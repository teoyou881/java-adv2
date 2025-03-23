package reflection.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Class<?> aClass = Class.forName("reflection.data.BasicData");

    Constructor<?> declaredConstructors = aClass.getDeclaredConstructor(String.class);
    declaredConstructors.setAccessible(true);
    Object instance = declaredConstructors.newInstance("hello");
    System.out.println("instance = " + instance);

    Method method1 = aClass.getDeclaredMethod("call");
    Method method2 = aClass.getDeclaredMethod("privateMethod");

    method1.invoke(instance);
    
    method2.setAccessible(true);
    method2.invoke(instance);

  }
}
