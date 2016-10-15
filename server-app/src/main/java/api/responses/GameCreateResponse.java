package api.responses;

import api.Game;
import api.Message;

public class GameCreateResponse 
{
  public static class Payload
  {
    public String Id;
    public String Type;
    public int CurrentPlayers;
    public int MaxPlayers;
    
    public Payload(Game aGame)
    {
      Id = aGame.getId();
      Type = aGame.getMap().getType();
      CurrentPlayers = aGame.getPlayers().size();
      MaxPlayers = 2;      
    }
  }
  
  public static Message<Payload> Success(Game aGame)
  {
    Message<Payload> ms = new Message<Payload>();
    
    ms.Message = "Ok";
    ms.Data = new Payload(aGame);
    
    return ms;  
  }
  
  public static Message<Payload> Unkown          = sendFailure("An unknown error occured.");
  public static Message<Payload> NoClientId      = sendFailure("No 'clientId' param provided."); 
  public static Message<Payload> InvalidClientId = sendFailure("No user with that id exists.");

  
  private static Message<Payload> sendFailure(String aMessage)
  {
    Message<Payload> ms = new Message<Payload>();
    
    ms.Message = aMessage;
    ms.Data = null;
    
    return ms;  
  }
}
