package api.responses;

import api.Game;
import api.GameMap;
import api.Message;
import api.PlayerStatus;
import api.Unit;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class GameStatusResponse
{
  
  public static class Payload
  {
    public String Id;
    public String currentPlayerId;
    
    public PayloadMap Map;    
    public Map<String, PayloadPlayerStatus> PlayerStatus;
    
    public Payload(Game aGame)
    {
      Id = aGame.getId();
      currentPlayerId = aGame.getCurrentPlayerId();
      
      Map = new PayloadMap(aGame.getMap());      
      PlayerStatus = new HashMap<String, PayloadPlayerStatus>();      
    }
    
  }
    public static class PayloadMap
    {
      public int Height;
      public int Width;
      public String Type;
      public int[][] Data;
      
      public PayloadMap(GameMap aMap)
      {
        Height  = aMap.getHeight();
        Width   = aMap.getWidth();
        Type    = aMap.getType();
        Data    = aMap.getData();
      }    
    }
    
    public static class PayloadPlayerStatus
    {
      public String Id;
      public Map<String, Unit> Units;
      
      public PayloadPlayerStatus(String aPlayerId, PlayerStatus aStatus)
      {
        Id = aPlayerId;      
        Units = aStatus.getUnitMap();       
      }    
    }

    
    
    public static Message<Payload> Success(Game aGame)
    {
      Message<Payload> ms = new Message<Payload>();
      
      ms.Message = "Ok";
      ms.Data = new Payload(aGame);
      
      return ms;
    }
    
    
    public static Message<Payload> NoClientId       = sendFailure("No param 'clientId' given.");
    public static Message<Payload> InvalidClientId  = sendFailure("The given clientId doesn't exist.");
    
    public static Message<Payload> NoGameId         = sendFailure("No param 'gameId' given.");
    public static Message<Payload> InvalidGameId    = sendFailure("The given gameId doesn't exist.");
    
    public static Message<Payload> PlayerNotInGame  = sendFailure("The given player is not in this game.");
    
    
    private static Message<Payload> sendFailure(String aMessage)
    {
      Message<Payload> ms = new Message<Payload>();
      
      ms.Message = aMessage;
      ms.Data = null;
      
      return ms;
    }
}
