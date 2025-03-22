package test_chat;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class User {

  public static final int PORT = 12345;

  private String name;
  private Scanner scanner;
  private DataInputStream dis;
  private DataOutputStream dos;

  public User() {
  }

  public User(Scanner scanner) {
    this.scanner = scanner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void changeName(String name) {
    setName(name);
  }

  public void message(Scanner scanner, DataOutputStream dos) throws IOException {
    System.out.println("Enter msg: ");
    String msg = getName() + ": " + scanner.nextLine();
    dos.writeUTF(msg);
    log("user sent msg: " + msg);
    scanner.nextLine();
  }

  public void join() {
    try (
        Socket socket = new Socket("localhost", PORT);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {

      log("Socket connected: " + socket);

      System.out.println("Enter your name: ");
      this.name = scanner.nextLine();

      while (true) {

        String order = scanner.nextLine();
        if (order.equals("/message")) {
          this.message(scanner, dos);
        }

        if (scanner.nextLine().equals("exit")) {
          break;
        }

        // Receive message from server
        String received = dis.readUTF();
        log("client <- server: " + received);

      }
    } catch (Exception e) {
      log("e= " + e);
    }
  }
}
