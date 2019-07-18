package game.map;

import game.AssetManager;
import game.GV;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.Food.Granary;
import game.gameobjects.buildings.defense.Armory;
import game.gameobjects.buildings.industry.StockPile;
import game.gameobjects.buildings.townBuilding.Hovel;
import game.gameobjects.units.Human;
import game.map.components.DragComponent;
import game.ui.inGame.Toolbar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Optional;

public class Map extends Application
{
    private String name;
    private int width;
    private int height;
    private int[][] tilesNumber;
    public transient static java.util.Map<Tile , LinkedHashSet<Tile>> adjList = new LinkedHashMap<>();
    private transient Toolbar toolbar;
    private transient DragComponent dragComponent;
    public static transient Tile[][] tiles;
    private transient java.util.Map<Integer,GameObject> gameObjects;
    private transient ScrollPane scrollPane;
    private transient BorderPane borderPane;
    public static transient Pane pane;
    private Human currentHuman;
    private transient Thread applyRules = new Thread(() -> {
        while (true)
        {
            Castle.caculate();
            Platform.runLater(() -> {
                toolbar.update();
            });

            for(int id : gameObjects.keySet())
            {
                if(gameObjects.get(id) instanceof Building)
                {
                    ((Building)gameObjects.get(id)).produce();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    private transient Thread scrollOnMouseMove = new Thread(() -> {

        borderPane.addEventHandler(MouseEvent.ANY , event -> {
            GV.mousePosition.set(event.getX() , event.getY());
            if(event.getTarget() instanceof Tile)
            {
                int j = (int)((Tile) event.getTarget()).getCoordinate().getX();
                int i = (int)((Tile) event.getTarget()).getCoordinate().getY();
                double x = Math.abs(tiles[i][j].getX() - GV.tileSize.getX());
                double y = tiles[i][j].getY() - GV.tileSize.getY();
                GV.mapPos.set(x, y);
//                System.out.println(i +","+j + " , " + tiles[i][j].getOccupiedType());
                if(Optional.ofNullable(toolbar.getCurrentGameObject()).isPresent() && toolbar.isWaitingToBePlaced())
                {
                    toolbar.getCurrentGameObject().setLayoutX(tiles[i][j].getX());
                    toolbar.getCurrentGameObject().setLayoutY(tiles[i][j].getY());
                }
                if(event.getButton() == MouseButton.SECONDARY && Optional.ofNullable(toolbar.getCurrentGameObject()).isPresent())
                {
                    if(toolbar.getCurrentGameObject() instanceof StockPile)
                        Castle.getMaxStockPileCapacity().addAndGet(-GV.stockPileCapacity);
                    if(toolbar.getCurrentGameObject() instanceof Granary)
                        Castle.getMaxGranaryCapacity().addAndGet(-GV.GranaryCapacity);
                    if(toolbar.getCurrentGameObject() instanceof Armory)
                        Castle.getMaxArmoryCapacity().addAndGet(-GV.armoryCapacity);
                    if(toolbar.getCurrentGameObject() instanceof Hovel)
                        Castle.getMaxPopulationSize().addAndGet(-GV.HovelCapacity);
                    toolbar.setWaitingToBePlaced(false);
                    pane.getChildren().remove(toolbar.getCurrentGameObject());
                    toolbar.setCurrentGameObject(null);
//                    toolbar.setCurrentIndex(-1);
//                    toolbar.setCurrentKey("");
                }else if(event.getButton() == MouseButton.PRIMARY && Optional.ofNullable(toolbar.getCurrentGameObject()).isPresent())
                {

                    toolbar.getCurrentGameObject().setIJ(i,j);
                    System.out.println(toolbar.getCurrentGameObject().getX() + " , " + toolbar.getCurrentGameObject().getY());
                    toolbar.setCurrentGameObject(null);
                }
                if(event.getButton() == MouseButton.PRIMARY && Optional.ofNullable(currentHuman).isPresent())
                {
                    Human h = currentHuman;
                    currentHuman = null;
                    new Thread(() ->{
                        h.move(tiles[i][j]);
                        System.out.println("moved");
                    }).start();
                }
            }
            if(event.getTarget() instanceof Human && event.getButton() == MouseButton.PRIMARY)
            {
                currentHuman = (Human) event.getTarget();
            }
        });
        pane.addEventHandler(MouseEvent.MOUSE_MOVED , event -> {


        });
        while (true)
        {
            if(GV.mousePosition.getX() > Screen.getPrimary().getBounds().getWidth()-30)
                scrollPane.setHvalue(scrollPane.getHvalue()+ GV.scrollOffset);

            if(GV.mousePosition.getX() <15)
                scrollPane.setHvalue(scrollPane.getHvalue()-GV.scrollOffset);

            if(GV.mousePosition.getY() > Screen.getPrimary().getBounds().getHeight()-10 )
                scrollPane.setVvalue(scrollPane.getVvalue()+GV.scrollOffset*4);

            if (GV.mousePosition.getY() < 15)
                scrollPane.setVvalue(scrollPane.getVvalue()-GV.scrollOffset*4);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    public Map() { }
    //TODO fix this so you can load this from a file
    public Map(String name , int width , int height)
    {
        System.out.println("Map created");
        this.name = name;
        this.width = width;
        this.height = height;
        tilesNumber = new int[width][height];
        tiles = new Tile[width][height];
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
                //tiles[i][j] = tiles[i][j] = new Tile(TileType.valueOf(tilesNumber[i][j]).get(),new Vector2D(j,i));
            }
        }
    }

    public void initialize()
    {
        gameObjects = new LinkedHashMap<>();
        pane = new Pane();
        //dragComponent = new DragComponent(pane);
        HBox hBox;
        scrollPane = new ScrollPane(pane);
        toolbar = new Toolbar(pane , gameObjects);
        borderPane = new BorderPane();
        StackPane stackPane = new StackPane(scrollPane , toolbar);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);
        borderPane.setCenter(stackPane);
        tiles = MapLoader.load();
        toolbar.setMiniMap(MapLoader.loadMiniMap());

        for (int i = 0; i < tiles.length ;i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];
                adjList.put(tile , new LinkedHashSet<>());

                try {
                    adjList.get(tile).add(tiles[i][j+1]);
                }catch (Exception e){}
                try {
                    adjList.get(tile).add(tiles[i][j-1]);
                }catch (Exception e){}
                try {
                    adjList.get(tile).add(tiles[i+1][j]);
                }catch (Exception e){}
                try {
                    adjList.get(tile).add(tiles[i-1][j]);
                }catch (Exception e){}
                try {
                    adjList.get(tile).add(tiles[i+2][j]);
                }catch (Exception e){}
                try {
                    adjList.get(tile).add(tiles[i-2][j]);
                }catch (Exception e){}
                if(i %2 ==0){
                    try {
                        adjList.get(tile).add(tiles[i-1][j-1]);
                    }catch (Exception e){}
                    try {
                        adjList.get(tile).add(tiles[i+1][j-1]);
                    }catch (Exception e){}
                }else
                {
                    try {
                        adjList.get(tile).add(tiles[i-1][j+1]);
                    }catch (Exception e){}
                    try {
                        adjList.get(tile).add(tiles[i+1][j+1]);
                    }catch (Exception e){}
                }
            }
        }

        GameObject castle = Castle.createCastle();
        if(Optional.ofNullable(castle).isPresent())
        {
            castle.setIJ(GV.castlePos[0][0] , GV.castlePos[0][1]);
            gameObjects.put(castle.getObjectId() , castle);
            castle.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
                toolbar.getMainContent().getChildren().clear();
                toolbar.getMainContent().getChildren().add(castle.getToolbar());
            });
            GameObject stockPile = new StockPile();
            stockPile.setIJ(8,8);
            stockPile.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
                toolbar.getMainContent().getChildren().clear();
                toolbar.getMainContent().getChildren().add(stockPile.getToolbar());
            });
            pane.getChildren().add(castle);
            pane.getChildren().add(stockPile);
        }
        else
            System.out.println("gameobject is null");
        GameObject citizen = new Human();
        citizen.setIJ(castle.getI() +10, castle.getJ()+10);
        pane.getChildren().addAll(citizen);
        applyRules.start();
        handleEvents();
    }

    private void handleEvents()
    {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                event.consume();
            }
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
                stringBuilder.append(t.getTileType()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
       launch(args);
    }

    public static void playSound(Media media)
    {
        new MediaPlayer(media).play();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Map map = new Map("Sting" , 150 , 150 );
        map.initialize();
        primaryStage.setScene(new Scene(map.getPane()));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
