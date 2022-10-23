package de.hhn.it.devtools.apis.paint;


import java.util.HashMap;


     /**
     * Represent and describes all shapes that can be used to draw on a board
     */
    public class ShapeDescriptor {

         protected int shapeId;
         protected double thickness;
         protected double startX;
         protected double startY;
         protected double endX;
         protected double endY;
         protected HashMap<Double, Double> coordinates = new HashMap<>();
         protected Shape shape;
         protected Boolean isFilled;
         protected ColorTriplet shapeColor;



         public ShapeDescriptor(double startX, double startY){
             shapeColor = new ColorTriplet();
             setStartPoint(startX, startY);
             shapeId = 0;
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
         public void setStartPoint(double startX, double startY) {
             this.startX = startX;
             this.startY = startY;
             coordinates.put(startX, startY);
         }


         /**
          * defines the endpoint of each shape
          *
          * @param endX X-coordinate of the point
          * @param endY Y-coordinate of the point
          * @throws IllegalArgumentException for invalid coordinates f.ex negative ones
          */
         public void setEndPoint(double endX, double endY) {
             this.endX = endX;
             this.endY = endY;
             coordinates.put(endX,endY);
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
         public void addPoint(double x, double y) {
             coordinates.put(x,y);
         }


         /**
          * @return y-coordinate of each start point
          */
         public double getStartX() {
             return startX;
         }

         /**
          * @return y-coordinate of each start point
          */
         public double getStartY() {
             return startY;
         }


         /**
          * @return x-coordinate of each end point
          */
         public double getEndX() {
             return endX;
         }


         /**
          * @return y-coordinate of each end point
          */
         public double getEndY() {
             return endY;
         }


         /**
          * @param shape one of the shapes that are defined in the enum class
          */
         public void setShape(Shape shape) {
             this.shape = shape;
         }


         /**
          * @return the actual shape
          */
         public Shape getShape() {
             return shape;
         }


         /**
         * @param isFilled describes if shape is filled out or not
         */
         public void setFilled(Boolean isFilled) {
             this.isFilled = isFilled;
         }


         /**
          * @return boolean expression if shape is filled out or not
          */
         public Boolean getFilled() {
             return isFilled;
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
          * Sets the id of the shape.
          *
          * @param id id of the shape
          */
         public void setShapeId(final int id) {
             this.shapeId = id;
         }


         /**
          * sets the color of a shape
          *
          * @param red proportion of RGB
          * @param green proportion of RGB
          * @param blue proportion of RGB
          */
         public void setShapeColor(double red, double green, double blue) {
             shapeColor.setRed(red);
             shapeColor.setGreen(green);
             shapeColor.setBlue(blue);
         }

         /**
          * @return color of the shape
          */
         public ColorTriplet getShapeColor() {
             return shapeColor;
         }

    }






















