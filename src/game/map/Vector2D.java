package game.map;

import java.util.Comparator;

public class Vector2D  {
    private double x , y;

    public Vector2D(double x , double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2D()
    {
        this(0 , 0);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Vector2D destination)
    {
        return Math.sqrt(Math.pow(destination.getX()-this.getX() , 2) +
                Math.pow(destination.getY()-this.getY() , 2)
        );
    }

    public Vector2D clone()  {
        return new Vector2D(this.x , this.y);
    }

    @Override
    public String toString() {
        return  "(" + x + " , " + y + ")";
    }



    public boolean compareTo(Vector2D that)
    {
        return this.y > that.y;
    }

}
