package de.hhn.it.devtools.apis.paint;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation.
 */

public class PaintUsageDemo {

    public static void main(String[] args) throws IllegalParameterException {


        PaintService paintService = null;


        //example coordinates of mouse point
        int mouseExampleCoordinateX1 = 1;
        int mouseExampleCoordinateY1 = 1;
        int mouseExampleCoordinateX2 = 2;
        int mouseExampleCoordinateY2 = 2;
        int mouseExampleCoordinateX3 = 3;
        int mouseExampleCoordinateY3 = 3;


        Color exampleColor = new Color(33, 33, 53,255);




        Board page1 = new Board(500, 500);
        page1.setBoardId(0);

        //default board color
        page1.setBoardColor(255, 255, 255);


        //user start new drawing action and select scribble as shape type
        ShapeDescriptor shape0 = new ShapeDescriptor(mouseExampleCoordinateX1, mouseExampleCoordinateY1, 5, exampleColor, false, false);
        shape0.setShapeId(0);





        //user starts draw by pressing mouse
        boolean mousePressed = true;


        //user keeps mouse pressed
        while (mousePressed) {

            //simulates move of the mouse
          shape0.addPoint(mouseExampleCoordinateX2, mouseExampleCoordinateY2);


        }



        //user releases mouse
        mousePressed = false;


        //defines the end of a shape/drawing action
        shape0.setEndPoint(mouseExampleCoordinateX3, mouseExampleCoordinateY3);

        //program add shape to board
        paintService.addShape(shape0, 0);



        //New Action

        //user want to change color of earlier drawn shape
        Color newColor = new Color(45,26,95,255);

        for (int i = 0; i<paintService.containsPoint(mouseExampleCoordinateX2, mouseExampleCoordinateY2, 0).size(); i++) {

        paintService.changeColor(paintService.containsPoint(mouseExampleCoordinateX2, mouseExampleCoordinateY2, 0).get(i),
                0,newColor );

        }


        //New Action


        //user do clear action
        paintService.clear(0, paintService.getShapes(0));







        //new Action



        //user wants to draw straight horizontal help lines
        ShapeDescriptor shape1 = new ShapeDescriptor(mouseExampleCoordinateX1, mouseExampleCoordinateY1, 5, exampleColor, true , false);
        shape1.setShapeId(1);




        //user starts draw by pressing mouse
        //boolean mousePressed = true;
        shape1.setStartPoint(mouseExampleCoordinateX1, mouseExampleCoordinateY1);





        //user releases mouse
       // mousePressed = false;
        //defines the end of a shape/drawing action
        shape0.setEndPoint(mouseExampleCoordinateX3, mouseExampleCoordinateY3);



        //program draws lines between each start/End-point
        paintService.addShape(shape1, 0);






    }
}