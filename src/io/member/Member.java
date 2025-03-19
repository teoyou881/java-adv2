package io.member;

import java.io.Serializable;

public class Member implements Serializable {

  private String id;
  private String name;
  private int age;

  public Member() {
  }

  public Member(String name, String id, int age) {
    this.name = name;
    this.id = id;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
