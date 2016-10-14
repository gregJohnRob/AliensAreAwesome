package api.endpoints;

import static spark.Spark.*;

import spark.*;
import api.Message;
import java.util.List;
import java.util.LinkedList;
import com.google.gson.Gson;


public class PlayerEndpoint 
{
  private static final String ENDPOINT_BASE_STR = "player";
  

  public PlayerEndpoint(Gson aGson)
  { 
    get (String.format("%s/%s", ENDPOINT_BASE_STR, "game"),  (req, res) -> doPlayerGame(req, res), aGson::toJson);
    
    post(String.format("%s/%s", ENDPOINT_BASE_STR, "login"), (req, res) -> doPlayerLogin(req, res), aGson::toJson);  
  }

  private Message<List<String>> doPlayerLogin(Request req, Response res) {
    Message<List<String>> ms = new Message<List<String>>();
    
    ms.Message = "";
    ms.Data = new LinkedList<String>();
    
    ms.Data.add("Hello");
    ms.Data.add("World");
    
    return ms;
  }

  private Object doPlayerGame(Request req, Response res) {
    // TODO Auto-generated method stub
    return null;
  }

  
}
