package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;

import api.Game;
import api.Message;
import api.responses.GameCreateResponse;
import api.responses.GameListResponse;
import api.responses.PlayerGameResponse;
import services.storage.GameRepository;
import services.storage.PlayerRepository;


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

  private Object doGameLeave(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  private Object doGameJoin(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  private Object doGameStatus(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

 
  private Message<GameListResponse.Payload> doGameList(Request req, Response res) { return GameListResponse.Success(GameRepository.instance.getContents()); }
  

}
