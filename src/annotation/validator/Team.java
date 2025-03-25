package annotation.validator;

public class Team {

  @NotEmpty(message = "Name must not be empty.")
  private String name;

  @Range(min = 1, max = 999, message = "Member count must be between 1 and 999.")
  private int memberCount;

  public Team(String name, int memberCount) {
    this.name = name;
    this.memberCount = memberCount;
  }

  public String getName() {
    return name;
  }

  public int getMemberCount() {
    return memberCount;
  }
}