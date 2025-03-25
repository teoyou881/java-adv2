package annotation.validator;

import static util.MyLogger.log;

public class ValidatorV1Main {

  public static void main(String[] args) {
    User user = new User("user1", 0);
    Team team = new Team("", 0);
    try {
      log("== Validating user ==");
      validateUser(user);
    } catch (Exception e) {
      log(e);
    }
    try {
      log("== Validating team ==");
      validateTeam(team);
    } catch (Exception e) {
      log(e);
    }
  }

  private static void validateUser(User user) {
    if (user.getName() == null || user.getName().isEmpty()) {
      throw new RuntimeException("Name must not be empty.");
    }
    if (user.getAge() < 1 || user.getAge() > 100) {
      throw new RuntimeException("Age must be between 1 and 100.");
    }
  }

  private static void validateTeam(Team team) {
    if (team.getName() == null || team.getName().isEmpty()) {
      throw new RuntimeException("Name must not be empty.");
    }
    if (team.getMemberCount() < 1 || team.getMemberCount() > 999) {
      throw new RuntimeException("Member count must be between 1 and 999.");
    }
  }
}
