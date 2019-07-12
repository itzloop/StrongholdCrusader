package game.ui.inGame;

import game.AssetManager;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public class Toolbar extends Group {
    private Pane mainContent;
    private Pane mainToolbar;
    private Pane defense;
    private Pane industry;
    private Pane farm;
    private Pane home;
    private Pane military;
    private Pane food;
    private double width;
    private double height;
    public Toolbar()
    {
        mainToolbar = new Pane();
        defense = new Pane();
        industry = new Pane();
        farm = new Pane();
        home = new Pane();
        military = new Pane();
        food = new Pane();
        mainContent = new Pane();
        ImageView backgroundImage = new ImageView(AssetManager.assets.get("toolbar"));
        ImageView backgroundFill= new ImageView(AssetManager.assets.get("toolbar-fill"));
        ImageView btnDefense = new ImageView(AssetManager.assets.get("toolbar-btn-defense"));
        ImageView btnIndustry = new ImageView(AssetManager.assets.get("toolbar-btn-industry"));
        ImageView btnFarm = new ImageView(AssetManager.assets.get("toolbar-btn-farm"));
        ImageView btnHome = new ImageView(AssetManager.assets.get("toolbar-btn-home"));
        ImageView btnMilitary = new ImageView(AssetManager.assets.get("toolbar-btn-military"));
        ImageView btnFood = new ImageView(AssetManager.assets.get("toolbar-btn-food"));
        setMainContent();
        backgroundImage.setFitWidth(backgroundImage.getImage().getWidth()*1.5);
        backgroundImage.setFitHeight(backgroundImage.getImage().getHeight()*1.5);
        backgroundImage.setX((Screen.getPrimary().getBounds().getWidth() - backgroundImage.getFitWidth())/2);
        backgroundFill.setFitHeight(backgroundImage.getFitHeight());
        this.width = backgroundImage.getFitWidth();
        this.height = backgroundImage.getFitHeight();
        double length = btnDefense.getImage().getWidth();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnDefense,btnIndustry,btnFarm,btnHome,btnMilitary,btnFood);
        hBox.setLayoutX(backgroundImage.getX());
        hBox.setLayoutY(this.getHeight() - btnDefense.getImage().getHeight());
        hBox.setPadding(new Insets(0 , 0, 0 , 30 ));
        hBox.setSpacing(30);


        btnDefense.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(defense);
        });
        btnIndustry.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(industry);
        });
        btnFarm.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(farm);
        });
        btnHome.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(home);
        });
        btnMilitary.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(military);
        });
        btnFood.setOnMouseClicked(event -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(food);
        });


        //mainContent.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        mainContent.setLayoutX(backgroundImage.getX()+20);
        mainContent.setMinWidth(500*1.5);
        mainContent.setMinHeight(100*1.5);
        mainContent.setLayoutY(backgroundImage.getLayoutY() + 35);
        mainToolbar.getChildren().addAll(backgroundFill, backgroundImage ,mainContent , hBox);
        this.getChildren().addAll(mainToolbar);

    }


    private void setMainContent() {



        //defense Building
        HBox defenseHBox = new HBox();
        for (int i = 1; i <8; i++) {
            ImageView imageView = new ImageView(AssetManager.assets.get("toolbar-btn-defense-"+i));
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                String s = "toolbar-btn-defense-"+ finalI;
                System.out.println(s);
            });
            defenseHBox.getChildren().add(imageView);
        }
        defenseHBox.setPadding(new Insets(30, 0, 0 , 20));
        defenseHBox.setSpacing(30);
        defense.getChildren().add(defenseHBox);


        //industry Building
        HBox industryHBox = new HBox();
        for (int i = 1; i <8; i++) {
            ImageView imageView = new ImageView(AssetManager.assets.get("toolbar-btn-industry-"+i));
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                String s = "toolbar-btn-industry-"+ finalI;
                System.out.println(s);
            });
            industryHBox.getChildren().add(imageView);
        }
        industryHBox.setPadding(new Insets(30, 0, 0 , 20));
        industryHBox.setSpacing(30);
        industry.getChildren().add(industryHBox);


        //farm Building
        HBox farmHBox = new HBox();
        for (int i = 1; i <6; i++) {
            ImageView imageView = new ImageView(AssetManager.assets.get("toolbar-btn-farm-"+i));
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                String s = "toolbar-btn-farm-" + finalI;
                System.out.println(s);
            });
            farmHBox.getChildren().add(imageView);
        }
        farmHBox.setPadding(new Insets(30, 0, 0 , 20));
        farmHBox.setSpacing(30);
        farm.getChildren().add(farmHBox);


        //home building
        HBox homeHBox = new HBox();
        for (int i = 1; i <6; i++) {
            ImageView imageView = new ImageView(AssetManager.assets.get("toolbar-btn-home-"+i));
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                String s = "toolbar-btn-home-" + finalI;
                System.out.println(s);
            });
            homeHBox.getChildren().add(imageView);
        }
        homeHBox.setPadding(new Insets(30, 0, 0 , 20));
        homeHBox.setSpacing(30);
        home.getChildren().add(homeHBox);



        //military building
        HBox militaryHBox = new HBox();
        for (int i = 1; i <6; i++) {
            ImageView imageView = new ImageView(AssetManager.assets.get("toolbar-btn-military-"+i));
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                String s = "toolbar-btn-military-" + finalI;
                System.out.println(s);
            });
            militaryHBox.getChildren().add(imageView);
        }
        militaryHBox.setPadding(new Insets(30, 0, 0 , 20));
        militaryHBox.setSpacing(30);
        military.getChildren().add(militaryHBox);


        //military building
        HBox foodHBox = new HBox();
        for (int i = 1; i <6; i++) {
            ImageView imageView = new ImageView(AssetManager.assets.get("toolbar-btn-food-"+i));
            int finalI = i;
            imageView.setOnMouseClicked(event -> {
                String s = "toolbar-btn-food-" + finalI;
                System.out.println(s);
            });
            foodHBox.getChildren().add(imageView);
        }
        foodHBox.setPadding(new Insets(30, 0, 0 , 20));
        foodHBox.setSpacing(30);
        food.getChildren().add(foodHBox);


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
}
