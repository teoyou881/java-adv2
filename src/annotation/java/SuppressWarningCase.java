package annotation.java;

import java.util.ArrayList;
import java.util.List;

public class SuppressWarningCase {

  @SuppressWarnings("unused")
  public void unusedWarning() {
    // Suppresses warning for unused variable
    int unusedVariable = 10;
  }

  @SuppressWarnings("deprecation")
  public void deprecatedMethod() {
    // Suppresses warning for using deprecated methods
    java.util.Date date = new java.util.Date();
    int date1 = date.getDate();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public void uncheckedCast() {
    // Suppresses warnings for raw types and unchecked casts
    List list = new ArrayList();
    List<String> stringList = (List<String>) list;
  }

  @SuppressWarnings("all")
  public void suppressAllWarning() {
    // Suppresses all warnings in this method
    java.util.Date date = new java.util.Date();
    date.getDate();
    List list = new ArrayList();
    List<String> stringList = (List<String>) list;
  }
}
