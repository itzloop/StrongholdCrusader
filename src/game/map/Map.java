package game.map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Map extends Application
{
    private int num;
    class Change implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {

        }
    }
    String name;
    private int width;
    private int height;
    private int[][] tilesNumber;
    private transient Tile[][] tiles;
    private transient VBox vBox;
    private transient HBox hBox;
    private transient ScrollPane scrollPane;
    private transient BorderPane pane;
    Stage stage;

    public Map(){

    }
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

        vBox = new VBox();
        scrollPane = new ScrollPane(vBox);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                event.consume();
            }
        });
        scrollPane.addEventHandler(MouseEvent.ANY,event -> {
            if(event.getSceneX() > stage.getWidth()-50)
            {
                scrollPane.setHvalue(scrollPane.getHvalue()+0.001);
                System.out.println(scrollPane.getHvalue());
            }
            if(event.getSceneX() <5)
            {
                scrollPane.setHvalue(scrollPane.getHvalue()-0.001);
                System.out.println(scrollPane.getHvalue());
            }

            if(event.getSceneY() > stage.getHeight()-50)
            {
                scrollPane.setVvalue(scrollPane.getVvalue()+0.001);
                System.out.println(scrollPane.getHvalue());
            }
            if (event.getSceneY() < 5)
            {
                scrollPane.setVvalue(scrollPane.getVvalue()-0.001);
                System.out.println(scrollPane.getVvalue());
            }
        });

        scrollPane.setHmin(0);
        pane = new BorderPane();
        pane.setCenter(scrollPane);


        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            hBox = new HBox();
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get(),j*30,i*30);
                hBox.getChildren().add(tiles[i][j]);
            }
            vBox.getChildren().add(hBox);
        }
    }



    public void initializeTiles(int[][] tilesNumber)
    {

//        gridPane = new GridPane();
//        scrollPane = new ScrollPane(gridPane);
//        pane = new Pane(scrollPane);
//
//        tiles = new Tile[width][height];
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get() , j*30 , i*30);
//                gridPane.add(tiles[i][j] , j , i);
//            }
//        }
    }

    public Pane getPane() {
        return pane;
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

    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Map map = new Map("Sting" , 200 , 200);
        System.out.println(map.toString());
        map.stage = primaryStage;
        primaryStage.setScene(new Scene(map.getPane()));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
