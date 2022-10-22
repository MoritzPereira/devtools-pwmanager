package de.hhn.it.devtools.apis.paintservice;

/**
 * Used to expand the description properties of round shapes.
 */
public interface CircleService extends ShapeService {


    /**
     * sets the X and Y-coordinates of the center point
     * @param centerX X-coordinate of the point
     * @param centerY Y-coordinate of the point
     * @throws IllegalArgumentException if the coordinates are < 0
     */
    void setCenterPoint(double centerX, double centerY) throws IllegalArgumentException;


    /**
     * sets the radius of the circle by calculating it from the planned "endX (from motherclass)and "centerX" data fields
     * @throws ArithmeticException if a wrong mathematical or arithmetic operation appears
     */
    void setRadius();



}
