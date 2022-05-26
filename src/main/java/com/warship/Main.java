package com.warship;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 500);

        Circle enemy = createEnemy(scene);
        ImageView ship = createShip(scene);
        root.getChildren().addAll(ship, enemy);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            double x = ship.getX();
            double y = ship.getY();

            switch (event.getCode()){
                case RIGHT -> ship.setX(x + 10);
                case LEFT -> ship.setX(x - 10);
                case UP -> ship.setY(y - 10);
                case DOWN -> ship.setY(y + 10);
            }

            if(event.getCode() == KeyCode.SPACE){
                Circle bullet = new Circle(3);
                bullet.setCenterX(x + (ship.getFitWidth() / 2));
                bullet.setCenterY(y);

                TranslateTransition moveEnemy = new TranslateTransition(Duration.millis(2000), bullet);
                moveEnemy.setByY(scene.getHeight() * -1);
                moveEnemy.play();

                root.getChildren().add(bullet);
            }
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private Circle createEnemy(Scene scene) {
        int radius = 40;
        Circle enemy = new Circle(radius);
        enemy.setCenterY(radius);
        enemy.setCenterX(radius);
        enemy.setFill(Color.valueOf("EF5350"));
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), enemy);
        tt.setByX(scene.getWidth() - (radius * 2));
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.setAutoReverse(true);

        tt.play();

        return enemy;
    }

    private ImageView createShip(Scene scene) {
        ImageView image = new ImageView(new Image("ship.png"));
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setY(scene.getHeight() - image.getFitHeight());
        return image;
    }

    public static void main(String[] args) {
        launch();
    }
}