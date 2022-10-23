package de.hhn.it.devtools.apis.paintservice;

/**
 * Used to expand the description properties of round shapes f.ex circles, ellipses or possible later planned curved lines.
 */
public class CircleDescriptor extends ShapeDescriptor {

    private double radiusX;
    private double radiusY;


    /**
     * constructor stating start point of the shape
     * @param startX x coordinates of the start point
     * @param startY y coordinates of the start point
     */
    public CircleDescriptor(double startX, double startY) {
        super(startX, startY);
    }


    /**
     * @param radiusX radius in x direction
     */
    public void setRadiusX(double radiusX){
        this.radiusX = radiusX;
    }

    /**
     * @param radiusY radius in y direction
     */
    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    /**
     * @return radius in x direction
     */
    public double getRadiusX() {
        return radiusX;
    }


    /**
     * @return radius in y direction
     */
    public double getRadiusY() {
        return radiusY;
    }

}
