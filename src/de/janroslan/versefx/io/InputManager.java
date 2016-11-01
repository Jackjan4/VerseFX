/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.io;

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Jackjan
 */
public class InputManager implements Input {

    private final HashMap<KeyCode, Boolean> keyTable;
    private final HashMap<MouseButton, Boolean> mouseTable;
    
    private double mouseX;
    private double mouseY;
    
    private static Input input;

    public InputManager() {
        keyTable = new HashMap<>(50);
        mouseTable = new HashMap<>();
    }
    
    public void init()
    {
        this.input = this;
    }
    
    public static Input getGlobalInput()
    {
        return input;
    }
    
    public void registerKeyDown(KeyCode key)
    {
        keyTable.put(key, true);
    }
    
    public void registerKeyUp(KeyCode key)
    {
        keyTable.put(key, false);
    }

    @Override
    public boolean IsKeyDown(KeyCode key) {
        Boolean b = keyTable.get(key);
        if (b == null) {
            return false;
        }

        return b;
    }
    
    public void registerMousePress(MouseButton btn)
    {
        mouseTable.put(btn, true);
    }

    public void registerMouseRelease(MouseButton btn)
    {
        mouseTable.put(btn, false);
    }
    
    public void registerMousePos(double x, double y)
    {
        this.mouseX = x;
        this.mouseY = y;
    }
    
    public Point2D getMousePos()
    {
        return new Point2D(mouseX, mouseY);
    }
    
}
