package api;

import java.util.Random;

/* Unit - Defines information about a single unit in the game.
 * 
 */
public abstract class Unit
{
  public static final String[] TYPES = {"sword", "spear", "horse"};
  private static Random gen = new Random();
  
  public static final double ADVANTAGE_MOD = 1.5;
  public static final double DISADVANTAGE_MOD = 0.75;
  
  private int range; // How many squares away can a unit attack
  private int damage; // How much damage does a unit do
  private int health; // How much health does a unit have
  private int movement; // How much movement does a unit have (not the same as number of squares they can move)
  private int[] location; // current location on the map
  
  protected Unit(int range, int damage, int health, int movement) {
    this.range    = range;
    this.damage   = damage;
    this.health   = health;
    this.movement = movement;
    this.location = new int[2];
    this.location[0] = 0;
    this.location[1] = 0;
  }
  
  public static String[] getTypes() { return TYPES; }
  public int getRange() { return range; }
  public int getDamage() { return damage; }
  public int getHealth() { return health; }
  public void setHealth(int health) { this.health = health; }
  public int getMovement() { return movement; }
  public void setXLocation(int x) { this.location[0] = x; }
  public void setYLocation(int y) { this.location[1] = y; }
  
  /**
   * Generates a number of random units
   * @param number
   * @return
   */
  public static Unit[] generateUnits(int number) {
    Unit[] newUnits = new Unit[number];
    int i = 0;
    if (TYPES.length < number) {
      while (i < TYPES.length) {
        newUnits[i] = generateUnit(TYPES[i]);
        i++;
      }
    }
    while (i < number) {
      String seed = TYPES[gen.nextInt(TYPES.length)];
      newUnits[i] = generateUnit(seed);
      i++;
    }
    return newUnits;
  }
  
  /**
   * Takes in a type of unit and randomly generates a unit of that type.
   * @param seed
   * @return
   */
  private static Unit generateUnit(String seed) {
    int range;
    int damage;
    int health;
    int movement;
    switch (seed) {
    case "sword":
      range = 1;
      damage = gen.nextInt(7) + 4;
      health = gen.nextInt(13) + 8;
      movement = 2; 
      return new Sword(range, damage, health, movement);
    case "spear":
      range = 1;
      damage = gen.nextInt(7) + 4;
      health = gen.nextInt(13) + 8;
      movement = 2;
      return new Spear(range, damage, health, movement);
    case "horse":
      range = 1;
      damage = gen.nextInt(7) + 4;
      health = gen.nextInt(13) + 8;
      movement = 6;
      return new Horse(range, damage, health, movement);
    default: 
      return null;
    }
  }
  
  /**
   * Deals damage to an enemy based on modifiers and returns true if the enemy was killed 
   * @param enemy
   * @return
   */
  public abstract boolean attack(Unit enemy);
  
  public boolean isDead() {
    return health <= 0;
  }

}
