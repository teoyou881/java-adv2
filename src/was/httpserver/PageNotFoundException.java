package was.httpserver;

//When can't find any page
public class PageNotFoundException extends RuntimeException {

  public PageNotFoundException(String message) {
    super(message);
  }
}