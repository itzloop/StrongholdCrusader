package game.map.components;

import game.Game;
import game.gameobjects.GameObject;
import game.map.Vector2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.text.html.ImageView;
import java.util.Iterator;
import java.util.List;

public class DragComponent {
    private Pane pane;

    public DragComponent(Pane pane )
    {
        this.pane = pane;
        Vector2D dragStartPosition = new Vector2D(0 , 0);
        Rectangle rectangle =  new Rectangle();
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(2);
        rectangle.setVisible(false);
        pane.getChildren().add(rectangle);
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED , event -> {
            dragStartPosition.setX(event.getX());
            dragStartPosition.setY(event.getY());
            rectangle.setX(event.getX());
            rectangle.setY(event.getY());
            rectangle.setFill(null); // transparent
            rectangle.setStroke(Color.GREEN); // border
            rectangle.setStrokeWidth(3);
            rectangle.getStrokeDashArray().add(15.0);
            rectangle.toFront();
        });

        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED , event -> {
            rectangle.setVisible(true);
            rectangle.setWidth(Math.abs(event.getX() - dragStartPosition.getX()));
            rectangle.setHeight(Math.abs(event.getY() - dragStartPosition.getY()));
            rectangle.setX(Math.min(dragStartPosition.getX(), event.getX()));
            rectangle.setY(Math.min(dragStartPosition.getY(), event.getY()));
        });
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED , event -> {
            rectangle.setVisible(false);



        });
    }



}
