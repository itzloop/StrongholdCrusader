package game.map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import game.GV;
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
    String name;
    private int width;
    private int height;
    private int[][] tilesNumber;
    private transient Tile[][] tiles;
    private transient VBox vBox;
    private transient HBox hBox;
    private transient ScrollPane scrollPane;
    private transient BorderPane borderPane;
    private transient double sceneX=0;
    private transient double sceneY=0;

    private transient Thread scrollOnMouseMove = new Thread(() -> {
        while (true)
        {
            if(sceneX > Screen.getPrimary().getBounds().getWidth()-50)
            {
                scrollPane.setHvalue(scrollPane.getHvalue()+ GV.scrollOffset);
            }
            if(sceneX <5)
            {
                scrollPane.setHvalue(scrollPane.getHvalue()-GV.scrollOffset);
            }

            if(sceneY > Screen.getPrimary().getBounds().getHeight()-50)
            {
                scrollPane.setVvalue(scrollPane.getVvalue()+GV.scrollOffset);
            }
            if (sceneY < 5)
            {
                scrollPane.setVvalue(scrollPane.getVvalue()-GV.scrollOffset);
            }
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    public Map(){

    }
    //TODO fix this so you can load this from a file
    public Map(String name , int width , int height)
    {
        System.out.println("Map created");
        this.name = name;
        this.width = width;
        this.height = height;
        tilesNumber = new int[width][height];
        loadMap();
        tiles = new Tile[width][height];
        //TODO this has to move on start method so fix this later
        //init();
    }

    //TODO later make this so we can load map from a file
    public void loadMap()
    {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tilesNumber[i][j] = (int)(Math.random()*3);
            }
        }
    }
    public void loadMap(int[][] tilesNumber)
    {
        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get(),new Vector2D(j,i));
            }
        }
        init();
    }

    public void init()
    {
        //initializing the
        vBox = new VBox();
        scrollPane = new ScrollPane(vBox);
        borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);

        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            hBox = new HBox();
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get(),new Vector2D(j,i));
                tiles[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
                    System.out.println(event);
                    System.out.println(event.getTarget());
                });
                hBox.getChildren().add(tiles[i][j]);
            }
            vBox.getChildren().add(hBox);
        }
        handleEvents();

    }


    public void handleEvents()
    {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                event.consume();
            }
        });
        scrollPane.addEventHandler(MouseEvent.ANY,event -> {
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            //System.out.println(event);
        });
        scrollOnMouseMove.start();
    }

    public void initializeTiles(int[][] tilesNumber)
    {

//        gridPane = new GridPane();
//        scrollPane = new ScrollPane(gridPane);
//        borderPane = new Pane(scrollPane);
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
        return borderPane;
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
        Map map = new Map("Sting" , 70 , 70 );
        //System.out.println(map.toString());
        Gson gson = new Gson();
        Map map1 = gson.fromJson(gson.toJson(map) , Map.class);
        map1.loadMap(map1.getTilesNumber());
        System.out.println(map1);
//        JsonElement jsonElement = gson.fromJson(gson.toJson(map) , JsonElement.class);
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        primaryStage.setScene(new Scene(map.getPane()));
//        primaryStage.setFullScreen(true);
//        primaryStage.show();
    }
}
