package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {

  public static void main(String[] args) {
    // Retrieve all available charsets (Java + OS)
    SortedMap<String, Charset> charsets = Charset.availableCharsets();
    for (String charsetName : charsets.keySet()) {
      System.out.println("charsetName = " + charsetName);
    }
    System.out.println("=====");

    // Retrieve charset by name (case-insensitive)
    Charset charset1 = Charset.forName("MS949");
    System.out.println("charset1 = " + charset1);

    // Retrieve aliases of the charset
    Set<String> aliases = charset1.aliases();
    for (String alias : aliases) {
      System.out.println("alias = " + alias);
    }

    // Retrieve UTF-8 charset by name
    Charset charset2 = Charset.forName("UTF-8");
    System.out.println("charset2 = " + charset2);

    // Retrieve UTF-8 using the StandardCharsets constant
    Charset charset3 = StandardCharsets.UTF_8;
    System.out.println("charset3 = " + charset3);

    // Retrieve the default charset of the system
    Charset defaultCharset = Charset.defaultCharset();
    System.out.println("defaultCharset = " + defaultCharset);
  }
}
