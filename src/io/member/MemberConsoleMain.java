package io.member;

import io.member.impl.ObjectMemberRepository;
import java.io.IOException;
import java.util.Scanner;

public class MemberConsoleMain {

  //  private static final MemberRepository repository = new MemoryMemberRepository();
//  private static final MemberRepository repository = new FileMemberRepository();
//  private static final MemberRepository repository = new DataMemberRepository();
  private static final MemberRepository repository = new ObjectMemberRepository();

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("1.register | 2.search member list| 3.exit");
      System.out.println("Enter your choice: ");
      int choice = scanner.nextInt();
      //remove newline
      scanner.nextLine();
      switch (choice) {
        case 1:
          //register
          registerMember(scanner);
          break;
        case 2:
          //search list
          displayMembers();
          break;
        case 3:
          System.out.println("Exiting...");
          return;
        default:
          System.out.println("Invalid choice");
      }
    }

  }


  private static void registerMember(Scanner scanner) throws IOException {
    System.out.print("id: ");
    String id = scanner.nextLine();

    System.out.print("name: ");
    String name = scanner.nextLine();

    System.out.print("age: ");
    int age = scanner.nextInt();
    scanner.nextLine();

    repository.add(new Member(id, name, age));
    System.out.println("Member registered successfully");

  }

  private static void displayMembers() throws IOException {
    System.out.println("Member list:");
    repository.findAll().forEach(System.out::println);
  }
}
