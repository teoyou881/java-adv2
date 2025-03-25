package annotation.validator;

import static util.MyLogger.log;

public class ValidatorV2Main {

  public static void main(String[] args) {
    User user = new User("user1", 0);
    Team team = new Team("", 0);
    try {
      log("== user validation ==");
      Validator.validate(user);
    } catch (Exception e) {
      log(e);
    }

    try {
      log("== team validation ==");
      Validator.validate(team);
    } catch (Exception e) {
      log(e);
    }
  }
}