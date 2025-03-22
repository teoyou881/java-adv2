package test_chat;

import java.io.IOException;
import java.util.Scanner;

public class Client {


  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String line = scanner.nextLine();
      if (line.equals("/join")) {
        while (true) {
          User user = new User(scanner);
          user.join();
        }
      }
    }


  }


}
