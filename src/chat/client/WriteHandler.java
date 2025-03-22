package chat.client;

import static util.MyLogger.log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriteHandler implements Runnable {

  private static final String DELIMITER = "|";
  private final DataOutputStream output;
  private final Client client;
  private boolean closed = false;

  public WriteHandler(DataOutputStream output, Client client) {
    this.output = output;
    this.client = client;
  }

  private static String inputUsername(Scanner scanner) {
    System.out.println("Enter your name:");
    String username;
    do {
      username = scanner.nextLine();
    } while (username.isEmpty());
    return username;
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);
    try {
      String username = inputUsername(scanner);
      output.writeUTF("/join" + DELIMITER + username);
      while (true) {
        String toSend = scanner.nextLine();
        if (toSend.isEmpty()) {
          continue;
        }
        if (toSend.equals("/exit")) {
          output.writeUTF(toSend);
          break;
        }
        // If starts with "/", it's a command; otherwise it's a normal message
        if (toSend.startsWith("/")) {
          output.writeUTF(toSend);
        } else {
          output.writeUTF("/message" + DELIMITER + toSend);
        }
      }
    } catch (IOException | NoSuchElementException e) {
      log(e);
    } finally {
      client.close();
    }
  }

  public synchronized void close() {
    if (closed) {
      return;
    }
    try {
      System.in.close(); // Stop input from user
    } catch (IOException e) {
      log(e);
    }
    closed = true;
    log("writeHandler terminated");
  }
}
