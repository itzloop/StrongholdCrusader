package game.map;

import game.GV;
import game.gameobjects.GameobjectType;
import javafx.scene.layout.Pane;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class MapLoader {
    public static Tile[][] load()
    {
        try {
            Tile[][] tiles = new Tile[100][100];
            File f = new File("Resources/Map/Map.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(f);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("tile");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)nNode;
                    int i = Integer.parseInt(element.getAttribute("y"));
                    int j = Integer.parseInt(element.getAttribute("x"));
                    int tileNum = Integer.parseInt(element.getAttribute("index"));
                    tileNum = tileNum == -1 ? 0:tileNum;
                    double x = j * GV.tileSize.getX() + (i % 2 == 0 ?  0:GV.tileSize.getX()/2);
                    double y = (i)*GV.tileSize.getY()/2;
                    tiles[i][j] = new Tile(TileType.valueOf(tileNum).get() , new Vector2D(j , i) , x, y);
                    tiles[i][j].setG(Double.MAX_VALUE);
                    tiles[i][j].setF(Double.MAX_VALUE);
                    tiles[i][j].setOccupiedType(GameobjectType.EMPTY);
                    Map.pane.getChildren().add(tiles[i][j]);
                }


            }
            return tiles;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        MapLoader.load();
    }

    public static Pane loadMiniMap()
    {
        try {
            Tile[][] tiles = new Tile[100][100];
            Pane pane = new Pane();
            File f = new File("Resources/Map/Map.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(f);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("tile");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)nNode;
                    int i = Integer.parseInt(element.getAttribute("y"));
                    int j = Integer.parseInt(element.getAttribute("x"));
                    int tileNum = Integer.parseInt(element.getAttribute("index"));
                    tileNum = tileNum == -1 ? 0:tileNum;
                    double x = j * GV.tileSizeMinimap.getX() + (i % 2 == 0 ?  0:GV.tileSizeMinimap.getX()/2);
                    double y = (i)*GV.tileSizeMinimap.getY()/2;
                    tiles[i][j] = new Tile(TileType.valueOf(tileNum).get() , new Vector2D(j , i) , x, y);
                    tiles[i][j].setG(Double.MAX_VALUE);
                    pane.getChildren().add(tiles[i][j]);
                }


            }
            return pane;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }



}
