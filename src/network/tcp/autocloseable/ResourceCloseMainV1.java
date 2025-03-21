package network.tcp.autocloseable;

public class ResourceCloseMainV1 {

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
    ResourceV1 resource1 = new ResourceV1("resource1");
    ResourceV1 resource2 = new ResourceV1("resource2");

    resource1.call();
    resource2.callEx(); // Throws CallException

    System.out.println("Resource cleanup"); // This will not be executed

    resource2.closeEx();
    resource1.closeEx();
  }
}
