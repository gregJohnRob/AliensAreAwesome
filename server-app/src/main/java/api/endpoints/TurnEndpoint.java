package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;

import api.Game;
import api.responses.PlayerGameResponse;
import api.responses.TurnAttackResponse;
import services.storage.GameRepository;
import services.storage.PlayerRepository;


public class TurnEndpoint 
{
  private static final String ENDPOINT_BASE_STR = "turn";
  
  public TurnEndpoint(Gson aGson)
  {
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "mine"),   (req, res) -> doTurnMine(req, res), aGson::toJson);   
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "end"),    (req, res) -> doTurnEnd(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "move"),   (req, res) -> doTurnMove(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "wait"),   (req, res) -> doTurnWait(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "attack"), (req, res) -> doTurnAttack(req, res), aGson::toJson);
  }

  /*
   * QueryParamsMap query = req.queryMap();
    
    String userId = query.get("clientId").value();
    
    if(userId == null || userId == "")              { return PlayerGameResponse.NoClientId; }  
    if(!PlayerRepository.instance.contains(userId)) { return PlayerGameResponse.InvalidClientId; }
    
    return PlayerGameResponse.Success(GameRepository.instance.getGamesForPlayer(userId));
   */
  private Object doTurnAttack(Request req, Response res) {
    QueryParamsMap query = req.queryMap();
    
    String clientId = query.get("clientId").value();
    if (clientId == null || clientId == "") { 
      return TurnAttackResponse.NoClientId; 
    }
    if(!PlayerRepository.instance.contains(clientId)) { 
      return TurnAttackResponse.InvalidClientId; 
    }
    
    String gameId = query.get("gameId").value();
    if (gameId == null || gameId == "") { 
      return TurnAttackResponse.NoGameId; 
    }
    if (!GameRepository.instance.contains(gameId)) {
      return TurnAttackResponse.InvalidGameId;
    }
    
    String unitId = query.get("unitId").value();
    if (unitId == null || unitId == "") {
      return TurnAttackResponse.NoUnitId;
    }
    
    String sx = query.get("x").value();
    String sy = query.get("y").value();
    int x = 0;
    int y = 0;
    try {
      x = Integer.parseInt(sx);
      y = Integer.parseInt(sy);
    } catch (Exception e) {
      return TurnAttackResponse.InvalidLocation;
    }
    
    
    Game game = GameRepository.instance.getById(gameId);   
    int damage = game.DoAttack(gameId, unitId, x, y);
    if (damage == -1) {
      return TurnAttackResponse.InvalidAttack;
    }
    return TurnAttackResponse.Success(damage);
  }

  private Object doTurnWait(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  private Object doTurnMove(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  private Object doTurnEnd(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  private Object doTurnMine(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

}
