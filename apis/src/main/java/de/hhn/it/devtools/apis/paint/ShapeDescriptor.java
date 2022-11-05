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
         private ColorTriplet shapeColor;



         public ShapeDescriptor(int startX, int startY){
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






















