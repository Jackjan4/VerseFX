/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.draw;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 *
 * @author Jackjan
 */
public class Tile2D {
    
    /**
     * 
     * @param x
     * @param y
     * @param layer
     * @param length
     * @param texture
     * @return 
     */
    public static MeshView createMesh(double x, double y, int layer,int length, Image texture)
    {
       TriangleMesh triMesh = new TriangleMesh();
        triMesh.getTexCoords().addAll(
        0,0,
        0,1,
        1,1,
        1,0);

        triMesh.getPoints().addAll(
                0, 0, 0, // 0
                0, length, 0, // 1
                length, length, 0, // 2
                length, 0, 0); // 3

        triMesh.getFaces().addAll(
                0, 0, 1, 1, 3, 3,
                3, 3, 1, 1, 2, 2);

        MeshView meshView = new MeshView(triMesh);
        meshView.setDrawMode(DrawMode.FILL);
        PhongMaterial mat = new PhongMaterial(Color.WHITE);
        mat.setDiffuseMap(texture);
        meshView.setMaterial(mat);
        meshView.setTranslateX(x);
        meshView.setTranslateY(y);
        meshView.setTranslateZ(layer);
        
       return meshView;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param layer
     * @param lengthX
     * @param lengthY
     * @param texture
     * @return 
     */
    public static MeshView createMesh(double x, double y, int layer,int lengthX,int lengthY, Image texture)
    {
       TriangleMesh triMesh = new TriangleMesh();
        triMesh.getTexCoords().addAll(
        0,0,
        0,1,
        1,1,
        1,0);

        triMesh.getPoints().addAll(
                0, 0, 0, // 0
                0, lengthY, 0, // 1
                lengthX, lengthY, 0, // 2
                lengthX, 0, 0); // 3

        triMesh.getFaces().addAll(
                0, 0, 1, 1, 3, 3,
                3, 3, 1, 1, 2, 2);

        MeshView meshView = new MeshView(triMesh);
        meshView.setDrawMode(DrawMode.FILL);
        PhongMaterial mat = new PhongMaterial(Color.WHITE);
        mat.setDiffuseMap(texture);
        meshView.setMaterial(mat);
        meshView.setTranslateX(x);
        meshView.setTranslateY(y);
        meshView.setTranslateZ(layer);
        
       return meshView;
    }
}
