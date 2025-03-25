package annotation.validator;

public class User {

  @NotEmpty(message = "Name must not be empty.")
  private String name;

  @Range(min = 1, max = 100, message = "Age must be between 1 and 100.")
  private int age;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
