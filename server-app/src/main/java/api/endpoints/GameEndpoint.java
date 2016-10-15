package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;

import api.Message;
import api.responses.GameListResponse;
import services.storage.GameRepository;


public class GameEndpoint
{
  private static final String ENDPOINT_BASE_STR = "game";
  
  public GameEndpoint(Gson aGson)
  {
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "list"),    (req, res) -> doGameList(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "view"),    (req, res) -> doGameView(req, res), aGson::toJson);
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "status"),  (req, res) -> doGameStatus(req, res), aGson::toJson);
    
    get(String.format("%s/%s",  ENDPOINT_BASE_STR, "join"),   (req, res) -> doGameJoin(req, res), aGson::toJson);
    get(String.format("%s/%s",  ENDPOINT_BASE_STR, "leave"),  (req, res) -> doGameLeave(req, res), aGson::toJson);
    get(String.format("%s/%s",  ENDPOINT_BASE_STR, "create"), (req, res) -> doGameCreate(req, res), aGson::toJson);
    
  }

  private Object doGameCreate(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
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

  private Object doGameView(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }
 
  private Message<GameListResponse.Payload> doGameList(Request req, Response res) { return GameListResponse.Success(GameRepository.instance.getContents()); }
  

}
