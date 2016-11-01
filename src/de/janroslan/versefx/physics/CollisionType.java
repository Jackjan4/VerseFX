/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.physics;

/**
 * 
 * @author Jackjan
 */
public enum CollisionType {
    
    Sphere(3),
    AABox2D(5),
    AABox3D(7),
    OOB(9);
    
    
    private final int val;
    
    
    CollisionType(int numVal) {
        this.val = numVal;
    }

    public int getVal() {
        return val;
    }
    
}
