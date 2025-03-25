package annotation.basic.inherited;

import java.lang.annotation.Annotation;

public class InheritedMain {

  //Annotations are not inherited in interface implementations.
  //Annotations are not inherited between parent and child interfaces either.
  public static void main(String[] args) {
    print(Parent.class);
    print(Child.class);
    print(TestInterface.class);
    print(TestInterfaceImpl.class);
  }

  private static void print(Class<?> clazz) {
    System.out.println("class: " + clazz);
    for (Annotation annotation : clazz.getAnnotations()) {
      System.out.println(" - " +
          annotation.annotationType().getSimpleName());
    }
    System.out.println();
  }
}