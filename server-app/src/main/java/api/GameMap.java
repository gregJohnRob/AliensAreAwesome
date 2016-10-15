package api;


import java.util.Random;

/* GameMap -> Deals with the Map of the Game
 * TODO write more here...
 */
public class GameMap 
{
  public static final int MAP_SIZE_W = 50;
  public static final int MAP_SIZE_H = 50;

  public static final String[] SEEDS = {"plains"};
  
  private MapElement[][] board; 
  
  public GameMap() {
    board = generateNewMap();
  }
  
  private MapElement[][] generateNewMap() {
    Random gen = new Random();
    String seed = SEEDS[gen.nextInt(SEEDS.length)];
    switch (seed) {
    case "plains":
      return generateNewPlainsMap();
    default:
      return generateNewPlainsMap();
    }
  }
  
  private MapElement[][] generateNewPlainsMap() {
    MapElement[][] newBoard = new MapElement[MAP_SIZE_W][MAP_SIZE_H];
    for (int i = 0; i < MAP_SIZE_W; i++) {
      for (int j = 0; j < MAP_SIZE_H; j++) {
        newBoard[i][j] = new Plains();
      }
    }
    return newBoard;
  }
}
