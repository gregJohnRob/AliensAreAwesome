package api.endpoints;

import static spark.Spark.*;

import api.*;
import spark.*;
import api.Message;
import api.responses.PlayerLoginResponse;

import services.storage.*;
import com.google.gson.Gson;


public class PlayerEndpoint 
{
  private static final String ENDPOINT_BASE_STR = "player";
  
  private final PlayerRepository iPlayerRepo;
  
 
  public PlayerEndpoint(PlayerRepository aPlayerRepo, Gson aGson)
  { 
    iPlayerRepo = aPlayerRepo;
    
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "game"),  (req, res) -> doPlayerGame(req, res), aGson::toJson);
    
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "login"), (req, res) -> doPlayerLogin(req, res), aGson::toJson);  
  }

  private Message<String> doPlayerLogin(Request req, Response res)
  {
    QueryParamsMap query = req.queryMap();

    String username = query.get("username").value();
    
    if(username == null || username == "") { return PlayerLoginResponse.NoUsername(); }
    
    if(!iPlayerRepo.containsName(username))
    {
      Player p = new Player(username);
      
      if(iPlayerRepo.add(p))
      {
        System.out.printf("Adding a player with the username '%s'\n", username);
        return PlayerLoginResponse.Success(p.getId());
      }     
    }
    else { return PlayerLoginResponse.DuplicateUsername(); }
    
    return PlayerLoginResponse.Unkown();
  }

  private Object doPlayerGame(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  
}
