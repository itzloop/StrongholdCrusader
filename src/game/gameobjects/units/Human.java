package game.gameobjects.units;

import game.AssetManager;
import game.GV;
import game.gameobjects.GameObject;
import game.map.Map;
import game.map.Tile;
import game.map.TileType;
import game.map.Vector2D;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.swing.text.html.ImageView;
import java.util.*;

public class Human extends GameObject {
    public Human(Vector2D location){
        super("" , location);
    }

    public void move(Tile b)  {
       Stack<Tile> path = AStar(b);
        System.out.println(path);
        if(Optional.ofNullable(path).isPresent()) {
            while (!path.isEmpty()) {
                Tile tile = path.pop();
                Platform.runLater(() -> {
                    this.setX(tile.getPos().getX());
                    this.setY(tile.getPos().getY());
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            System.out.println("no path is found");
        }
    }




        private Stack<Tile> drawPath(java.util.Map<Tile , Tile> cameFrom , Tile current ){
            Stack<Tile> totalPath = new Stack<>();
            totalPath.push(current);
            while (cameFrom.containsKey(current) )
            {
                current = cameFrom.get(current);
                totalPath.push(current);
            }
            return totalPath;

        }
        public Stack<Tile> AStar( Tile target )
        {
            Queue<Tile> open = new PriorityQueue<>();
            Queue<Tile> close = new PriorityQueue<>();
            java.util.Map<Tile , Tile> cameFrom = new HashMap<>();
            open.add(getOn());
            getOn().setG(0);
            getOn().setF(getOn().getPos().distance(target.getPos()));
            Tile current;
            while (!open.isEmpty())
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                current = open.poll();
                if (current.equals(target))
                {
                    return drawPath(cameFrom , current );
                }
                close.add(current);
                for (Tile neighbor : Map.adjList.get(current))
                {
                    //TODO fix this later
                    if(true) {
                        if (close.contains(neighbor)) {
                            neighbor.setImage(AssetManager.images.get("dust"));
                            continue;
                        }
                        double tentativeGScore = current.getG() + current.getPos().distance(neighbor.getPos());
                        if (!open.contains(neighbor))
                            open.add(neighbor);
                        else if (tentativeGScore >= neighbor.getG()) {
                            neighbor.setImage(AssetManager.images.get("dust"));
                            continue;
                        }
                        neighbor.setImage(AssetManager.images.get("grass"));
                        cameFrom.put(neighbor, current);
                        neighbor.setG(tentativeGScore);
                        neighbor.setF(neighbor.getG() + neighbor.getPos().distance(target.getPos()));
                    }
                }
            }
            return null;

        }






}
