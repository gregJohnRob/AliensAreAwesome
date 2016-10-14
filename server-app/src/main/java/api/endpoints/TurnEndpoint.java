package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;


public class TurnEndpoint 
{
  private static final String ENDPOINT_BASE_STR = "turn";
  
  public TurnEndpoint(Gson aGson)
  {
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "mine"),   (req, res) -> doTurnMine(req, res), aGson::toJson);
    
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "end"),    (req, res) -> doTurnEnd(req, res), aGson::toJson);
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "move"),   (req, res) -> doTurnMove(req, res), aGson::toJson);
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "wait"),   (req, res) -> doTurnWait(req, res), aGson::toJson);
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "attack"), (req, res) -> doTurnAttack(req, res), aGson::toJson);
  }

  
  private Object doTurnAttack(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
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
