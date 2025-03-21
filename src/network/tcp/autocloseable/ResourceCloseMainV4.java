package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

  public static void main(String[] args) {
    try {
      logic();
    } catch (CallException e) {
      System.out.println("Handling CallException");

      Throwable[] suppressed = e.getSuppressed();
      for (Throwable throwable : suppressed) {
        System.out.println("Suppressed: " + throwable);
      }
      e.printStackTrace();
    } catch (CloseException e) {
      System.out.println("Handling CloseException");
      e.printStackTrace();
    }
  }

  private static void logic() throws CallException, CloseException {
    try (ResourceV2 r1 = new ResourceV2("resource1"); ResourceV2 r2 = new ResourceV2(
        "resource2");) {
      r1.call();
      r2.callEx();
    } catch (CallException e) {
      System.out.println("ex: " + e);
      throw e;
    }
  }

}
