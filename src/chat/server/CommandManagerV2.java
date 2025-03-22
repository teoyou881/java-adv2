package chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {

  public static final String DELIMITER = "|";
  private final SessionManager sessionManager;

  public CommandManagerV2(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public void execute(String totalMessage, Session session) throws IOException {
    if (totalMessage.startsWith("/join")) {
      String[] split = totalMessage.split(DELIMITER);
      String username = split[1];
      session.setUsername(username);
      sessionManager.sendAll(username + " has joined the chat.");

    } else if (totalMessage.startsWith("/message")) {
      // Send message to all clients
      String[] split = totalMessage.split(DELIMITER);
      String message = split[1];
      sessionManager.sendAll("[" + session.getUsername() + "] " + message);

    } else if (totalMessage.startsWith("/change")) {
      String[] split = totalMessage.split(DELIMITER);
      String changeName = split[1];
      sessionManager.sendAll(session.getUsername() + " changed their name to " + changeName + ".");
      session.setUsername(changeName);

    } else if (totalMessage.startsWith("/users")) {
      List<String> usernames = sessionManager.getAllUsername();
      StringBuilder sb = new StringBuilder();
      sb.append("Total users: ").append(usernames.size()).append("\n");
      for (String username : usernames) {
        sb.append(" - ").append(username).append("\n");
      }
      session.send(sb.toString());

    } else if (totalMessage.startsWith("/exit")) {
      throw new IOException("exit");

    } else {
      session.send("Unknown command: " + totalMessage);
    }
  }
}
