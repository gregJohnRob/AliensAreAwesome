package api;

import java.util.Set;
import java.util.Map;
import java.util.Random;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.awt.Point;
import java.util.ArrayList;

/* Game - Handles all the general game logic
 * Passes off the actual methods to the PlayerStatus
 */
public class Game
{
  private final String iId;
  private static Random random = new Random();
 
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
  public int getCurrentPlayers()    { return iLobby.size(); }
  
  public synchronized boolean addPlayer(String aPlayer)
  {
    if(!hasPlayer(aPlayer))
    {
      iLobby.add(aPlayer);
      if (this.isFull(2)) {
        this.startGame();
      }
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
  
  
  public synchronized String getCurrentPlayerId() { return iCurrentPlayer; }
  
  
  public synchronized void startGame()
  {
    for (String playerId : iLobby) {
      PlayerStatus newPlayer = new PlayerStatus(playerId, iMap);
      Map<String, Unit> units = newPlayer.getUnitMap();
      for (String s : units.keySet()) {
        Unit u = units.get(s);
        Point p;
        do {
          p = new Point(random.nextInt(GameMap.MAP_H), random.nextInt(GameMap.MAP_W));
        } while (!iMap.setUnitLocation(u, p));
      }
      iActivePlayers.put(playerId, newPlayer);
      iActivePlayerOrder.add(playerId);
    }
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
  
  
  public synchronized boolean isCurrentPlayer(String aPlayer) { return aPlayer == iCurrentPlayer; }
  
  private String getNewGameId() { return UUID.randomUUID().toString(); }
  
  // TODO
  // Get a general status to return for the game/status REST method
  
}
