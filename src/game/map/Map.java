package game.map;

import game.AssetManager;
import game.GV;
import game.map.components.DragComponent;
import game.ui.inGame.Toolbar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class Map extends Application
{
    String name;
    private int width;
    private int height;
    private int[][] tilesNumber;
    private transient Toolbar toolbar = new Toolbar();
    private transient DragComponent dragComponent;
    private transient Tile[][] tiles;
    private transient ScrollPane scrollPane;
    private transient BorderPane borderPane;
    private transient double sceneX=0;
    private transient double sceneY=0;


    //TODO Fix this later
    private transient Thread scrollOnMouseMove = new Thread(() -> {
        AtomicBoolean shouldScroll = new AtomicBoolean(true);
        toolbar.addEventFilter(MouseEvent.ANY , event -> {
            shouldScroll.set(false);
        });
        scrollPane.addEventFilter(MouseEvent.ANY , event -> {
            shouldScroll.set(true);
        });

        while (true)
        {
            if(sceneX > Screen.getPrimary().getBounds().getWidth()-50)
            {
                scrollPane.setHvalue(scrollPane.getHvalue()+ GV.scrollOffset);
            }
            if(sceneX <15)
            {
                scrollPane.setHvalue(scrollPane.getHvalue()-GV.scrollOffset);
            }

            if(sceneY > Screen.getPrimary().getBounds().getHeight()-100 - toolbar.getHeight() && shouldScroll.get())
            {
                scrollPane.setVvalue(scrollPane.getVvalue()+4*GV.scrollOffset);
            }
            if (sceneY < 15)
            {
                scrollPane.setVvalue(scrollPane.getVvalue()-4*GV.scrollOffset);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public Map() {

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
        Pane pane = new Pane();
        dragComponent = new DragComponent(pane);
        HBox hBox;
        scrollPane = new ScrollPane(pane);
        borderPane = new BorderPane();


        borderPane.setCenter(scrollPane);
        borderPane.setBottom(toolbar);
        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            hBox = new HBox();
            hBox.setLayoutX(i%2==0 ? 0: GV.tileSize.getX() /2);
            hBox.setLayoutY(i*GV.tileSize.getY()/2);
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(TileType.valueOf(0).get(),new Vector2D(j,i));
                tiles[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
                    System.out.println(event);
                    System.out.println(event.getTarget());
                });
                hBox.getChildren().add(tiles[i][j]);
            }
            pane.getChildren().add(hBox);

        }
        ImageView imageView = new ImageView(AssetManager.assets.get("building"));
        imageView.setLayoutX(500);
        imageView.setLayoutY(500);
        pane.getChildren().add(imageView);
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


    public BorderPane getPane() {
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
        Map map = new Map("Sting" , 150 , 150 );
        map.init();
//        JsonElement jsonElement = gson.fromJson(gson.toJson(map) , JsonElement.class);
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
        primaryStage.setScene(new Scene(map.getPane()));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
