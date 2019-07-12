package game.map;

import game.AssetManager;
import game.GV;
import game.gameobjects.Gameobject;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Tile extends Group
{




    private TileType tileType;
    private ImageView image;
    private Rectangle rectangle;
    private Vector2D location;
    public Tile(TileType tileType ,Vector2D location) {
        this.tileType = tileType;
        this.location = location;
        Image img;
        image = new ImageView();
        image.setFitWidth(GV.tileSize.getX());
        image.setFitHeight(GV.tileSize.getY());
        super.setLayoutX(location.getX());
        super.setLayoutY(location.getY());
        image.setX(location.getX());
        image.setY(location.getY());

        switch (tileType)
        {
            case DUST:
                image.setImage(AssetManager.assets.get("dust"));
                break;
            case MOUNTAIN:

                image.setImage(AssetManager.assets.get("dust"));
                break;
            case SEA:

                image.setImage(AssetManager.assets.get("dust"));
                break;
        }
        super.getChildren().add(image);
    }



//
//    private TileType tileType;
//    private Polygon polygon;
//    private Gameobject gameobject;
//    private Vector2D locatoion;
//    public Tile(TileType tileType ,Vector2D location) {
//        this.tileType = tileType;
//        this.locatoion = location;
//        Image img;
//        polygon = myShape(location);
//        super.setLayoutX(location.getX());
//        super.setLayoutY(location.getY());
//
//
////        switch (tileType)
////        {
////            case DUST:
////                polygon.setFill(AssetManager.assets.get("dust"));
////                break;
////            case MOUNTAIN:
////
////                rectangle.setImage(AssetManager.assets.get("dust"));
////                break;
////            case SEA:
////
////                rectangle.setImage(AssetManager.assets.get("dust"));
////                break;
////        }
//        super.getChildren().add(polygon);
//    }
//
//
//    public Polygon myShape(Vector2D center)
//    {
//        Polygon diamond = new Polygon(new double[]{
//                center.getX() ,center.getY() - GV.tileSize.getY()/2,
//                center.getX()+GV.tileSize.getX()/2 , center.getY(),
//                center.getX() , center.getY() + GV.tileSize.getY()/2,
//                center.getX()-GV.tileSize.getX()/2 , center.getY()
//        });
//        diamond.setFill(new ImagePattern(AssetManager.assets.get("dust")));
//        return diamond;
//    }
//



    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    @Override
    public String toString() {
        return "[source = pane , " + "tile type = " + tileType + " , location = " + location;
    }
}
