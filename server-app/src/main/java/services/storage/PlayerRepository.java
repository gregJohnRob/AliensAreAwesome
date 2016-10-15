package services.storage;

import java.util.List;
import java.util.ArrayList;

import api.Player;

public class PlayerRepository implements Repository<Player>
{
  private final List<Player> iPlayers;
  
  public PlayerRepository() { iPlayers = new ArrayList<Player>(); }
  
  
  public synchronized boolean contains(String aId)       { return getById(aId) != null; }
  public synchronized boolean contains(Player aPlayer)   { return iPlayers.contains(aPlayer); }  
  
    
  public synchronized boolean add(Player aPlayer)
  {
    if(!contains(aPlayer))
    {
      iPlayers.add(aPlayer);
      return true;
    }
    
    return false;
  }
  
  
  public synchronized boolean remove(String aId)
  {
    Player p = getById(aId);
    
    if(p != null) { return remove(p); }
    
    return false;
  }
  
  public synchronized boolean remove(Player aPlayer) { return iPlayers.remove(aPlayer); }
  
  
  public synchronized boolean containsName(String aName)
  {
    for(Player p : iPlayers)
    {
      if(p.getName().equals(aName)) { return true; }
    }
    
    return false;
  }
  
  
  public synchronized Player getByName(String aName)
  {
    for(Player p : iPlayers)
    {
      if(p.getName().equals(aName)) { return p; }
    }
    
    return null;
  }
  
  
  public synchronized List<Player> getContents() { return iPlayers; }
  
  
  private Player getById(String aId)
  {
    for(Player p : iPlayers)
    {
      if(p.getId().equals(aId)) { return p; }
    }
    
    return null;
  }
  
  public static PlayerRepository instance = getInstance();
  
  private static PlayerRepository theInstance = null;
  private static PlayerRepository getInstance()
  {
    if(theInstance == null) { theInstance = new PlayerRepository(); }  
    return theInstance;
  }
 
}

