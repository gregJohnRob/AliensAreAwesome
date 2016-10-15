package api;

import java.util.UUID;
import java.util.List;
import java.util.LinkedList;

/* Player - Defines a player in the system
 * 
 */
public class Player
{
  private final String iId;
  private final String iName;
  private final LinkedList<String> iActiveGames;
  
  public Player(String aName)
  {
    iId = getNewPlayerId();
    iName = aName; 
    iActiveGames = new LinkedList<String>();   
  }
  
  public String getId()   { return iId; }
  public String getName() { return iName; }
  
  
  public synchronized List<String> getActiveGames() { return iActiveGames; }
  
  
  public synchronized boolean addGame(String aGameId)
  {
    if(!iActiveGames.contains(aGameId)) 
    {
      iActiveGames.add(aGameId);
      return true;
    }
    
    return false;  
  }
  
  public synchronized boolean removeGame(String aGameId)
  {
    if(iActiveGames.contains(aGameId))
    {
      iActiveGames.remove(aGameId);
      return true;
    }
    
    return false;
  }
   
  private String getNewPlayerId() { return UUID.randomUUID().toString(); }
}
