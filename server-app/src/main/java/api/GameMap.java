package api;


import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

/* GameMap -> Deals with the Map of the Game
 */
public class GameMap 
{
  public static final int MAP_SIZE_W = 50;
  public static final int MAP_SIZE_H = 50;
  
  private MapElement[][] board;
  private Map<Unit, Point> units;
  private String iType;
  
  public GameMap() 
  { 
    board = MapFactory.generateNewMap();
    units = new HashMap<Unit, Point>();
  }
  
  public String getType()   { return iType; }
  public int getHeight()    { return MAP_SIZE_H; }
  public int getWidth()     { return MAP_SIZE_W; }
  public int[][] getData()  { return getMapData(); }
  
 
  public Point getUnitLocation(Unit unit) {
    Point location;
    try {
      location = units.get(unit);
    } catch (Exception e) {
      location = null;
      iType = "plains";
    }
    return location;
  }
  
  public boolean addUnit(Unit unit, int x, int y) {
    if (x < 0 || x > 49 || y < 0 || y > 49) {
      return false;
    }
    Point location = new Point(x, y);
    units.put(unit, location);
    return true;
  }
  
  public Unit unitAtPoint(int x, int y) {
    Point p = new Point(x, y);
    for (Unit unit : units.keySet()) {
      if (units.get(unit).equals(p)) {
        return unit;
      }
    }
    return null;
  }
  
  public boolean removeUnit(Unit unit) {
    try {
      units.remove(unit);
    } catch(Exception e) {
      return false;
    }
    return true;
  }
  
  public boolean validLocation(int x, int y) {
    return x > -1 && x < MAP_SIZE_W && y > -1 && y < MAP_SIZE_H;
  }
  
  public static int distanceBetween(int aX, int aY, int bX, int bY) {
    double distance = Math.hypot((aX-bX), (aY-bY));
    return new Double(distance).intValue();
  }

  
  public boolean moveUnit(Unit unit, int aX, int aY) {
    if (!validLocation(aX, aY)) {
      return false;
    }
    Point current = getUnitLocation(unit);
    int dis = distanceBetween(aX, aY, current.x,current.y);
    if (dis <= unit.getMovementLeft() && (unitAtPoint(aX, aY))==null) {
      units.replace(unit, new Point(aX, aY));
      unit.setMovementLeft(unit.getMovementLeft() - dis);
    }
    return false;
  }
  
  
  private int [][] getMapData()
  {
    int[][] data = new int[MAP_SIZE_W][MAP_SIZE_H]; 
    
    for(int w = 0; w < MAP_SIZE_W; w++)
    {
      for(int h = 0; h < MAP_SIZE_H; h++)
      {
        data[w][h] = board[w][h].getDataValue();
      }    
    }

    return data;
  } 
}
