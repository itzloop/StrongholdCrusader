package game.gameobjects.buildings;

import game.gameobjects.Building;
import game.map.Tile;
import game.map.TileType;

public class WoodCamp extends Building {
    Tile[][] tiles;
    private final int width = 2;
    private final int length = 2;

    public WoodCamp()
    {
        tiles = new Tile[width][length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(TileType.WOOD_CAMP);
            }
        }
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                System.out.print(tiles[i][j].getTileType().ordinal());
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        WoodCamp wc = new WoodCamp();

    }
}


