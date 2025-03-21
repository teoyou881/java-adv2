package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

  public static void main(String[] args) {
    try {
      logic();
    } catch (CallException e) {
      System.out.println("Handling CallException");
      e.printStackTrace();
    } catch (CloseException e) {
      System.out.println("Handling CloseException");
      e.printStackTrace();
    }
  }

  private static void logic() throws CallException, CloseException {
    ResourceV1 resource1 = null;
    ResourceV1 resource2 = null;

    try {
      resource1 = new ResourceV1("resource1");
      resource2 = new ResourceV1("resource2");

      resource1.call();
      resource2.callEx(); // Throws CallException
    } catch (CallException e) {
      System.out.println("Exception occurred: " + e);
      throw e;
    } finally {
      if (resource2 != null) {
        try {
          resource2.closeEx();
        } catch (CloseException e) {
          // Ignore or log the exception from close()
          System.out.println("Close exception: " + e);
        }
      }
      if (resource1 != null) {
        try {
          resource1.closeEx();
        } catch (CloseException e) {
          System.out.println("Close exception: " + e);
        }
      }
    }
  }
}
