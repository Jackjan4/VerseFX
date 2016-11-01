/*
 * Copyright 2016, Jan-Philipp Roslan, Alle Rechte vorbehalten
 */
package de.janroslan.versefx.draw;

import de.janroslan.versefx.base.ObjectManager;
import de.janroslan.versefx.base.BasicNode;
import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.BoundingBox;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Jackjan
 */
public abstract class DrawObject extends BasicNode implements Collidable
{
    private MeshView mesh;
 
    private ObjectManager man;
    
    // An Axis Aligned BoundingBox
    protected BoundingBox box;
    
    

    public DrawObject(String tag, BoundingBox box, MeshView mesh, int time)
    {
        super(tag,mesh,time);
        this.tag = tag;
        this.box = box;
        this.mesh = mesh;
        
    }
    
    public void moveCol(double x, double y, double z)
    {
       mesh.setTranslateX(mesh.getTranslateX() + x);
       mesh.setTranslateY(mesh.getTranslateY() + y);
       mesh.setTranslateZ(mesh.getTranslateZ() + z);
       box.setX(mesh.getTranslateX() + x);
       box.setY(mesh.getTranslateY() + y);
       box.setZ(mesh.getTranslateZ() + z);
    }
    
      
    
}
