package reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import reflection.data.BasicData;

public class BasicV2 {

  public static void main(String[] args) {
    Class<BasicData> basicData = BasicData.class;

    System.out.println("basicData.getName() = " + basicData.getName());
    System.out.println("basicData.getSimpleName() = " + basicData.getSimpleName());
    System.out.println("basicData.getPackageName() = " + basicData.getPackageName());

    System.out.println("basicData.getSuperclass() = " + basicData.getSuperclass());
    System.out.println("Arrays.toString(basicData.getInterfaces()) = " + Arrays.toString(
        basicData.getInterfaces()));
    System.out.println("basicData.isInterface() = " + basicData.isInterface());
    System.out.println("basicData.isAnnotation() = " + basicData.isAnnotation());

    int modifiers = basicData.getModifiers();
    System.out.println("modifiers = " + modifiers);
    System.out.println("isPublic= " + Modifier.isPublic(modifiers));
    System.out.println("modifiers.toString = " + Modifier.toString(modifiers));

  }


}
