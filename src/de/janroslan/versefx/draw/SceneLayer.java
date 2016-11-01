/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.draw;

/**
 *
 * @author jackjan
 */
public enum SceneLayer {
    Background(1),
    GUI(10);
    
    
    
    private final int val;
    
    
    SceneLayer(int numVal) {
        this.val = numVal;
    }

    public int getVal() {
        return val;
    }
}
