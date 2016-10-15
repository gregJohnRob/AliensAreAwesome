package api;

import java.util.Random;

public class UnitFactory {
  
  public static final String[] UNIT_TYPES = {"sword", "spear", "horse"};
  private static Random random = new Random();
  
  public static Unit[] generateNUnits(int n) {
    if (n <= 0) {
      return null;
    }
    int i = 0;
    Unit[] units = new Unit[n];
    if (n >= UNIT_TYPES.length) {
      while (i < UNIT_TYPES.length) {
        units[i] = generateUnit(UNIT_TYPES[i]);
        i++;
      }
    }
    while (i < n) {
      String type = UNIT_TYPES[random.nextInt(UNIT_TYPES.length)];
      units[i] = generateUnit(type);
      i++;
    }
    return units;
  }
  
  public static Unit generateUnit(String type) {
    int range;
    int damage;
    int health;
    int movement;
    if (type == null) {
      return null;
    }
    switch (type) {
    case "sword":
      range = 1;
      damage = random.nextInt(7) + 4;
      health = random.nextInt(13) + 8;
      movement = 2; 
      return new Sword(range, damage, health, movement);
    case "spear":
      range = 1;
      damage = random.nextInt(7) + 4;
      health = random.nextInt(13) + 8;
      movement = 2;
      return new Spear(range, damage, health, movement);
    case "horse":
      range = 1;
      damage = random.nextInt(7) + 4;
      health = random.nextInt(13) + 8;
      movement = 6;
      return new Horse(range, damage, health, movement);
    default:
      return null;
    }
  }

}
