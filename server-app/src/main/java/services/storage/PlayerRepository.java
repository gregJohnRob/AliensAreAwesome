package services.storage;

import java.util.List;
import java.util.ArrayList;

import api.Player;

public class PlayerRepository implements Repository<Player>
{
  private final List<Player> iPlayers;
  
  public PlayerRepository() { iPlayers = new ArrayList<Player>(); }
  
  
  public boolean contains(String aId)       { return getById(aId) != null; }
  public boolean contains(Player aPlayer)   { return iPlayers.contains(aPlayer); }  
  
    
  public boolean add(Player aPlayer)
  {
    if(!contains(aPlayer))
    {
      iPlayers.add(aPlayer);
      return true;
    }
    
    return false;
  }
  
  
  public boolean remove(String aId)
  {
    Player p = getById(aId);
    
    if(p != null) { return remove(p); }
    
    return false;
  }
  
  public boolean remove(Player aPlayer) { return iPlayers.remove(aPlayer); }
  
  
  public boolean containsName(String aName)
  {
    for(Player p : iPlayers)
    {
      if(p.getName().equals(aName)) { return true; }
    }
    
    return false;
  }
  
  
  public List<Player> getContents() { return iPlayers; }
  
  
  private Player getById(String aId)
  {
    for(Player p : iPlayers)
    {
      if(p.getId().equals(aId)) { return p; }
    }
    
    return null;
  }
 
}

