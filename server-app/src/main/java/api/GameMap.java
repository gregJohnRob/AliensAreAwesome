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
  
  public GameMap() {
    board = MapFactory.generateNewMap();
    units = new HashMap<Unit, Point>();
  }
  
  public Point getUnitLocation(Unit unit) {
    Point location;
    try {
      location = units.get(unit);
    } catch (Exception e) {
      location = null;
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
  
  public static int distanceBetween(int aX, int aY, int bX, int bY) {
    double distance = Math.hypot((aX-bX), (aY-bY));
    return new Double(distance).intValue();
  }
  
}
