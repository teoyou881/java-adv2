package test_chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Concurrency handling
class SessionManager {

  private List<Session> sessions = new ArrayList<>();

  public synchronized void add(Session session) {
    sessions.add(session);
  }

  public synchronized void remove(Session session) {
    sessions.remove(session);
  }

  public synchronized void closeAll() {
    for (Session session : sessions) {
      session.close();
    }
    sessions.clear();
  }

  public synchronized void print(String msg) throws IOException {
    for (Session session : sessions) {
      session.print(msg);
    }
  }
}
