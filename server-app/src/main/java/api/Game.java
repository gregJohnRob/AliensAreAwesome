package api;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;

/* Game - Handles all the general game logic
 * Passes off the actual methods to the PlayerStatus
 */
public class Game
{
  private final String iId;
 
  private final GameMap iMap;
  private final List<String> iLobby;
  private final List<String> iActivePlayerOrder;
  private final Map<String, PlayerStatus> iActivePlayers;

  private String iCurrentPlayer;
  
  public Game()
  {
    iCurrentPlayer = null;

    iId = getNewGameId();
    iMap = new GameMap();
    
    iLobby = new ArrayList<String>();
    iActivePlayerOrder = new ArrayList<String>();
    iActivePlayers = new HashMap<String, PlayerStatus>();
  }
  
  
  public String getId()             { return iId; }
  public GameMap getMap()           { return iMap; }
  public Set<String> getPlayers()   { return iActivePlayers.keySet(); }
  
  public synchronized boolean addPlayer(String aPlayer)
  {
    if(!hasPlayer(aPlayer))
    {
      iLobby.add(aPlayer);
      return true;
    }
    return false; 
  }
  
  public synchronized boolean removePlayer(String aPlayer)
  {
    if(hasPlayer(aPlayer))
    {
      iLobby.remove(aPlayer);
      return true;
    }
    
    return false;
  }
  
  public synchronized boolean hasPlayer(String aPlayer) { return iLobby.contains(aPlayer); }
    
  public synchronized boolean isFull(int aMaxPlayers) { return iLobby.size() == aMaxPlayers; }
  
  public synchronized void startGame()
  {
    //TODO --- do something?
  }
  
  
  public synchronized int DoAttack(String aPlayer, String aUnit, int aX, int aY)
  {
    if(isCurrentPlayer(aPlayer))
    {
      PlayerStatus currentPlayerStatus = iActivePlayers.get(iCurrentPlayer);
      return currentPlayerStatus.attack(aUnit, aX, aY);   
    }
    
    return -1;
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
