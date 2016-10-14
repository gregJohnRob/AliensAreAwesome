package api.endpoints;

import static spark.Spark.*;

import spark.*;
import com.google.gson.Gson;


public class PlayerEndpoint 
{
  private static final String ENDPOINT_BASE_STR = "player";
  

  public PlayerEndpoint(Gson aGson)
  { 
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "game"),  (req, res) -> doPlayerGame(req, res), aGson::toJson);
    
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "login"), (req, res) -> doPlayerLogin(req, res), aGson::toJson);  
  }

  private Object doPlayerLogin(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  private Object doPlayerGame(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  
}
