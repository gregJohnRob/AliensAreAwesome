package api;

public class Spear extends Unit {
  
  public Spear(int range, int damage, int health, int movement) {
    super(range, damage, health, movement);
  }
  
  @Override
  public boolean attack(Unit enemy) {
    int damage = 0;
    if (enemy instanceof Horse) {
      damage = new Double(this.getDamage() * Unit.ADVANTAGE_MOD).intValue();
    } else if (enemy instanceof Spear) {
      damage = new Double(this.getDamage() * Unit.DISADVANTAGE_MOD).intValue();
    }
    enemy.setHealth(enemy.getHealth() - damage);
    this.setCanAttack(false);
    return enemy.isDead();
  }

}
