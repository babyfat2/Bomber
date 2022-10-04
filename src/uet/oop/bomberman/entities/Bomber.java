package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.event.KeyEvent;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void move(KeyEvent a) {
        switch (a.getKeyCode()) {
            case KeyEvent.VK_KP_DOWN:
                y++;
                break;
            case KeyEvent.VK_KP_UP:
                y--;
                break;
            case KeyEvent.VK_KP_LEFT:
                x--;
                break;
            case KeyEvent.VK_KP_RIGHT:
                x++;
                break;
        }
    }

}
