package api.endpoints;

import static spark.Spark.*;

import api.*;
import spark.*;
import api.Message;
import api.responses.PlayerLoginResponse;
import api.responses.PlayerGameResponse;

import services.storage.*;
import com.google.gson.Gson;


public class PlayerEndpoint 
{
  private static final String ENDPOINT_BASE_STR = "player";
  
  public PlayerEndpoint(Gson aGson)
  {   
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "game"),  (req, res) -> doPlayerGame(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "login"), (req, res) -> doPlayerLogin(req, res), aGson::toJson);  
  }

  private Message<String> doPlayerLogin(Request req, Response res)
  {
    QueryParamsMap query = req.queryMap();

    String username = query.get("username").value();
    
    if(username == null || username == "") { return PlayerLoginResponse.NoUsername; }
    
    if(!PlayerRepository.instance.containsName(username))
    {
      Player p = new Player(username);
      
      if(PlayerRepository.instance.add(p))
      {
        System.out.printf("Adding a player with the username '%s'\n", username);
        return PlayerLoginResponse.Success(p.getId());
      }     
    }
    else 
    {
      Player p = PlayerRepository.instance.getByName(username);
      return PlayerLoginResponse.Success(p.getId());
    }
    
    return PlayerLoginResponse.Unkown;
  }

  private Message<PlayerGameResponse.Payload> doPlayerGame(Request req, Response res)
  {
    QueryParamsMap query = req.queryMap();
    
    String userId = query.get("clientId").value();
    
    if(userId == null || userId == "")              { return PlayerGameResponse.NoClientId; }  
    if(!PlayerRepository.instance.contains(userId)) { return PlayerGameResponse.InvalidClientId; }
    
    return PlayerGameResponse.Success(GameRepository.instance.getGamesForPlayer(userId));
  }

  
}
