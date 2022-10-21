package de.hhn.it.devtools.apis.paintservice;

     /**
     * Represent and describes all shapes that can be used to draw
     */
    public interface ShapeService {


    /**
     * defines the width of the lines of a shape object
     * @param thickness width of the lines
     * @throws IllegalArgumentException if the given thickness is not positive
     */
    void setLineThickness(double thickness);



    /**
     * sets the coordinates for the start point of a drawing action
     * @param startX X-coordinate of the point
     * @param startY Y-coordinate of the point
     * @throws IllegalArgumentException for invalid coordinates f.ex negative ones
     */
    void setStartPoint(double startX, double startY);


    /**
     * defines the endpoint of each shape
     * @param endX X-coordinate of the point
     * @param endY Y-coordinate of the point
     * @throws IllegalArgumentException for invalid coordinates f.ex negative ones
     */
    void setEndPoint(double endX, double endY);


    /**
     * adds continually the coordinates of all detected points during a drawing actions
     * @param x X-coordinate of each point
     * @param y Y-coordinate of each point
     * @throws IllegalArgumentException if one coordinate is invalid
     */
    void addPoint(double x, double y);



    /**
     * @return y-coordinate of each start point
     */
    double getStartX();

    /**
     * @return y-coordinate of each start point
     */
    double getStartY();


    /**
     * @return x-coordinate of each end point
     */
    double getEndX();


    /**
     * @return y-coordinate of each end point
     */
    double getEndY();


    /**
     * is necessary for "undo" and "redo" of drawing actions
     * draws the removed shape again
     * @throws NullPointerException if one of the getter-methods which are used as parameters are null
     *
     */
    void draw();
}