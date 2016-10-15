package api;

import java.util.Map;
import java.util.HashMap;

/* PlayerStatus - Keeps state of a player in a given game
 * TODO - Write more here...
 */
public class PlayerStatus
{
  private final String iPlayerId;
  
  private final GameMap iMap;
  private final Map<String, Unit> iUnits;
  
  public PlayerStatus(String aPlayerId, GameMap aGameMap)
  {
    iMap = aGameMap;
    iPlayerId = aPlayerId;
    
    iUnits = getNewUnits();  
  }
  
  public synchronized boolean attack(String aUnit, int aX, int aY)
  {
    return false;
  }
  
  public synchronized boolean move(String aUnit, int aX, int aY)
  {
    return false;
  }
  
  public synchronized boolean waitOn(String aUnit)
  {
    return false;
  }
  
  
  public synchronized void beginTurn()
  {
    // TODO 
  }
  
  
  private Map<String, Unit> getNewUnits()
  {
    return new HashMap<String, Unit>();  
  }
  
  
}
