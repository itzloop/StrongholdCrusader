package game.ui.inGame;

import game.AssetManager;
import game.GV;
import game.gameobjects.GameObject;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.Food.*;
import game.gameobjects.buildings.defense.Armory;
import game.gameobjects.buildings.defense.Barracks;
import game.gameobjects.buildings.defense.MercenaryPost;
import game.gameobjects.buildings.farm.*;
import game.gameobjects.buildings.industry.*;
import game.gameobjects.buildings.military.*;
import game.gameobjects.buildings.townBuilding.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import java.util.Map;
import java.util.Optional;

public class Toolbar extends Group {
    private Pane mainContent;
    private Pane pane;
    private double width;
    private double height;
    private int[] counts = {8 , 8 , 6 , 6,6 , 6};
    private String[] keys =
            {"defense",
            "industry",
            "farm",
            "home",
            "military",
            "food"};
    private Map<Integer ,GameObject> gameObjects;
    private GameObject currentGameObject;
    private int currentIndex;
    private String currentKey;
    private boolean waitingToBePlaced;
    private Label lblGold;
    private Label lblPopularity;
    private Label lblPopulation;



    public Toolbar(Pane pane , Map<Integer , GameObject> gameObjects)
    {
        mainContent = new Pane();
        this.pane = pane;
        this.gameObjects = gameObjects;
        lblGold = new Label(Castle.getGold().get() + "");
        lblGold.setStyle("-fx-font-size: 15;");
        lblGold.setTextFill(Color.BLACK);
        lblPopularity = new Label(Castle.getPopularity().get() + "");
        lblPopularity.setStyle("-fx-font-size: 15;");
        lblPopularity.setStyle("-fx-font-size: 15;");
        lblPopularity.setTextFill(Color.GREEN);
        lblPopulation = new Label(Castle.getCurrentPopulation().get() + "/" + Castle.getMaxPopulationSize().get());
        lblPopulation.setStyle("-fx-font-size: 15;");
        VBox texts = new VBox(lblPopularity , lblGold , lblPopulation);
        texts.setLayoutX(1255);
        texts.setLayoutY(60);
        texts.setRotate(13);
        texts.setAlignment(Pos.CENTER);

        //load the images
        ImageView backgroundImage = new ImageView(AssetManager.images.get("toolbar"));
        ImageView backgroundFill= new ImageView(AssetManager.images.get("toolbar-fill"));
        ImageView[] buttons = new ImageView[keys.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new ImageView(AssetManager.images.get("toolbar-btn-"+keys[i]));
        }

        //layout setup
        backgroundImage.setFitWidth(backgroundImage.getImage().getWidth());
        backgroundImage.setFitHeight(backgroundImage.getImage().getHeight());
        backgroundImage.setX((Screen.getPrimary().getBounds().getWidth() - backgroundImage.getFitWidth())/2);
        backgroundFill.setY(backgroundImage.getImage().getHeight() - backgroundFill.getImage().getHeight());
        this.width = backgroundImage.getFitWidth();
        this.height = backgroundImage.getFitHeight();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(buttons);
        hBox.setLayoutX(backgroundImage.getX());
        hBox.setLayoutY(this.getHeight() - buttons[0].getImage().getHeight());
        hBox.setPadding(new Insets(0 , 0, 0 , 30 ));
        hBox.setSpacing(25);

        //add click event for buttons
        for (int i = 0; i < buttons.length; i++) {
            addClickEvent(buttons[i] , i);
        }

        //more layout setup
        mainContent.setLayoutX(backgroundImage.getX()+20);
        mainContent.setMinWidth(500);
        mainContent.setMinHeight(100);
        mainContent.setLayoutY(backgroundImage.getLayoutY() + 35);
        this.getChildren().addAll(backgroundFill, backgroundImage ,mainContent,hBox , texts);

    }

    private void addClickEvent(ImageView imageView , int i)
    {
        imageView.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            loadImages(keys[i] , counts[i]);
        });
    }


    private void loadImages(String key , int count)
    {
        HBox hBox = new HBox();
        for (int i = 1; i <count; i++) {
            ImageView imageView = new ImageView(AssetManager.images.get("toolbar-btn-"+key + "-" + i));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                try {

                    GameObject gameObject = createObject(key , finalI);
                    if(!Optional.ofNullable(gameObject).isPresent())
                        throw new Exception("game object is null with id: " + key+"-"+finalI);
                    gameObject.addEventHandler(MouseEvent.MOUSE_CLICKED , event1 -> {
                        mainContent.getChildren().clear();
                        mainContent.getChildren().add(gameObject.getToolbar());
                    });
                    currentIndex = finalI;
                    currentKey = key;
                    gameObjects.put(gameObject.getObjectId() , gameObject);
                    if(Optional.ofNullable(currentGameObject).isPresent())
                        this.pane.getChildren().remove(currentGameObject);
                    currentGameObject = gameObject;
                    this.pane.getChildren().add(gameObject);
                    this.waitingToBePlaced = true;
                }catch (Exception e)
                { e.printStackTrace(); }
            });


            hBox.getChildren().add(imageView);
        }
        hBox.setPadding(new Insets(20, 0, 0 , 10));
        hBox.setSpacing(20);
        mainContent.getChildren().addAll(hBox);
    }

    public GameObject createObject(String key , int id) {
        switch (key)
        {
            case"defense":
                return createDefenseBuilding(id);
            case "industry":
                return createIndustryBuilding(id);
            case"farm":
                return createFarmBuilding(id);
            case "home":
                return createHomeBuilding(id);
            case"military":
                return createMilitaryBuilding(id);
            case"food":
                return createFoodBuilding(id);
                default:
                    return null;
        }
    }

    private GameObject createMilitaryBuilding(int id) {
        switch (id)
        {
            case 1:
                return new FlethersWorkshop(GV.mapPos.clone());
            case 2:
                return new PoleturnersWorkshop(GV.mapPos.clone());
            case 3:
                return new BlacksmithsWorkshop(GV.mapPos.clone());
            case 4:
                return new TannerWorkshop(GV.mapPos.clone());
            case 5:
                return new ArmourersWorkshop(GV.mapPos.clone());
            default:
                return null;
        }
    }

    private GameObject createHomeBuilding(int id) {
        switch (id)
        {
            case 1:
                Hovel.rand = (int)(Math.random()*4);
                return new Hovel(GV.mapPos.clone());
            case 2:
                return new Chapel(GV.mapPos.clone());
            case 3:
                return new Church(GV.mapPos.clone());
            case 4:
                return new Cathedral(GV.mapPos.clone());
            case 5:
                return new Apothecary(GV.mapPos.clone());
            default:
                return null;
        }
    }

    private GameObject createFarmBuilding(int id) {
        switch (id)
        {
            case 1:
                return new Hunter(GV.mapPos.clone());
            case 2:
                return new DairyFarm(GV.mapPos.clone());
            case 3:
                return new AppleFarm(GV.mapPos.clone());
            case 4:
                return new WheatFarm(GV.mapPos.clone());
            case 5:
                return new HopsFarm(GV.mapPos.clone());
            default:
                return null;
        }
    }

    private GameObject createIndustryBuilding(int id) {
        switch (id)
        {
            case 1:
                return new StockPile(GV.mapPos.clone());
            case 2:
                return new WoodCutter(GV.mapPos.clone());
            case 3:
                return new StoneQuarry(GV.mapPos.clone());
            case 5:
                return new IronMine(GV.mapPos.clone());
            case 6:
                return new PitchRig(GV.mapPos.clone());
            case 7:
                return MarketPlace.createMarketPlace(GV.mapPos.clone());
            default:
                return null;
        }
    }

    private GameObject createDefenseBuilding(int id) {
        switch (id)
        {
            case 5:
                return Barracks.createBrracks(GV.mapPos.clone());
            case 6:
                return MercenaryPost.createMercenaryPost(GV.mapPos.clone());
            case 7:
                return new Armory(GV.mapPos.clone());
            default:
                return null;
        }
    }

    private GameObject createFoodBuilding(int id) {
        switch (id)
        {
            case 1:
                return new Granary(GV.mapPos.clone());
            case 2:
                return new Bakery(GV.mapPos.clone());
            case 3:
                return new Brewery(GV.mapPos.clone());
            case 4:
                return new Mill(GV.mapPos.clone());
            case 5:
                return new Inn(GV.mapPos.clone());
            default:
                return null;
        }
    }

    public Pane getMainContent() {
        return mainContent;
    }


    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public GameObject getCurrentGameObject() {
        return currentGameObject;
    }

    public void setCurrentGameObject(GameObject currentGameObject) {
        this.currentGameObject = currentGameObject;
    }

    public boolean isWaitingToBePlaced() {
        return waitingToBePlaced;
    }

    public void setWaitingToBePlaced(boolean waitingToBePlaced) {
        this.waitingToBePlaced = waitingToBePlaced;
    }

    public Label getLblGold() {
        return lblGold;
    }

    public void setLblGold(Label lblGold) {
        this.lblGold = lblGold;
    }

    public Label getLblPopularity() {
        return lblPopularity;
    }

    public void setLblPopularity(Label lblPopularity) {
        this.lblPopularity = lblPopularity;
    }

    public Label getLblPopulation() {
        return lblPopulation;
    }

    public void setLblPopulation(Label lblPopulation) {
        this.lblPopulation = lblPopulation;
    }

    public void update() {
        getLblGold().setText(Castle.getGold().get() + "");
        getLblPopularity().setText(Castle.getPopularity().get() + "");
        System.out.println(Castle.getMaxPopulationSize().get());
        getLblPopulation().setText(Castle.getCurrentPopulation().get() +"/"+Castle.getMaxPopulationSize().get());
    }
}
