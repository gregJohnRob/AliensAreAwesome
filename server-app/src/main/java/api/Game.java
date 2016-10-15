package api;

import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.util.Random;
import java.util.HashMap;

/* Game - Handles all the general game logic
 * Passes off the actual methods to the PlayerStatus
 */
public class Game
{
  private final String iId;
 
  private final GameMap iMap;
  private final List<String> iActivePlayerOrder;
  private final HashMap<String, PlayerStatus> iActivePlayers;

  private String iCurrentPlayer;
  
  public Game(List<String> aPlayers)
  {
    iCurrentPlayer = null;
    iActivePlayerOrder = aPlayers;
    
    iId = getNewGameId();
    iMap = new GameMap();
    
    iActivePlayers = new HashMap<String, PlayerStatus>();
    
    for(String player : aPlayers)
    {
      if(iCurrentPlayer == null) { iCurrentPlayer = player; }
      
      iActivePlayers.put(player, new PlayerStatus(player, iMap));
    }
    
  }
  
  public synchronized boolean DoAttack(String aPlayer, String aUnit, int aX, int aY)
  {
    if(isCurrentPlayer(aPlayer))
    {
      PlayerStatus currentPlayerStatus = iActivePlayers.get(iCurrentPlayer);
      return currentPlayerStatus.attack(aUnit, aX, aY);   
    }
    
    return false;
  }
  
  public synchronized boolean DoMove(String aPlayer, String aUnit, int aX, int aY)
  {
    if(isCurrentPlayer(aPlayer))
    {
      PlayerStatus currentPlayerStatus = iActivePlayers.get(iCurrentPlayer);
      return currentPlayerStatus.move(aUnit, aX, aY);
    }
    
    return false;
  }
  
  public synchronized boolean DoWait(String aPlayer, String aUnit)
  {
    if(isCurrentPlayer(aPlayer))
    {    
      PlayerStatus currentPlayerStatus = iActivePlayers.get(iCurrentPlayer);     
      return currentPlayerStatus.waitOn(aUnit);
    }
   
    return false;
  }
  
  public synchronized boolean DoEnd(String aPlayer)
  {
    if(isCurrentPlayer(aPlayer))
    {
      int currentIndex = iActivePlayerOrder.indexOf(iCurrentPlayer);
      int newIndex = (currentIndex++) % iActivePlayerOrder.size();
      
      if(newIndex >= 0 && newIndex <= iActivePlayerOrder.size())
      {
        iCurrentPlayer = iActivePlayerOrder.get(newIndex);
        PlayerStatus newPlayerStatus = iActivePlayers.get(iCurrentPlayer);
        
        if(newPlayerStatus != null)
        {
          newPlayerStatus.beginTurn(); 
          return true;
        }
      }
    }
    
    return false;
  }
  
  
  private boolean isCurrentPlayer(String aPlayer) { return aPlayer == iCurrentPlayer; }
  
  private String getNewGameId() { return UUID.randomUUID().toString(); }
  
  // TODO
  // Get a general status to return for the game/status REST method
  
}
