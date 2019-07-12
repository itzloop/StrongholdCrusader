package game;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    public static Map<String , Image> assets = new HashMap<>();

    static {
        try {
            assets.put("building" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection52.png")));
            assets.put("shop" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection48.png")));
            assets.put("dust" , new Image(new FileInputStream("Resources/Tiles2/tile_land_macros.gm1/collection140.png")));
            assets.put("toolbar" , new Image(new FileInputStream("Resources/Misc/face800-blank.gm1/0_0img0.png")));
            assets.put("toolbar-fill" , new Image(new FileInputStream("Resources/Misc/edge1024l.tgx.png")));
            assets.put("toolbar-btn-defense" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img7.png")));
            assets.put("toolbar-content-defense" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img7.png")));
            assets.put("toolbar-btn-industry" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img10.png")));
            assets.put("toolbar-btn-farm" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img13.png")));
            assets.put("toolbar-btn-home" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img16.png")));
            assets.put("toolbar-btn-military" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img19.png")));
            assets.put("toolbar-btn-food" , new Image(new FileInputStream("Resources/Misc/interface_buttons.gm1/0_0img22.png")));


            //btnDefense Contents
            assets.put("toolbar-btn-defense-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img18.png")));
            assets.put("toolbar-btn-defense-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img14.png")));
            assets.put("toolbar-btn-defense-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img16.png")));
            assets.put("toolbar-btn-defense-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img12.png")));
            assets.put("toolbar-btn-defense-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img98.png")));
            assets.put("toolbar-btn-defense-6" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img96.png")));
            assets.put("toolbar-btn-defense-7" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img100.png")));



            //actual defense buildings
//            assets.put("building-defense-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img18.png")));
//            assets.put("building-defense-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img14.png")));
//            assets.put("building-defense-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img16.png")));
//            assets.put("building-defense-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img12.png")));
            assets.put("building-defense-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection25.png")));
            assets.put("building-defense-6" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection21.png")));
            assets.put("building-defense-7" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection23.png")));

            //btnIndustry Contents
            assets.put("toolbar-btn-industry-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img38.png")));
            assets.put("toolbar-btn-industry-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img36.png")));
            assets.put("toolbar-btn-industry-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img40.png")));
            assets.put("toolbar-btn-industry-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img42.png")));
            assets.put("toolbar-btn-industry-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img46.png")));
            assets.put("toolbar-btn-industry-6" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img44.png")));
            assets.put("toolbar-btn-industry-7" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img34.png")));

            //actual industry buildings
            assets.put("building-industry-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection2222.png")));
            assets.put("building-industry-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection0000.png")));
            assets.put("building-industry-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection16.png")));
//            assets.put("building-defense-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection45.png")));
            assets.put("building-industry-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection45.png")));
            assets.put("building-industry-6" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection47.png")));
            assets.put("building-industry-7" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection47.png")));
            //btnFarm Contents
            assets.put("toolbar-btn-farm-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img22.png")));
            assets.put("toolbar-btn-farm-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img114.png")));
            assets.put("toolbar-btn-farm-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img112.png")));
            assets.put("toolbar-btn-farm-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img110.png")));
            assets.put("toolbar-btn-farm-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img108.png")));

            //actual farm buildings
            assets.put("building-farm-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection50.png")));
            assets.put("building-farm-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection42.png")));
            assets.put("building-farm-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection41.png")));
            assets.put("building-farm-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection39.png")));
            assets.put("building-farm-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection40.png")));


            //btnHome Contents
            assets.put("toolbar-btn-home-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img48.png")));
            assets.put("toolbar-btn-home-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img126.png")));
            assets.put("toolbar-btn-home-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img128.png")));
            assets.put("toolbar-btn-home-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img130.png")));
            assets.put("toolbar-btn-home-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img154.png")));

            //actual home buildings
            assets.put("building-home-1-0" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection62.png")));
            assets.put("building-home-1-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection63.png")));
            assets.put("building-home-1-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection64.png")));
            assets.put("building-home-1-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection65.png")));
            assets.put("building-home-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection2.png")));
            assets.put("building-home-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection1.png")));
            assets.put("building-home-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection0.png")));
            assets.put("building-home-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection55.png")));



            //btnMilitary Contents
            assets.put("toolbar-btn-military-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img124.png")));
            assets.put("toolbar-btn-military-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img116.png")));
            assets.put("toolbar-btn-military-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img122.png")));
            assets.put("toolbar-btn-military-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img120.png")));
            assets.put("toolbar-btn-military-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img118.png")));

            //actual military buildings
            assets.put("building-military-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST12_Fletchers_Workshop.tgx.png")));
            assets.put("building-military-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST14_Poleturners_Workshop.tgx.png")));
            assets.put("building-military-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST13_Blacksmiths_Workshop.tgx.png")));
            assets.put("building-military-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST16_Tanners_Workshop.tgx.png")));
            assets.put("building-military-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST15_Armourers_Workshop.tgx.png")));


            //btnFood Contents
            assets.put("toolbar-btn-food-1" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img24.png")));
            assets.put("toolbar-btn-food-2" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img28.png")));
            assets.put("toolbar-btn-food-3" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img30.png")));
            assets.put("toolbar-btn-food-4" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img26.png")));
            assets.put("toolbar-btn-food-5" , new Image(new FileInputStream("Resources/Misc/icons_placeholders.gm1/0_0img32.png")));

            //actual food buildings
            assets.put("building-food-1" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection22.png")));
            assets.put("building-food-2" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST17_Bakers_Workshop.tgx.png")));
            assets.put("building-food-3" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/ST18_Brewers_Workshop.tgx.png")));
            assets.put("building-food-4" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection18.png")));
            assets.put("building-food-5" , new Image(new FileInputStream("Resources/Tiles2/tile_buildings2.gm1/collection43.png")));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
