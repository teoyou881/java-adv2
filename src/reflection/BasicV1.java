package reflection;

import reflection.data.BasicData;

public class BasicV1 {

  public static void main(String[] args) throws ClassNotFoundException {
    //There are 3 ways to look up class metadata

    //1. find it in class
    Class<BasicData> basicDataClass1 = BasicData.class;
    System.out.println("basicDataClass1 = " + basicDataClass1);

    //2. find it in instance
    BasicData basicInstance = new BasicData();
    Class<? extends BasicData> basicDataClass2 = basicInstance.getClass();
    System.out.println("basicDataClass2 = " + basicDataClass2);

    //3. find it using string
    String className = "reflection.data.BasicData";
    Class<?> basicDataClass3 = Class.forName(className);
    System.out.println("basicDataClass3 = " + basicDataClass3);

  }

}
