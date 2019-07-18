package game;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    public static Map<String , Image> images = new HashMap<>();
    public static Map<String , Media> sounds = new HashMap<>();

    static {
        try {



            images.put("building-castle" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/castle.png")));
            images.put("body" , new Image(new FileInputStream("Resources/Other NPCs/body_peasant.gm1/0_0img99.png")));
            images.put("dust" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection140.png")));
            images.put("dust2" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection138.png")));
            images.put("dust3" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection329.png")));
            images.put("sea" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection43.png")));
            images.put("mount1" , new Image(new FileInputStream("Resources/Tiles2/tile_land3.gm1/collection0.png")));
            images.put("mount2" , new Image(new FileInputStream("Resources/Tiles2/tile_land3.gm1/collection1.png")));
            images.put("mount3" , new Image(new FileInputStream("Resources/Tiles2/tile_land3.gm1/collection2.png")));
            images.put("tile4" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection140-4x4.png")));
            images.put("grass" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection505.png")));
            images.put("toolbar" , new Image(new FileInputStream("Resources/Misc/face800-blank.gm1/0_0img0.png")));
            images.put("toolbar-fill" , new Image(new FileInputStream("Resources/Misc/edge1024l.tgx.png")));
            images.put("toolbar-btn-defense" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img7.png")));
            images.put("toolbar-btn-industry" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img10.png")));
            images.put("toolbar-btn-farm" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img13.png")));
            images.put("toolbar-btn-townBuilding" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img16.png")));
            images.put("toolbar-btn-home" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img16.png")));
            images.put("toolbar-btn-military" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img19.png")));
            images.put("toolbar-btn-food" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img22.png")));


            //btnDefense Contents
            images.put("toolbar-btn-defense-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img18.png")));
            images.put("toolbar-btn-defense-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img14.png")));
            images.put("toolbar-btn-defense-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img16.png")));
            images.put("toolbar-btn-defense-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img12.png")));
            images.put("toolbar-btn-defense-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img98.png")));
            images.put("toolbar-btn-defense-6" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img96.png")));
            images.put("toolbar-btn-defense-7" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img100.png")));



            //actual defense buildings
//            images.put("building-defense-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img18.png")));
//            images.put("building-defense-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img14.png")));
//            images.put("building-defense-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img16.png")));
//            images.put("building-defense-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img12.png")));
            images.put("building-defense-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection25.png")));
            images.put("building-defense-6" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection21.png")));
            images.put("building-defense-7" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection23.png")));

            //btnIndustry Contents
            images.put("toolbar-btn-industry-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img38.png")));
            images.put("toolbar-btn-industry-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img36.png")));
            images.put("toolbar-btn-industry-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img40.png")));
            images.put("toolbar-btn-industry-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img42.png")));
            images.put("toolbar-btn-industry-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img46.png")));
            images.put("toolbar-btn-industry-6" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img44.png")));
            images.put("toolbar-btn-industry-7" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img34.png")));

            //actual industry buildings
            images.put("building-industry-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection2222.png")));
            images.put("building-industry-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection0000.png")));
            images.put("building-industry-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection16.png")));
//            images.put("building-defense-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection45.png")));
            images.put("building-industry-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection45.png")));
            images.put("building-industry-6" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection47.png")));
            images.put("building-industry-7" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection48.png")));
            //btnFarm Contents
            images.put("toolbar-btn-farm-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img22.png")));
            images.put("toolbar-btn-farm-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img114.png")));
            images.put("toolbar-btn-farm-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img112.png")));
            images.put("toolbar-btn-farm-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img110.png")));
            images.put("toolbar-btn-farm-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img108.png")));



            //actual farm buildings
            images.put("building-farm-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection50.png")));
            images.put("building-farm-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection42.png")));
            images.put("building-farm-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection41.png")));
            images.put("building-farm-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection39.png")));
            images.put("building-farm-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection40.png")));


            //btnHome Contents
            images.put("toolbar-btn-home-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img48.png")));
            images.put("toolbar-btn-home-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img128.png")));
            images.put("toolbar-btn-home-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img126.png")));
            images.put("toolbar-btn-home-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img130.png")));
            images.put("toolbar-btn-home-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img154.png")));

            //actual townBuilding buildings
            images.put("building-home-1-0" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection62.png")));
            images.put("building-home-1-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection63.png")));
            images.put("building-home-1-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection64.png")));
            images.put("building-home-1-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection65.png")));
            images.put("building-home-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection2.png")));
            images.put("building-home-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection1.png")));
            images.put("building-home-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection0.png")));
            images.put("building-home-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection55.png")));



            //btnMilitary Contents
            images.put("toolbar-btn-military-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img124.png")));
            images.put("toolbar-btn-military-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img116.png")));
            images.put("toolbar-btn-military-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img122.png")));
            images.put("toolbar-btn-military-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img120.png")));
            images.put("toolbar-btn-military-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img118.png")));

            //actual military buildings
            images.put("building-military-1" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection0.png")));
            images.put("building-military-2" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection36.png")));
            images.put("building-military-3" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection72.png")));
            images.put("building-military-4" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection108.png")));
            images.put("building-military-5" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection90.png")));


            //btnFood Contents
            images.put("toolbar-btn-food-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img24.png")));
            images.put("toolbar-btn-food-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img28.png")));
            images.put("toolbar-btn-food-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img30.png")));
            images.put("toolbar-btn-food-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img26.png")));
            images.put("toolbar-btn-food-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img32.png")));

            //actual food buildings
            images.put("building-food-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection22.png")));
            images.put("building-food-2" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection18.png")));
            images.put("building-food-3" , new Image(new FileInputStream("Resources/Tiles2/tile_workshops.gm1/collection54.png")));
            images.put("building-food-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection18.png")));
            images.put("building-food-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection43.png")));


            //resource icons
            images.put("wood" ,             new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img45.png")));
            images.put("stone" ,            new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img50.png")));
            images.put("iron" ,             new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img53.png")));
            images.put("wheat" ,            new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img59.png")));
            images.put("hop" ,              new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img47.png")));
            images.put("bread" ,            new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img61.png")));
            images.put("cheese" ,           new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img64.png")));
            images.put("meat" ,             new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img65.png")));
            images.put("apple" ,            new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img67.png")));
            images.put("brew" ,           new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img69.png")));
            images.put("flour" ,           new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img73.png")));
            images.put("gold" ,             new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img71.png")));
            images.put("bow" ,              new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img75.png")));
            images.put("axe" ,              new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img81.png")));
            images.put("sword" ,            new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img85.png")));
            images.put("leather_armor" ,    new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img87.png")));
            images.put("metal_armor" ,      new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img89.png")));
            images.put("market-food" ,      new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img93.png")));
            images.put("market-resource" , new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img95.png")));
            images.put("market-armor" ,     new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img97.png")));

            //unit icons
            images.put("soldier-arabianArcher" ,    new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img447.png")));
            images.put("soldier-arabianSwordMan" ,  new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img452.png")));
            images.put("soldier-archer" ,           new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img336.png")));
            images.put("soldier-axeMan" ,           new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img340.png")));
            images.put("soldier-swordMan" ,         new Image(new FileInputStream("Resources/Misc/interface_icons2.gm1/0_0img341.png")));



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            sounds.put("empty-granary" , new Media(new File("Resources/Sounds/emptyGranary.mp3").toURI().toString()));
            sounds.put("full-granary" , new Media(new File("Resources/Sounds/fullGranary.mp3").toURI().toString()));
            sounds.put("full-armory" , new Media(new File("Resources/Sounds/fullArmory.mp3").toURI().toString()));
            sounds.put("full-stockpile" , new Media(new File("Resources/Sounds/fullStockPile.mp3").toURI().toString()));
            sounds.put("need-gold" , new Media(new File("Resources/Sounds/goldNeeded.mp3").toURI().toString()));
            sounds.put("need-iron" , new Media(new File("Resources/Sounds/ironNeeded.mp3").toURI().toString()));
            sounds.put("need-stone" , new Media(new File("Resources/Sounds/stoneNeeded.mp3").toURI().toString()));
            sounds.put("need-wood" , new Media(new File("Resources/Sounds/woodNeeded.mp3").toURI().toString()));
            sounds.put("no-workers" , new Media(new File("Resources/Sounds/notEnoughWorkers.mp3").toURI().toString()));
            sounds.put("no-armory" , new Media(new File("Resources/Sounds/noArmory.mp3").toURI().toString()));
            sounds.put("no-granary" , new Media(new File("Resources/Sounds/noGranary.mp3").toURI().toString()));
            sounds.put("placement-warning" , new Media(new File("Resources/Sounds/PlacementWarning16.mp3").toURI().toString()));
            sounds.put("hire-unit-warning" , new Media(new File("Resources/Sounds/UnitsWarning.mp3").toURI().toString()));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
