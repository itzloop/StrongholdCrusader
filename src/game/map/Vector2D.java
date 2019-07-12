package game.map;

public class Vector2D {
    private double x , y;
    public Vector2D(double x , double y)
    {
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return  "(" + x + " , " + y + ")";
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
