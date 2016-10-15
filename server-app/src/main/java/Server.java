import com.google.gson.Gson;

import api.endpoints.*;


public class Server
{
  // TODO -> MAKE THE CORS stuff work for DYLAN.
  // THE NOOB
  // WHO SMELLS
  private final Gson gson;
  
  private final GameEndpoint iGameEndpoint;
  private final TurnEndpoint iTurnEndpoint;
  private final PlayerEndpoint iPlayerEndpoint;
  
  public Server()
  {
    gson = new Gson();
    
    iGameEndpoint = new GameEndpoint(gson);
    iTurnEndpoint = new TurnEndpoint(gson);
    iPlayerEndpoint = new PlayerEndpoint(gson); 
  }
  
  
  public void Run()
  {
    //Do stuff here...
    //Is this really needed?
  }
}
