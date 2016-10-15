package api;

import java.util.Random;

/* Unit - Defines information about a single unit in the game.
 * 
 */
public abstract class Unit
{
  public static final String[] TYPES = {"sword", "spear", "horse"};
  private static Random gen = new Random();
  
  private int range; // How many squares away can a unit attack
  private int damage; // How much damage does a unit do
  private int health; // How much health does a unit have
  private int movement; // How much movement does a unit have (not the same as number of squares they can move)
  
  protected Unit(int range, int damage, int health, int movement) {
    this.range    = range;
    this.damage   = damage;
    this.health   = health;
    this.movement = movement;
  }
  
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
  
  private static Unit generateUnit(String seed) {
    switch (seed) {
    case "sword":
      return new Sword(0,0,0,0);
    case "spear":
      return new Spear(0,0,0,0);
    case "horse":
      return new Horse(0,0,0,0);
    default: 
      return null;
    }
  }

}
