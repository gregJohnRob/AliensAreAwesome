package api;

import java.util.Random;

public class Sword extends Unit {
  
  public Sword(int range, int damage, int health, int movement) {
    super(range, damage, health, movement);
  }
  
  @Override
  public int attack(Unit enemy) {
    int damage = 0;
    if (enemy instanceof Spear) {
      damage = new Double(this.getDamage() * Unit.ADVANTAGE_MOD).intValue();
    } else if (enemy instanceof Horse) {
      damage = new Double(this.getDamage() * Unit.DISADVANTAGE_MOD).intValue();
    }
    enemy.setHealth(enemy.getHealth() - damage);
    this.setCanAttack(false);
    return damage;
  }
}
