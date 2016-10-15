import com.google.gson.Gson;

import api.endpoints.*;
import services.storage.*;

import static spark.Spark.*;


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
    
    enableCORS();
    
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
  
  
  //Enables CORS on requests. This method is an initialization method and should be called once.
  private void enableCORS() 
  {
     options("/*", (request, response) -> {
  
       String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
       if (accessControlRequestHeaders != null) {
           response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
       }
  
       String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
       if (accessControlRequestMethod != null) {
           response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
       }
  
       return "OK";
   });
  
   before((request, response) -> {
       response.header("Access-Control-Allow-Origin", "*");
       response.header("Access-Control-Request-Method", "GET, POST, DELETE");
       response.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Accept");
       // Note: this may or may not be necessary in your particular application
       response.type("application/json");
     });
  }
}
