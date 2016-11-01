/*
 * Copyright 2016, Jan-Philipp Roslan, Alle Rechte vorbehalten
 */
package de.janroslan.versefx;

import de.janroslan.versefx.io.InputManager;
import de.janroslan.versefx.base.FXGame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jackjan
 */
public class Main extends Application {
    

    @Override
    public void start(Stage primaryStage) {

        FXGame game = new Game();
        game.init(primaryStage);
        game.loadContent();
        
        InputManager input = new InputManager();
        input.init();

        // Key down event register
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            input.registerKeyDown(event.getCode());
        });
        
        // Key Up event registr
        primaryStage.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            input.registerKeyUp(event.getCode());
        });
        
        // Mouse press event register
        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            input.registerMousePress(event.getButton());
        });
        
        // Mouse release event register
        primaryStage.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            input.registerMouseRelease(event.getButton());
        });
        
        // Mouse pos register
        primaryStage.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            input.registerMousePos(event.getX(),event.getY());
        });
        
        // The timer for the main game loop
        AnimationTimer timer = new AnimationTimer()
        {
            private long last = 0;
            private long lastSystem = 0;
            
            @Override
            public void handle(long now) {
                game.update((float)((now - last) / 1000_000.0));
                //System.out.println("FPS: " + 1000 / ((now - last) / 1000_000.0));
                long nowSystem = System.currentTimeMillis();
                //System.out.println("FPS on System: " + (1000.0 / (nowSystem - lastSystem)));
                
                lastSystem = nowSystem;
                last = now;
            }
        };
        timer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Debug printings
        System.out.println("Graphic pipeline: " + com.sun.prism.GraphicsPipeline.getPipeline().getClass().getName());
        System.out.println("java.version : " + System.getProperty("java.version"));
        System.out.println("sun.arch.data.model : " + System.getProperty("sun.arch.data.model"));
        System.out.println("os.arch : " + System.getProperty("os.arch"));
        
        launch(args);
    }

}
