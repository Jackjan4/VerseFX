/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.base;

import de.janroslan.versefx.base.BasicNode;
import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.CollisionType;
import java.util.ArrayList;

/**
 *
 * @author jackjan
 */
public interface ObjectCollector {
    
    
    public void add(BasicNode... n);
    
    public void registerCollider(Collidable o, CollisionType type, String... targets);
    

    public void add(ArrayList<BasicNode> list);
}
