package api.responses;

import api.Message;

public class PlayerLoginResponse
{
  public static Message<String> Success(String aId)
  {
    Message<String> ms = new Message<String>();
    
    ms.Message = "Ok";
    ms.Data = aId;
    
    return ms;  
  }
  
  public static Message<String> Unkown              =  sendFailure("An unknown error occured.");
  public static Message<String> NoUsername          = sendFailure("No 'username' param provided."); 
  public static Message<String> DuplicateUsername   = sendFailure("Username already exists.");

  
  private static Message<String> sendFailure(String aMessage)
  {
    Message<String> ms = new Message<String>();
    
    ms.Message = aMessage;
    ms.Data = null;
    
    return ms;  
  }
  
}
