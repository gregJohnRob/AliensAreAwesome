package api;

import java.util.Map;
import java.awt.Point;
import java.util.HashMap;

/* PlayerStatus - Keeps state of a player in a given game
 * TODO - Write more here...
 */
public class PlayerStatus
{
  private final String iPlayerId;
  private static final int STARTING_UNIT_COUNT = 5;
  
  private final GameMap iMap;
  private final Map<String, Unit> iUnits;
  
  public PlayerStatus(String aPlayerId, GameMap aGameMap)
  {
    iMap = aGameMap;
    iPlayerId = aPlayerId;
    
    iUnits = getNewUnits();  
  }
  
  public synchronized int attack(String aUnit, int aX, int aY)
  {
    Unit unit = iUnits.get(aUnit);
    if (unit == null) {
      return -1;
    }
    synchronized(iMap) {
      Point p = iMap.getUnitLocation(unit);
      int x = new Double(p.getX()).intValue();
      int y = new Double(p.getY()).intValue();
      Unit enemy = iMap.unitAtPoint(aX, aY);
      if (unit.getRange() >= GameMap.distanceBetween(aX, aY, x, y) && unit.getCanAttack()) {
        int damage = unit.attack(enemy);
        if (enemy.isDead()) {
          iMap.removeUnit(enemy);
        }
        return damage;
      }
    }
    return -1;
  }
  
  public synchronized Map<String, Unit> getUnitMap() { return iUnits; }
  
  public synchronized boolean move(String aUnit, int aX, int aY)
  {
    return false;
  }
  
  public synchronized boolean waitOn(String aUnit)
  {
    Unit unit = iUnits.get(aUnit);
    if (unit == null) {
      return false;
    }
    unit.endTurn();
    return true;
  }
  
  
  public synchronized void beginTurn()
  {
    for (String id : iUnits.keySet()) {
      iUnits.get(id).startTurn();
    }
  }
  
  
  private Map<String, Unit> getNewUnits()
  {
    HashMap<String, Unit> newUnits = new HashMap<String, Unit>();
    Unit[] units = UnitFactory.generateNUnits(STARTING_UNIT_COUNT);
    for (int i = 0; i < STARTING_UNIT_COUNT; i++) {
      String unitId = iPlayerId + "_" + String.valueOf(i);
      newUnits.put(unitId, units[i]);
    }
    return newUnits;  
  }
  
  
}
