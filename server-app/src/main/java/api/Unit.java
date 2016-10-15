package api;

import java.util.Random;

/* Unit - Defines information about a single unit in the game.
 * 
 */
public abstract class Unit
{
  
  private static Random gen = new Random();
  
  public static final double ADVANTAGE_MOD = 1.5;
  public static final double DISADVANTAGE_MOD = 0.75;
  
  private int range; // How many squares away can a unit attack
  private int damage; // How much damage does a unit do
  private int health; // How much health does a unit have
  private int movement; // How much movement does a unit have (not the same as number of squares they can move)
  private int[] location; // current location on the map
  private int movementLeft; // how many squares the unit can still move this turn
  private boolean canAttack; // can the unit still attack this turn
  
  protected Unit(int range, int damage, int health, int movement) {
    this.range    = range;
    this.damage   = damage;
    this.health   = health;
    this.movement = movement;
    this.location = new int[2];
    this.location[0] = 0;
    this.location[1] = 0;
    this.canAttack = false;
    this.movementLeft = 0;
  }
  
  public int getRange() { return range; }
  public int getDamage() { return damage; }
  public int getHealth() { return health; }
  public void setHealth(int health) { this.health = health; }
  public int getMovement() { return movement; }
  public void setXLocation(int x) { this.location[0] = x; }
  public void setYLocation(int y) { this.location[1] = y; }
  public int getMovementLeft() { return this.movement; }
  public void setMovementLeft(int left) { this.movementLeft = left; }
  public boolean getCanAttack() { return this.canAttack; }
  public void setCanAttack(boolean can) { this.canAttack = can; }
  
  /**
   * Deals damage to an enemy based on modifiers and returns true if the enemy was killed 
   * @param enemy
   * @return
   */
  public abstract boolean attack(Unit enemy);
  
  public boolean isDead() {
    return health <= 0;
  }
  
  public void endTurn() {
    this.movementLeft = 0;
    this.canAttack = false;
  }
  
  public void startTurn() {
    this.movementLeft = this.movement;
    this.canAttack = true;
  }

}
