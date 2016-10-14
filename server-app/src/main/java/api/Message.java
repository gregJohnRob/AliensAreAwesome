package api;

// Defines a message as a response from one of the endpoints.

public class Message<TData> {

  public int    Status;
  public String Message;
  public TData  Data;
  
}
