/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.physics;

import de.janroslan.versefx.physics.BoundingBox;
import java.util.ArrayList;

/**
 *
 * @author Jackjan
 */
public interface Collidable {

    public BoundingBox getBounds();

    public CollisionType getType();

    public default boolean isIntersecting(Collidable col) {
        return getBounds().intersects(col);
    }
    
    public void intersects(Collidable col);

    public String getTag();

    public void setColType(CollisionType type);

    public ArrayList<Collidable> getCollisions();
}
