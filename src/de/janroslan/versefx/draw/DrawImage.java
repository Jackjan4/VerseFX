/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.draw;

import de.janroslan.versefx.base.BasicNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A 2D object based on JavaFX-ImageView instead of being an actual 3D Mesh. Has
 * better performance, lacks however 3D features Can be animated by a tile based
 * animation
 *
 * @author jackjan
 */
public class DrawImage extends BasicNode {

    /**
     * Primary constructor -
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param width
     * @param height
     * @param layer
     * @param time
     */
    public DrawImage(String tag, int startX, int startY, ImageView img, int width, int height, int layer, int time) {
        super(tag, img, time);
        
        scaleX(width / getWidth());
        scaleY(height / getHeight());
        
        setX(startX);
        setY(startY);
        setZ(layer);
    }

    public DrawImage(String tag, int startX, int startY, Image img, double ratio, int layer, int time) {
        this(tag, startX, startY, new ImageView(img), (int) (img.getWidth() * ratio), (int) (img.getHeight() * ratio), layer, time);

    }

    /**
     * Constructor for quadratic images
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param length
     * @param layer
     */
    public DrawImage(String tag, int startX, int startY, Image img, int length, int layer, int time) {
        this(tag, startX, startY, new ImageView(img), length, length, layer, time);

    }

    public final ImageView getImageView() {
        return (ImageView) getNode();
    }

    @Override
    protected void update(float deltaT) {
        
    }

}
