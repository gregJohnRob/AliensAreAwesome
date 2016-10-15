package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;

import api.Game;
import api.Message;
import api.responses.*;
import services.storage.*;


public class GameEndpoint
{
  private static final String ENDPOINT_BASE_STR = "game";
  
  public GameEndpoint(Gson aGson)
  {
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "list"),   (req, res) -> doGameList(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "status"), (req, res) -> doGameStatus(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "join"),   (req, res) -> doGameJoin(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "leave"),  (req, res) -> doGameLeave(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "create"), (req, res) -> doGameCreate(req, res), aGson::toJson);
    
  }

  private Message<GameCreateResponse.Payload> doGameCreate(Request req, Response res)
  {
    QueryParamsMap query = req.queryMap();
    
    String userId = query.get("clientId").value();
    
    if(userId == null || userId == "")              { return GameCreateResponse.NoClientId; }  
    if(!PlayerRepository.instance.contains(userId)) { return GameCreateResponse.InvalidClientId; }
    
    Game g = new Game();
    g.addPlayer(userId);
    
    GameRepository.instance.add(g);
    
    System.out.println(String.format("Game created with Id: %s", g.getId()));
    
    return GameCreateResponse.Success(g);
  }

  private Message<Boolean> doGameLeave(Request req, Response res)
  {
    QueryParamsMap query = req.queryMap();
    
    String userId = query.get("clientId").value();
    String gameId = query.get("gameId").value();
    
    if(userId == null || userId == "")              { return GameLeaveResponse.NoClientId; }  
    if(!PlayerRepository.instance.contains(userId)) { return GameLeaveResponse.InvalidClientId; }
    
    if(gameId == null || gameId == "")            { return GameLeaveResponse.NoGameId; }  
    if(!GameRepository.instance.contains(gameId)) { return GameLeaveResponse.InvalidGameId; }
   
    Game g = GameRepository.instance.getById(gameId);

    if(g.hasPlayer(userId))
    {
      g.removePlayer(userId);
      return GameLeaveResponse.Success;
    }
    
    return GameLeaveResponse.PlayerNotInGame;
  }

  private Message<Boolean> doGameJoin(Request req, Response res) 
  {
    QueryParamsMap query = req.queryMap();
    
    String userId = query.get("clientId").value();
    String gameId = query.get("gameId").value();
    
    if(userId == null || userId == "")              { return GameJoinResponse.NoClientId; }  
    if(!PlayerRepository.instance.contains(userId)) { return GameJoinResponse.InvalidClientId; }
    
    if(gameId == null || gameId == "")              { return GameJoinResponse.NoGameId; }  
    if(!GameRepository.instance.contains(gameId))   { return GameJoinResponse.InvalidGameId; }
   
    Game g = GameRepository.instance.getById(gameId);

    if(!g.hasPlayer(userId))
    {
      g.addPlayer(userId);
      return GameJoinResponse.Success;
    }
    
    return GameJoinResponse.PlayerAlreadyInGame;

  }

  private Object doGameStatus(Request req, Response res) 
  {
    QueryParamsMap query = req.queryMap();
    
    String userId = query.get("clientId").value();
    String gameId = query.get("gameId").value();
    
    if(userId == null || userId == "")              { return GameStatusResponse.NoClientId; }  
    if(!PlayerRepository.instance.contains(userId)) { return GameStatusResponse.InvalidClientId; }
    
    if(gameId == null || gameId == "")              { return GameStatusResponse.NoGameId; }  
    if(!GameRepository.instance.contains(gameId))   { return GameStatusResponse.InvalidGameId; }
   
    Game g = GameRepository.instance.getById(gameId);

    if(!g.hasPlayer(userId))                        { return GameStatusResponse.PlayerNotInGame; }
       
    return GameStatusResponse.Success(g);
  }

 
  private Message<GameListResponse.Payload> doGameList(Request req, Response res) { return GameListResponse.Success(GameRepository.instance.getContents()); }
  

}
