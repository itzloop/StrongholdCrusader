package game.map;

import game.AssetManager;
import game.GV;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Shape extends Application {
    @Override
    public void start(Stage stage) throws InterruptedException {


        Pane pane = new Pane();
        ScrollPane root = new ScrollPane(pane);
        HBox[] hBox1 = new HBox[10];
        HBox hbox ;
        for (int i = 0; i < 70; i++) {
            hbox = new HBox();
            hbox.setLayoutY(i * GV.tileSize.getY()/2);
           hbox.setLayoutX(i % 2 == 0 ? 0 : GV.tileSize.getX()/2);
            for (int j = 0; j < 70; j++) {

                Polygon polygon = myShape(new Vector2D((j+1)*GV.tileSize.getX() , (i+1)*GV.tileSize.getY()));
                polygon.setOnMouseEntered(event -> System.out.println(event));
                hbox.getChildren().addAll(polygon);

            }
            pane.getChildren().add(hbox);

        }

        
        //Creating a scene object
        Scene scene = new Scene(root, 1000, 1000);

        //Setting title to the Stage
        stage.setTitle("Drawing an arc through a path");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

    public Polygon myShape(Vector2D center)
    {
        Polygon diamond = new Polygon(new double[]{
            center.getX() ,center.getY() - GV.tileSize.getY()/2,
            center.getX()+GV.tileSize.getX()/2 , center.getY(),
            center.getX() , center.getY() + GV.tileSize.getY()/2,
            center.getX()-GV.tileSize.getX()/2 , center.getY()
        });
        diamond.setFill(new ImagePattern(AssetManager.images.get("dust")));
        return diamond;
    }

    public static void main(String args[]){
        launch(args);
    }

}


