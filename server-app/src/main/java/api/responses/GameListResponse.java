package api.responses;

import java.util.ArrayList;
import java.util.List;

import api.Game;
import api.Message;
import api.responses.PlayerGameResponse.Payload;
import api.responses.PlayerGameResponse.PayloadEntry;

public class GameListResponse
{
  public List<PayloadEntry> Entries;
    
  public static class Payload
  {
    private List<PayloadEntry> Entries;
    
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
      CurrentPlayers = aGame.getPlayers().size();
      MaxPlayers = 2;      
    }
  }
  
  
  public static Message<Payload> Success(List<Game> aGames)
  {
    Message<Payload> ms = new Message<Payload>();
    
    ms.Message = "Ok";   
    ms.Data = new GameListResponse.Payload(aGames);
    
    return ms;
  }
}
