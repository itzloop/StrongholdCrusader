package game.map;


import java.util.Arrays;
import java.util.Optional;

public enum TileType
{
    DUST(0),
    SEA(1),
    MOUNTAIN(2),
    GRASS(3);

    private int value;
    TileType(int value)
    {
        this.value = value;
    }


    public static Optional<TileType> valueOf(int value){
        return Arrays.stream(values()).filter(val -> val.value == value).findFirst();
    }


}