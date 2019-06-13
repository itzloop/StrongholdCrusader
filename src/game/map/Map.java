package game.map;

public class Map
{
    String name;
    private int width;
    private int height;
    private int[][] tilesNumber;
    private transient Tile[][] tiles;
    //TODO fix this so you can load this from a file
    public Map(String name , int width , int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
        tilesNumber = new int[width][height];

        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tilesNumber[i][j] = (int)(Math.random()*3);
            }
        }

        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get());
            }
        }
    }

    public void initializeTiles(int[][] tilesNumber)
    {
        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get());
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getTilesNumber() {
        return tilesNumber;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Tile[] tile : tiles)
        {
            for (Tile t: tile)
            {
                stringBuilder.append(t.getTileType()+" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
