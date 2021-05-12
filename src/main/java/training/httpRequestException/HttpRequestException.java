package training.httpRequestException;

public class HttpRequestException extends Exception { 
  public HttpRequestException(String errorMessage) {
    super(errorMessage);
  }
}