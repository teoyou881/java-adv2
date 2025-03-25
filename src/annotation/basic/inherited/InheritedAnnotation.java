package annotation.basic.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation is also applied to subclasses when inherited
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {

}
