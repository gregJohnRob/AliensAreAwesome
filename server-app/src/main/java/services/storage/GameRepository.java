package services.storage;

import java.util.List;
import java.util.ArrayList;

import api.Game;

public class GameRepository implements Repository<Game>
{
  private final List<Game> iGames;
  
  public GameRepository() { iGames = new ArrayList<Game>(); }
  
  
  public boolean contains(String aId) { return getById(aId) != null; }
  public boolean contains(Game aGame) { return iGames.contains(aGame);  }
  
  
  public boolean add(Game aGame)
  {
    if(!contains(aGame))
    {
      iGames.add(aGame);
      return true;
    }
    
    return false;
  }
  
  
  public boolean remove(String aId)
  {
    Game g = getById(aId);    
    if(g != null) { return remove(g); }
    
    return false;
  }
  
  public boolean remove(Game aGame) { return iGames.remove(aGame); }
  
  public List<Game> getContents() { return iGames; }
  
  
  private Game getById(String aId)
  {
    for(Game g : iGames)
    {
     if(g.getId() == aId) { return g; }
    }

    return null;
  }
  
}

