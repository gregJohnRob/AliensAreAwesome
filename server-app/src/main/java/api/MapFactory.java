package api;

import java.util.Random;

public class MapFactory {
  public static final String[] MAP_TYPES = {"plains"};
  private static Random random = new Random();
  
  public static MapElement[][] generateNewMap() {
    String type = MAP_TYPES[random.nextInt(MAP_TYPES.length)];
    switch (type) {
    case "plains":
      return generatePlainsMap();
    default: return null;
    }
  }
  
  public static MapElement[][] generatePlainsMap() {
    MapElement[][] newBoard = new MapElement[GameMap.MAP_SIZE_W][GameMap.MAP_SIZE_H];
    for (int i = 0; i < GameMap.MAP_SIZE_W; i++) {
      for (int j = 0; j < GameMap.MAP_SIZE_H; j++) {
        newBoard[i][j] = new Plains();
      }
    }
    return newBoard;
  }

}