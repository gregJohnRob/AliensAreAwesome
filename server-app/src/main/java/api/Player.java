package api;

import java.util.List;
import java.util.LinkedList;

public class Player
{
  private final String iName;
  private final LinkedList<String> iActiveGames;
  
  public Player(String aName)
  {
    iName = aName; 
    iActiveGames = new LinkedList<String>();   
  }
  
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

}
