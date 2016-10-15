package api.responses;

import api.Message;

public class GameJoinResponse
{
  public static Message<Boolean> NoClientId          = sendMsg("No param 'clientId' given.", false);
  public static Message<Boolean> InvalidClientId     = sendMsg("The given clientId doesn't exist.", false);
  
  public static Message<Boolean> NoGameId            = sendMsg("No param 'gameId' given.", false);
  public static Message<Boolean> InvalidGameId       = sendMsg("The given gameId doesn't exist.", false);
  
  public static Message<Boolean> PlayerAlreadyInGame = sendMsg("The given clientId was already part of this game.", false);
  
  public static Message<Boolean> Success             = sendMsg("Ok", true);
  
  
  public static Message<Boolean> sendMsg(String aMessage, boolean aResult)
  {
    Message<Boolean> ms = new Message<Boolean>();
    
    ms.Message = aMessage;
    ms.Data = aResult;
    
    return ms;
  }
  

}
