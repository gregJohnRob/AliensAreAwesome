package api;

/*
 * MapaElement -> Deals with the elements of a map.
 */
public abstract class MapElement {

  private String name;
  private int movementCost;
  
  protected MapElement(String name, int movementCost) {
    this.name = name;
    this.movementCost = movementCost;
  }
  
  public String getName() {
    return name;
  }

  public int getMovementCost() {
    return movementCost;
  }
  
  public int getDataValue()
  {
    switch(name)
    {
      case "plains":    return 1;
      case "stone":     return 2;
      case "wheat":     return 3;
      case "dirt":      return 4;
      default:          return 0;
    }
  }

}
