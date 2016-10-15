package api.map;

import java.util.Random;

import api.GameMap;
import api.map.elements.*;

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
    MapElement[][] newBoard = new MapElement[GameMap.MAP_W][GameMap.MAP_H];
    for (int i = 0; i < GameMap.MAP_W; i++) {
      for (int j = 0; j < GameMap.MAP_H; j++) {
        newBoard[i][j] = new Plains();
      }
    }
    return newBoard;
  }
  
  
  private static final int TILE_WALL = 1;
  private static final int TILE_FLOOR = 0;

  private static final int R1_CUT = 5;
  private static final int R2_CUT = 2;
  
  
  private static int[][] generateLevel()
  {
    //Init 2 maps
    int[][] map  = new int[GameMap.MAP_W][GameMap.MAP_H];
    int[][] map2 = new int[GameMap.MAP_W][GameMap.MAP_H];

    //Set one to 0 (we'll use this soon) and another entirely to floors
    for (int w = 0; w < GameMap.MAP_W; w++)
    {
        for (int h = 0; h < GameMap.MAP_H; h++)
        {
            map [w][h] = TILE_FLOOR;
            map2[w][h] = TILE_WALL;
        }
    }
    
    int totalSize = GameMap.MAP_W * GameMap.MAP_H;

    //Set an initial upper and lower percentage of the area to cover with walls.
    int pctLW = (int)(totalSize * 0.2);
    int pctUP = (int)(totalSize * 0.35);

    //Actually decide how much to fill and how many generationes to compute
    int numofStarts = random.nextInt(pctUP - pctLW) + pctLW;
    int numGenerations = random.nextInt(3) + 2;

    //Randomly fill the first array with walls.
    for (int i = 0; i < numofStarts; i++)
    {
        int x = random.nextInt(GameMap.MAP_W - 1);
        int y = random.nextInt(GameMap.MAP_H - 1);

        map[x][y] = TILE_WALL;
    }
    
    
    //Randomly decide to put a border or not...
    if( random.nextInt(50) % 2 == 0)
    {
      for (int yi = 0; yi < GameMap.MAP_W; yi++)
      {
          map[yi][0] = map[yi][GameMap.MAP_H - 1] = TILE_WALL;
      }
   
      for (int xi = 0; xi < GameMap.MAP_H; xi++)
      {
          map[0][xi] = map[GameMap.MAP_W - 1][xi] = TILE_WALL;
      }
    }
    
    //Do some generational pish based on some random neighbours "pish" / Gregor Roberston 2k16
    for (int gen = 0; gen < numGenerations; gen++)
    {
        for (int w = 1; w < GameMap.MAP_W; w++)
        {
            for (int h = 1; h < GameMap.MAP_H; h++)
            {
                int xi = 0; int ii = 0;                      
                int yi = 0; int jj = 0;

                for (yi = 1; yi < GameMap.MAP_W - 1; yi++)
                {
                    for (xi = 1; xi < GameMap.MAP_H - 1; xi++)
                    {
                        int adjcount_r1 = 0;
                        int adjcount_r2 = 0;

                        for (ii = -1; ii <= 1; ii++)
                        {
                            for (jj = -1; jj <= 1; jj++)
                            {
                                if (map[yi + ii][ xi + jj] != TILE_FLOOR) { adjcount_r1++; }
                            }
                        }
                        for (ii = yi - 2; ii <= yi + 2; ii++)
                        {
                            for (jj = xi - 2; jj <= xi + 2; jj++)
                            {
                                if (Math.abs(ii - yi) == 2 && Math.abs(jj - xi) == 2)                { continue; }
                                if (ii < 0 || jj < 0 || ii >= GameMap.MAP_W || jj >= GameMap.MAP_H)  { continue; }
                                if (map[ii][jj] != TILE_FLOOR)                                       { adjcount_r2++; }
                            }
                        }

                        if (adjcount_r1 >= R1_CUT || adjcount_r2 <= R2_CUT) { map2[yi][xi] = TILE_WALL; }
                        else                                                { map2[yi][xi] = TILE_FLOOR; }
                    }
                }
            }
            
            //Only useful for the second one...
            for (int yi = 1; yi < GameMap.MAP_W - 1; yi++)
            {
                for (int xi = 1; xi < GameMap.MAP_H - 1; xi++)
                {
                    map[yi][xi] = map2[yi][xi];
                }
            }
        }
    }
    
    return map;
  }
 
  
  
  
  

}