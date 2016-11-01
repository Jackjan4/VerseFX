/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.draw;

import de.janroslan.versefx.physics.BoundingBox;
import javafx.scene.image.Image;

/**
 *
 * @author Jackjan
 */
public class Object2D extends DrawObject  {
    
    public Object2D(String id, Image texture, double startX, double startY, int width, int height, int layer) {
        super(id, new BoundingBox(startX, startY,layer,width,height,0), Tile2D.createMesh(startX, startY, layer, width, height, texture),5000);
    }

    public Object2D(String id, Image texture, double startX, double startY, int length, int layer) {
        super(id, new BoundingBox(startX, startY,layer,length,length,0), Tile2D.createMesh(startX, startY, layer, length, length, texture),5000);
    }

    public Object2D(String id, Image texture, double startX, double startY, double ratio, int layer) {
        super(id, new BoundingBox(startX, startY,layer,(int) (texture.getWidth() * ratio), (int) (texture.getHeight() * ratio), 0), Tile2D.createMesh(startX, startY, layer, (int) (texture.getWidth() * ratio), (int) (texture.getHeight() * ratio), texture),5000);
    }
    
    @Override
    protected void update(float deltaT)
    {
        
    }
    
    
}
