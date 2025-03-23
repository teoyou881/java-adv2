package reflection;

import java.lang.reflect.Field;
import reflection.data.User;

public class FieldV2 {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    User user = new User("id1", "userA", 20);
    System.out.println("user.getName() = " + user.getName());

    Class<? extends User> aClass = user.getClass();
    Field namefield = aClass.getDeclaredField("name");

    // allow to access private field
    // private method can be accessed like that
    namefield.setAccessible(true);
    namefield.set(user, "userB");
    System.out.println("changed name: " + namefield.get(user));
  }

}
