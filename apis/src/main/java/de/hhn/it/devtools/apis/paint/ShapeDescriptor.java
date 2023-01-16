package de.hhn.it.devtools.apis.paint;


import java.util.ArrayList;



/**
 * Represent and describes all shapes that can be used to draw on a board
 */
public class ShapeDescriptor {

    private int shapeId;
    private double thickness;
    private Point startPoint;
    private Point endPoint;
    private ArrayList<Point> points = new ArrayList<>();
    private Boolean straightMode;
    private Boolean eraseMode;
    private Color shapeColor;




    public ShapeDescriptor(int startX, int startY, double thickness, Color color, Boolean straightMode, Boolean eraseMode,int shapeId){
        shapeColor = color;
        setStartPoint(startX, startY);
        this.thickness = thickness;
        this.straightMode = straightMode;
        this.eraseMode = eraseMode;
        this.shapeId=shapeId;
    }


    /**
     * defines the width of the lines of a shape object
     *
     * @param thickness width of the lines
     * @throws IllegalArgumentException if the given thickness is not positive
     */
    public void setLineThickness(double thickness) {
        this.thickness = thickness;
    }


    /**
     * sets the coordinates for the start point of a drawing action
     *
     * @param startX X-coordinate of the point
     * @param startY Y-coordinate of the point
     * @throws IllegalArgumentException for invalid coordinates f.ex negative ones
     */
    public void setStartPoint(int startX, int startY) {
        startPoint = new Point(startX, startY);
        points.add(startPoint);
    }

    /**
     * defines the endpoint of each shape
     *
     * @param endX X-coordinate of the point
     * @param endY Y-coordinate of the point
     * @throws IllegalArgumentException for invalid coordinates f.ex negative ones
     */
    public void setEndPoint(int endX, int endY) {
        endPoint = new Point(endX, endY);
        points.add(endPoint);
    }


    /**
     *
     * add point with x and y coordinates to hashmap
     *
     * later used for adding continually the coordinates of all mouse points during mouse is pressed for a drawing actions to hashmap
     * can be used later in components to calculate actual point for all kind of rectangle or circle shapes
     * for eraser and scribble actions the points can be used instantly
     * @param x X-coordinate of each point
     * @param y Y-coordinate of each point
     * @throws IllegalArgumentException if one coordinate is invalid
     */
    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }


    /**
     * @return start point of each drawing action
     */
    public Point getStartPoint() {
        return startPoint;
    }


    /**
     * @return End point of each drawing action
     */
    public Point getEndPoint() {
        return endPoint;
    }


    /**
     * @return all points of the shape
     */
    public ArrayList<Point> getPoints() {
        return points;
    }


    /**
     * @param straightMode defines if the draw action is free hand or on straight lines
     */
    public void setStraightMode(Boolean straightMode) {
        this.straightMode = straightMode;
    }


    /**
     * @return style of drawing action (straight or free hand)
     */
    public Boolean getStraightMode() {
        return straightMode;
    }




    /**
     * returns the id of the shape.
     *
     * @return unique id of the shape
     */
    public int getShapeId() {
        return shapeId;
    }




    /**
     * sets the color of a shape
     *
     * @param color of the shape
     */
    public void setShapeColor(Color color) {
        shapeColor = color;
    }

    /**
     * @return color of the shape
     */
    public Color getShapeColor() {
        return shapeColor;
    }


    /**
     * @param eraseMode defines if shape should draw or erase
     */
    public void setEraseMode(Boolean eraseMode) {
        this.eraseMode = eraseMode;
    }


    /**
     * @return boolean expression if shapee draw or erase
     */
    public Boolean getEraseMode() {
        return eraseMode;
    }


    public double getThickness() {
        return thickness;
    }
}






















