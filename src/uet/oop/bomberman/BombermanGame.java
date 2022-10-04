package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    /**
     * khoi tao man hinh.
     */
    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        try {
            createMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * tao map.
     */
    public void createMap() throws IOException {
        File file = new File("res\\levels\\Level1.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        int widthMap = 31;
        int heightMap = 13;
        for (int i = 0; i < heightMap; i++) {
            String line1 = reader.readLine();
            for (int j = 0; j < widthMap; j++) {
                Entity object;
                char s = line1.charAt(j);
                if (s == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (s == '*') {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                    Entity Brick = new Brick(j,i,Sprite.brick.getFxImage());
                    entities.add(Brick);
                } else if (s == ' ') {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                } else if (s == '1') {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                    Entity Balloom = new Balloom(j,i,Sprite.balloom_left1.getFxImage());
                    entities.add (Balloom);
                } else if (s == '2') {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                } else if (s == 'p') {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                    Entity bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                    entities.add(bomberman);
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
