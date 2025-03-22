package chat.server;

import java.io.IOException;

public class CommandManagerV1 implements CommandManager {

  private final chat.server.SessionManager sessionManager;

  public CommandManagerV1(chat.server.SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public void execute(String totalMessage, chat.server.Session session) throws IOException {
    if (totalMessage.startsWith("/exit")) {
      throw new IOException("exit");
    }
    sessionManager.sendAll(totalMessage);
  }
}
