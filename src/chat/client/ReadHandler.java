package chat.client;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadHandler implements Runnable {

  private final DataInputStream input;
  private final Client client;
  public boolean closed = false;

  public ReadHandler(DataInputStream input, Client client) {
    this.input = input;
    this.client = client;
  }

  @Override
  public void run() {
    try {
      while (true) {
        String received = input.readUTF();
        System.out.println(received);
      }
    } catch (IOException e) {
      log(e);
    } finally {
      client.close();
    }
  }

  public synchronized void close() {
    if (closed) {
      return;
    }
    // Add shutdown logic here if needed
    closed = true;
    log("readHandler terminated");
  }
}
