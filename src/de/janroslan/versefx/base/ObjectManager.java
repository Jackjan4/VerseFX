/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.base;

import de.janroslan.versefx.base.BasicNode;
import de.janroslan.versefx.physics.BoundingBox;
import de.janroslan.versefx.physics.Collidable;
import java.util.ArrayList;

/**
 *
 * @author Jackjan
 */
public interface ObjectManager {
    
    public void destroyObject(BasicNode o);
    
    public ArrayList<Collidable> getCollisions(Collidable o);
    
    public void addToColQueue(BasicNode o, BoundingBox newPos);
    
    public void addObject(BasicNode o);
}
