package api.responses;

import api.Message;

public class TurnWaitResponse {
  
  public static Message<String> Success(String unitId)
  {
    Message<String> ms = new Message<String>();
    
    ms.Message = "Ok";
    ms.Data = unitId;
    
    return ms;  
  }
  
  public static Message<String> InvalidWait              =  sendFailure("An unknown error occured.");
  public static Message<String> NoClientId          = sendFailure("No 'clientId' param provided."); 
  public static Message<String> InvalidClientId   = sendFailure("No user linked to this Id.");
  public static Message<String> NoGameId          = sendFailure("No 'gameId' param provided."); 
  public static Message<String> InvalidGameId   = sendFailure("No game related to this Id.");
  public static Message<String> NoUnitId          = sendFailure("No 'gameId' param provided."); 
  public static Message<String> InvalidUnitId   = sendFailure("No game related to this Id.");
  public static Message<String> InvalidLocation   = sendFailure("No game related to this Id.");

  
  private static Message<String> sendFailure(String aMessage)
  {
    Message<String> ms = new Message<String>();
    
    ms.Message = aMessage;
    ms.Data = null;
    
    return ms;  
  }

}
