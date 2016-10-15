package api.responses;

import api.Game;
import api.Message;
import java.util.List;
import java.util.ArrayList;

public class PlayerGameResponse
{
  public static class Payload
  {
    public List<PayloadEntry> Entries;
    
    public Payload(List<Game> aGames)
    {
      Entries = new ArrayList<PayloadEntry>(aGames.size());
      
      for(Game g : aGames) { Entries.add(new PayloadEntry(g)); }
    }
  }
  
  public static class PayloadEntry
  {
    public String Id;
    public String Type;
    public int CurrentPlayers;
    public int MaxPlayers;
    
    public PayloadEntry(Game aGame)
    {
      Id = aGame.getId();
      Type = aGame.getMap().getType();
      CurrentPlayers = aGame.getCurrentPlayers();
      MaxPlayers = 2;      
    }
  }
  
  
  public static Message<Payload> Success(List<Game> aGames)
  {
    Message<Payload> ms = new Message<Payload>();
    
    ms.Message = "Ok";   
    ms.Data = new PlayerGameResponse.Payload(aGames);
    
    return ms;
  }
  
  public static Message<Payload> NoClientId         = sendFailure("No param 'clientId'.");
  public static Message<Payload> InvalidClientId    = sendFailure("Param 'clientId' is invalid.");
  
  
  private static Message<Payload> sendFailure(String aMessage)
  {
      Message<Payload> ms = new Message<Payload>();
      
      ms.Message = aMessage;
      ms.Data = null;
      
      return ms;
  }
  
}
