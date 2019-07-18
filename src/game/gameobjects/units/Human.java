package game.gameobjects.units;

import game.AssetManager;
import game.GV;
import game.gameobjects.GameObject;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.map.Map;
import game.map.Tile;
import javafx.animation.Timeline;
import javafx.application.Platform;

import java.util.*;

public class Human extends GameObject {
    public Human(){

        super("body" );
        setGameObjectHelper(new GameObjectHelper( "building-castle" , getObjectId() , GameobjectType.CASTLE ));
    }
    private static boolean isRunning;

    public void move(Tile b)  {
        isRunning = false;
        Stack<Tile> path = AStar(b);
        try {
            Thread.sleep(501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = true;
        System.out.println(path);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        if(Optional.ofNullable(path).isPresent()) {
            while (!path.isEmpty() && isRunning) {
                Tile tile = path.pop();
                Platform.runLater(() -> {
                    setTile(tile);
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
            open.add(getTile());
            getTile().setG(0);
            getTile().setF(getTile().getPos().distance(target.getPos()));
            Tile current;
            while (!open.isEmpty())
            {
                current = open.poll();
                if (current.equals(target))
                {
                    return drawPath(cameFrom , current );
                }
                close.add(current);
                for (Tile neighbor : Map.adjList.get(current))
                {
                    //TODO fix this later
                    if(!neighbor.isObstacle() && neighbor.getOccupiedType().equals(GameobjectType.EMPTY)) {
                        if (close.contains(neighbor)) {
                            continue;
                        }
                        double tentativeGScore = current.getG() + current.getPos().distance(neighbor.getPos());
                        if (!open.contains(neighbor))
                            open.add(neighbor);
                        else if (tentativeGScore >= neighbor.getG()) {
                            continue;
                        };
                        cameFrom.put(neighbor, current);
                        neighbor.setG(tentativeGScore);
                        neighbor.setF(neighbor.getG() + neighbor.getPos().distance(target.getPos()));
                    }
                }
            }
            return null;

        }






}
