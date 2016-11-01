/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.base;

import de.janroslan.versefx.base.Level;
import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.base.ObjectBatch;
import javafx.scene.Group;
import org.dyn4j.dynamics.World;

/**
 * Singleton LevelLoader
 *
 * @author Jackjan
 */
public class LevelLoader {
    
    private static LevelLoader obj;
    
    private Level currentLvl;
    private Group group;
    private ObjectBatch currentBatch;
    private World world;
    
    private boolean isTicking;
    
    private boolean autoUpdate;
    private boolean colCheckPerFrame;

    /**
     *
     * @return
     */
    public static LevelLoader getInstance() {
        return obj;
    }

    /**
     * Generating a LevelLoader with the specified root
     *
     * @param root
     * @return
     */
    public static LevelLoader getInstance(Group root) {
        return obj = new LevelLoader(root, null);
    }
    
    /**
     * Generating a LevelLoader with the specified root
     *
     * @param root
     * @param world
     * @return
     */
    public static LevelLoader getInstance(Group root, World world) {
        return obj = new LevelLoader(root,world);
    }
    
    private LevelLoader(Group root, World world) {
        this.group = root;
        this.world = world;
    }
    
    public void loadLevel(String levelRes) {
        stopTicking();
        
    }
    
    public void loadLevel(Level lvl) {
        stopTicking();
        autoUpdate = false;
        currentLvl = lvl;
        currentBatch = lvl.initLevel(group);
        
        startTicking();
    }

    /**
     *
     * @param lvl
     * @param autoUpdate
     * @param colCheckPerFrame
     */
    public void loadLevel(Level lvl, boolean autoUpdate, boolean colCheckPerFrame) {
        stopTicking();
        this.autoUpdate = autoUpdate;
        currentLvl = lvl;
        currentBatch = lvl.initLevel(group, world);
        this.colCheckPerFrame = colCheckPerFrame;
        
        startTicking();
    }
    
    public final void tick(float deltaT) {
        if (isTicking) {
            
            // Collision check
            if (colCheckPerFrame) {
                currentBatch.getCollisionDict().forEach((k, v) -> {
                    v.forEach((object, targets) -> {
                        for (String s : targets) {
                            currentBatch.getCollisionDict().get(s).forEach((a, b) -> {
                                if (object.isIntersecting(a)) {
                                    object.intersects(a);
                                }
                            });
                        }
                    });
                });
            }
            
            
            
            // Update level itself
            currentLvl.tick(deltaT);
        }
        
    }
    
    public void startTicking() {
        isTicking = true;
    }
    
    public void stopTicking() {
        isTicking = false;
    }
    
}
