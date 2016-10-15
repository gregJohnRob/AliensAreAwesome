package api;

public class Horse extends Unit {
  
  public Horse(int range, int damage, int health, int movement) {
    super(range, damage, health, movement);
  }
  
  @Override
  public int attack(Unit enemy) {
    int damage = 0;
    if (enemy instanceof Sword) {
      damage = new Double(this.getDamage() * Unit.ADVANTAGE_MOD).intValue();
    } else if (enemy instanceof Spear) {
      damage = new Double(this.getDamage() * Unit.DISADVANTAGE_MOD).intValue();
    }
    enemy.setHealth(enemy.getHealth() - damage);
    this.setCanAttack(false);
    return damage;
  }
}
