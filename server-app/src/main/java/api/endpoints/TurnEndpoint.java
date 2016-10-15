package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;

import api.Game;
import api.Message;
import api.responses.PlayerGameResponse;
import api.responses.TurnAttackResponse;
import api.responses.TurnEndResponse;
import api.responses.TurnMineResponse;
import api.responses.TurnMoveResponse;
import api.responses.TurnWaitResponse;
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

  private Message<String> doTurnAttack(Request req, Response res) {
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

  private Message<String> doTurnWait(Request req, Response res) {
    QueryParamsMap query = req.queryMap();
    
    String clientId = query.get("clientId").value();
    if (clientId == null || clientId == "") { 
      return TurnWaitResponse.NoClientId; 
    }
    if(!PlayerRepository.instance.contains(clientId)) { 
      return TurnWaitResponse.InvalidClientId; 
    }
    
    String gameId = query.get("gameId").value();
    if (gameId == null || gameId == "") { 
      return TurnWaitResponse.NoGameId; 
    }
    if (!GameRepository.instance.contains(gameId)) {
      return TurnWaitResponse.InvalidGameId;
    }
    
    String unitId = query.get("unitId").value();
    if (unitId == null || unitId == "") {
      return TurnWaitResponse.NoUnitId;
    }
    Game game = GameRepository.instance.getById(gameId);
    if (game.DoWait(clientId, unitId)) {
      return TurnWaitResponse.Success(unitId);
    }
    return TurnWaitResponse.InvalidWait;
  }

  private Message<String> doTurnMove(Request req, Response res) {
    QueryParamsMap query = req.queryMap();
    
    String clientId = query.get("clientId").value();
    if (clientId == null || clientId == "") { 
      return TurnMoveResponse.NoClientId; 
    }
    if(!PlayerRepository.instance.contains(clientId)) { 
      return TurnMoveResponse.InvalidClientId; 
    }
    
    String gameId = query.get("gameId").value();
    if (gameId == null || gameId == "") { 
      return TurnMoveResponse.NoGameId; 
    }
    if (!GameRepository.instance.contains(gameId)) {
      return TurnMoveResponse.InvalidGameId;
    }
    
    String unitId = query.get("unitId").value();
    if (unitId == null || unitId == "") {
      return TurnMoveResponse.NoUnitId;
    }
    
    String sx = query.get("x").value();
    String sy = query.get("y").value();
    int x = 0;
    int y = 0;
    try {
      x = Integer.parseInt(sx);
      y = Integer.parseInt(sy);
    } catch (Exception e) {
      return TurnMoveResponse.InvalidLocation;
    }
    
    Game game = GameRepository.instance.getById(gameId);
    if (game.DoMove(clientId, unitId, x, y)) {
      return TurnMoveResponse.Success();
    }
    return TurnMoveResponse.InvalidMove;
  }

  private Message<String> doTurnEnd(Request req, Response res) {
    QueryParamsMap query = req.queryMap();
    
    String clientId = query.get("clientId").value();
    if (clientId == null || clientId == "") { 
      return TurnEndResponse.NoClientId; 
    }
    if(!PlayerRepository.instance.contains(clientId)) { 
      return TurnEndResponse.InvalidClientId; 
    }
    
    String gameId = query.get("gameId").value();
    if (gameId == null || gameId == "") { 
      return TurnEndResponse.NoGameId; 
    }
    if (!GameRepository.instance.contains(gameId)) {
      return TurnEndResponse.InvalidGameId;
    }
    
    Game game = GameRepository.instance.getById(gameId); 
    boolean success = game.DoEnd(clientId);
    if (success) {
      return TurnEndResponse.Success();
    }
    return TurnEndResponse.Unknown;
  }

  private Message<String> doTurnMine(Request req, Response res) {
    QueryParamsMap query = req.queryMap();
    
    String clientId = query.get("clientId").value();
    if (clientId == null || clientId == "") { 
      return TurnMineResponse.NoClientId; 
    }
    if(!PlayerRepository.instance.contains(clientId)) { 
      return TurnMineResponse.InvalidClientId; 
    }
    
    String gameId = query.get("gameId").value();
    if (gameId == null || gameId == "") { 
      return TurnMineResponse.NoGameId; 
    }
    if (!GameRepository.instance.contains(gameId)) {
      return TurnMineResponse.InvalidGameId;
    }
    
    Game game = GameRepository.instance.getById(gameId); 
    return TurnMineResponse.Success(game.isCurrentPlayer(clientId));
  }

}
