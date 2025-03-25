package annotation.basic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import util.MyLogger;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {

  String value();

  int count() default 0;

  String[] tags() default {};

  // MyLogger data();
  // other types not allowed
  // class info is allowed
  Class<? extends MyLogger> annoData() default MyLogger.class;
}
