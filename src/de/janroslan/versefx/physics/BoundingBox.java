/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.physics;

/**
 * the bounding box is a box collider
 *
 * @author Jackjan
 */
public class BoundingBox {

    private double x;
    private double y;
    private double z;
    private double width;
    private double height;
    private double depth;
    private double rotX;
    private double rotY;
    private double rotZ;
    private CollisionType type;

    public double getRotX() {
        return rotX;
    }

    public void setRotX(double rotX) {
        this.rotX = rotX;
    }

    public double getRotY() {
        return rotY;
    }

    public void setRotY(double rotY) {
        this.rotY = rotY;
    }

    public double getRotZ() {
        return rotZ;
    }

    public void setRotZ(double rotZ) {
        this.rotZ = rotZ;
    }

    
    public BoundingBox(double x, double y, double z, double width, double height, double depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    
    // Copy constructor
    public BoundingBox(BoundingBox ori)
    {
        this.x = ori.x;
        this.y = ori.y;
        this.z = ori.z;
        this.width = ori.width;
        this.height = ori.height;
        this.depth = ori.depth;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setWidth(double val) {
        this.width = val;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    /**
     * 2D AABox collision
     * @param col
     * @return
     */
    private boolean intersectsAABoxAABox(Collidable col) {
        BoundingBox box = col.getBounds();

        return (box.x + box.width >= x
                && box.y + box.height >= y
                && box.z + box.depth >= z
                && box.x <= x + width
                && box.y <= y + height
                && box.z <= z + depth);
    }

    private boolean intersectsSphereSphere(Collidable col) {
        BoundingBox box = col.getBounds();

        double radius = Math.max(width, Math.max(height, depth)) / 2.0;
        double otherRad = Math.max(box.width, Math.max(box.height, box.depth)) / 2.0;

        return false;
    }

    private boolean intersectsSphereAABox(BoundingBox sphere, BoundingBox box) {
        return false;
    }

    public CollisionType getType() {
        return type;
    }

    public void setType(CollisionType type) {
        this.type = type;
    }

    public boolean intersects(Collidable col) {
        boolean result = false;

        // No collider types setted
        if (type == null || col.getType() == null) {
            return false;
        }
        switch (type.getVal() + col.getType().getVal()) {

            // Sphere-Sphere
            case 6:
                result = intersectsSphereSphere(col);
                break;

            // Sphere - AABox
            case 8:
                if (type == CollisionType.AABox2D) {
                    result = intersectsSphereAABox(col.getBounds(), this);
                } else {
                    result = intersectsSphereAABox(this, col.getBounds());
                }
                break;

            // AABox - AABox
            case 10:
                result = intersectsAABoxAABox(col);
                break;
        }
        return result;
    }

}
