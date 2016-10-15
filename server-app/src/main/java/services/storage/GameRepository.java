package services.storage;

import java.util.List;
import java.util.ArrayList;

import api.Game;

public class GameRepository implements Repository<Game>
{
  private final List<Game> iGames;
  
  public GameRepository() { iGames = new ArrayList<Game>(); }
  
  
  public synchronized boolean contains(String aId) { return getById(aId) != null; }
  public synchronized boolean contains(Game aGame) { return iGames.contains(aGame);  }
  
  
  public synchronized boolean add(Game aGame)
  {
    if(!contains(aGame))
    {
      iGames.add(aGame);
      return true;
    }
    
    return false;
  }
  
  
  public synchronized boolean remove(String aId)
  {
    Game g = getById(aId);    
    if(g != null) { return remove(g); }
    
    return false;
  }
  
  public synchronized boolean remove(Game aGame) { return iGames.remove(aGame); }
  
  public synchronized List<Game> getContents() { return iGames; }
  
  
  public synchronized List<Game> getGamesForPlayer(String aPlayerId)
  {
    List<Game> games = new ArrayList<Game>();
        
    for(Game g : iGames)
    {
      if (g.getPlayers().contains(aPlayerId)) { games.add(g); }
    }
     
    return games;
  }
  
  
   
  private Game getById(String aId)
  {
    for(Game g : iGames)
    {
     if(g.getId().equals(aId)) { return g; }
    }

    return null;
  }  
  
  
  public static GameRepository instance = getInstance();
  
  private static GameRepository theInstance = null;
  private static GameRepository getInstance()
  {
    if(theInstance == null) { theInstance = new GameRepository(); }  
    return theInstance;
  }
 
}

