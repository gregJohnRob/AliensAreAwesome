import com.google.gson.Gson;

import api.endpoints.*;
import services.storage.*;


public class Server
{
  // TODO -> MAKE THE CORS stuff work for DYLAN.
  // THE NOOB
  // WHO SMELLS
  private final Gson gson;
  
  private final GameRepository iGameRepo;
  private final PlayerRepository iPlayerRepo;

  
  private final GameEndpoint iGameEndpoint;
  private final TurnEndpoint iTurnEndpoint;
  private final PlayerEndpoint iPlayerEndpoint;
  
  public Server()
  {
    gson = new Gson();
    
    iGameRepo = new GameRepository();
    iPlayerRepo = new PlayerRepository();
    
    iGameEndpoint = new GameEndpoint(gson);
    iTurnEndpoint = new TurnEndpoint(gson);
    iPlayerEndpoint = new PlayerEndpoint(iPlayerRepo, gson); 
  }
  
  
  public void Run()
  {
    //Do stuff here...
    //Is this really needed?
  }
}
