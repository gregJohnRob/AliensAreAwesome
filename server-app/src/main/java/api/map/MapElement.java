package api.map;

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
      
      
      default:          return 0;
    }
  }

}
