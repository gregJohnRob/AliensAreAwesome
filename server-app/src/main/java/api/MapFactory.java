package api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapFactory {
  public static final String[] MAP_TYPES = {"plains", "von"};
  public static final String[] ELEMENT_TYPES = {"plains", "stone", "wheat", "dirt"};
  private static Random random = new Random();
  
  public static MapElement[][] generateNewMap() {
    String type = MAP_TYPES[random.nextInt(MAP_TYPES.length)];
    switch (type) {
    case "plains":
      return generatePlainsMap();
    case "von":
      return generateVonMap();
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
  
  public static MapElement[][] generateVonMap() {
    MapElement[][] newBoard = new MapElement[GameMap.MAP_SIZE_W][GameMap.MAP_SIZE_H];
    List<Point> points = new ArrayList<Point>(); 
    for (int i = 0; i < 20; i++) {
      points.add(new Point(random.nextInt(GameMap.MAP_SIZE_W), random.nextInt(GameMap.MAP_SIZE_H)));
    }
    for (int i = 0; i < GameMap.MAP_SIZE_W; i++) {
      for (int j = 0; j < GameMap.MAP_SIZE_H; i++) {
        int closest = 0;
        double closestDistance = 10000000.0;
        for (int x = 0; x < ELEMENT_TYPES.length; x++) {
          double store = points.get(x).distance(i, j);
          if (store < closestDistance) {
            closestDistance = store;
            closest = x;
          }
        }
        switch (ELEMENT_TYPES[closest]) {
        case "plains":
          newBoard[i][j] = new Plains();
          break;
        case "stone":
          newBoard[i][j] = new Stone();
          break;
        case "wheat":
          break;
        case "dirt":
          break;
        default: 
          newBoard[i][j] = new Plains();
        }
      }
    }
    return newBoard;
  }

}