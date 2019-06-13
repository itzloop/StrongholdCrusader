package game.map;


import java.util.Arrays;
import java.util.Optional;

public enum TileType
{
    DUST(0),
    MOUNTAIN(1),
    SEA(2);
    private int value;
    TileType(int value)
    {
        this.value = value;
    }


    public static Optional<TileType> valueOf(int value){
        return Arrays.stream(values()).filter(val -> val.value == value).findFirst();
    }


}