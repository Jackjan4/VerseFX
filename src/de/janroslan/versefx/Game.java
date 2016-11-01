/*
 * Copyright 2016, Jan-Philipp Roslan, Alle Rechte vorbehalten
 */
package de.janroslan.versefx;

import de.janroslan.versefx.base.FXGame;
import de.janroslan.versefx.base.LevelLoader;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dyn4j.collision.AbstractBounds;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.Capacity;
import org.dyn4j.dynamics.World;

/**
 *
 * @author Jackjan
 */
public class Game implements FXGame
{
    private LevelLoader level;
    
    public static final int W_WIDTH = 1280;
    public static final int W_HEIGHT = 720;
    
    private World world;
    
    /**
     * Inits the game itself
     * @param stage 
     */
    @Override
    public void init(Stage stage)
    {
        Group root = new Group();
        
        // Camera settings
        ParallelCamera cam = new ParallelCamera();
        cam.setNearClip(0.1);
        cam.setFarClip(2000.0);
        
        // Scene settings
        Scene scene = new Scene(root,W_WIDTH,W_HEIGHT,true);
        scene.setCamera(cam);

        // Physic world init
        world = new World();
        
        // LevelLoader init
        this.level  = LevelLoader.getInstance(root, world);
        
        
        
        // Stage settings
        stage.setWidth(W_WIDTH);
        stage.setHeight(W_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Verse - Jan-Philipp Roslan");
        
        stage.show();
    }
    
    
    /**
     * Loads the game content
     */
    @Override
    public void loadContent()
    {
        level.loadLevel(,false,false);
    }
    
    
    /**
     * The main update loop
     * @param deltaT
     */
    @Override
    public void update(float deltaT)
    {
        level.tick(deltaT);
        world.update(deltaT / 1000.0);
    }
}
