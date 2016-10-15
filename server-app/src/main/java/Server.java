import com.google.gson.Gson;

import api.Game;
import api.Player;
import api.endpoints.*;
import services.storage.*;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;


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
    
    enableCORS();
    
    //DEBUG
    makeRandomData();
    

    iGameEndpoint = new GameEndpoint(gson);
    iTurnEndpoint = new TurnEndpoint(gson);
    iPlayerEndpoint = new PlayerEndpoint(gson); 
  }
  
  
  public void Run()
  {
    //Do stuff here...
    //Is this really needed?
  }
  
  
  //Enables CORS on requests. This method is an initialization method and should be called once.
  private void enableCORS() 
  {
     options("/*", (request, response) -> 
     { 
       String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
       if (accessControlRequestHeaders != null) { response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);  }
  
       String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
       if (accessControlRequestMethod != null) { response.header("Access-Control-Allow-Methods", accessControlRequestMethod); }
  
       return "OK";
   });
  
   before((request, response) -> 
   {
       response.header("Access-Control-Allow-Origin", "*");
       response.header("Access-Control-Request-Method", "GET");
       response.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Accept");
       response.type("application/json");
     });
  }
  
  
  private void makeRandomData()
  {
    PlayerRepository.instance.add(new Player("Ewan"));  
    String myId = PlayerRepository.instance.getContents().get(0).getId();
    
    List<String> players = new ArrayList<String>();
    players.add(myId);
    
    System.out.println(myId);
    
    GameRepository.instance.add(new Game(players));   
    GameRepository.instance.add(new Game(new ArrayList<String>()));
  }
  
  
  
}
