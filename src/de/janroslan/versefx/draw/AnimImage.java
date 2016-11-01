/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.draw;

import de.janroslan.versefx.draw.AnimationState;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jackjan
 */
public class AnimImage extends DrawImage {

    private int animTime;

    private double elapsed;

    private AnimationState animState;

    int pointer;

    private int columns;
    private int rows;

    private int subX;
    private int subY;

    /**
     * Constructor for images that should stay in their aspect ration but can be
     * edited in their size by a size ratio
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param columns
     * @param rows
     * @param ratio
     * @param time
     * @param layer
     */
    public AnimImage(String tag, int startX, int startY, Image img, int columns, int rows, double ratio, int layer, int time) {
        this(tag, startX, startY, img, columns, rows, (int) (img.getWidth() * ratio), (int) (img.getHeight() * ratio), layer, time);
    }

    /**
     * Constructor for quadratic images
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param subs
     * @param length
     * @param layer
     * @param time
     */
    public AnimImage(String tag, int startX, int startY, Image img, int subs, int length, int layer, int time) {
        this(tag, startX, startY, img, subs, subs, length, length, layer, time);
    }

    /**
     * Primary constructor
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param columns
     * @param rows
     * @param width
     * @param height
     * @param layer
     * @param time
     */
    public AnimImage(String tag, int startX, int startY, Image img, int columns, int rows, int width, int height, int layer, int time) {
        super(tag, startX, startY, new ImageView(img), width, height, layer, time);

        pointer = 0;
        this.columns = columns;
        this.rows = rows;

        this.subX = (int) img.getWidth() / columns;
        this.subY = (int) img.getHeight() / rows;

        animTime = 1000;
        
        getBounds().setWidth(subX * getScaleX());
        getBounds().setHeight(subY * getScaleY());

        getImageView().setViewport(new Rectangle2D(0, 0, subX, subY));
    }

    /**
     * Sets the animation speed for a complete animation cycle to the given time
     * in milliseconds The default is one second for a cycle
     *
     * @param time
     */
    public void setAnimSpeed(int time) {
        animTime = time;
    }

    public void startAnim() {
        animState = AnimationState.RUNNING;

    }

    public void pauseAnim() {
        animState = AnimationState.PAUSED;
    }

    public void stopAnim() {
        animState = AnimationState.STOPPED;
    }

    /**
     * Changes the view to the given frame. Frames are counted from left to
     * right & top to bottom
     *
     * @param frame
     */
    public void goToFrame(int frame) {
        animState = AnimationState.PAUSED;
        pointer = frame;

        int posX = pointer % columns;
        int posY = pointer / columns;

        getImageView().setViewport(new Rectangle2D(posX * subX, posY * subY, subX, subY));

    }

    @Override
    protected final void update(float deltaT) {
        if (animState == AnimationState.RUNNING && columns > 1) {

            elapsed += deltaT;
            double timePerFrame = animTime / (columns * rows);

            pointer = (int) (elapsed / timePerFrame);
            int posX = pointer % columns;
            int posY = pointer / columns;

            if (pointer > columns * rows - 1) {
                pointer = 0;
                elapsed = 0;
            }
            
            getImageView().setViewport(new Rectangle2D(posX * subX, posY * subY, subX, subY));

        }

        refresh(deltaT);
    }

    protected void refresh(float deltaT) {

    }

}
