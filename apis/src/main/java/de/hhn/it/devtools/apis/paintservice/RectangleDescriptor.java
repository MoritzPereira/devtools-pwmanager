package de.hhn.it.devtools.apis.paintservice;

/**
 * Used to expand the description properties of angular shapes.
 */
public class RectangleDescriptor extends ShapeDescriptor {

    private double height;
    private double width;

    /**
     * constructor stating start point of the shape
     * @param startX x coordinate
     * @param startY y coordinate
     */
    public RectangleDescriptor(double startX, double startY) {
        super(startX, startY);
    }

    /**
     * @param width of the shape
     */
    public void setWidth(double width) {
        this.width = width;
    }


    /**
     * @param height of the shape
     */
    public void setHeight(double height) {
        this.height = height;
    }


    /**
     * @return height of the shape
     */
    public double getHeight() {
        return height;
    }


    /**
     * @return width of the shape
     */
    public double getWidth() {
        return width;
    }




}