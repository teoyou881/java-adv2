package chat.server;

import java.io.IOException;

public interface CommandManager {

  void execute(String totalMessage, chat.server.Session session) throws IOException;
}
