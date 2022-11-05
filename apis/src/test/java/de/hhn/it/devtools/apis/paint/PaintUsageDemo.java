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
        int mouseExampleCoordinateX = 1;
        int mouseExampleCoordinateY = 1;


        Board page1 = new Board(500, 500);
        page1.setBoardId(0);

        //default board color
        page1.setBoardColor(255, 255, 255);


        //user start new drawing action and select scribble as shape type
        ShapeDescriptor shape0 = new ShapeDescriptor(mouseExampleCoordinateX, mouseExampleCoordinateY);
        shape0.setShapeId(0);
        shape0.setStraightMode(false);


        //example color, should later be the last selected color
        shape0.setShapeColor(33, 33, 53);


        //example thickness, should later be the last selected one
        shape0.setLineThickness(5.3);



        //user starts draw by pressing mouse
        boolean mousePressed = true;


        //user keeps mouse pressed
        while (mousePressed) {

            //simulates move of the mouse
            mouseExampleCoordinateX++;
            mouseExampleCoordinateY++;

        }


        //user releases mouse and defines the end of a shape/drawing action
        mousePressed = false;

        shape0.setEndPoint(mouseExampleCoordinateX, mouseExampleCoordinateY);

        //program draws lines between each added point during mouse press
        paintService.calculateDraw(shape0, mouseExampleCoordinateX, mouseExampleCoordinateY, Action.DRAW, 0);



        //New Action

        //user want to change color of earlier drawn shape
        paintService.changeColor(paintService.containsPoint(mouseExampleCoordinateX, mouseExampleCoordinateY, 0),
                Action.COLOR, 0, 45,26,95);



        //New Action


        //user do clear action
        paintService.clear(0, paintService.getShapes(0));







        //new Action



        //user wants to draw straight horizontal help lines
        ShapeDescriptor shape1 = new ShapeDescriptor(mouseExampleCoordinateX, mouseExampleCoordinateY);
        shape1.setShapeId(1);
        shape1.setStraightMode(true);


        //example color, should later be the last selected color
        shape0.setShapeColor(33, 33, 53);


        //example thickness, should later be the last selected one
        shape0.setLineThickness(5.3);



        //user starts draw by pressing mouse
        //boolean mousePressed = true;
        shape1.setStartPoint(mouseExampleCoordinateX, mouseExampleCoordinateY);





        //user releases mouse
       // mousePressed = false;
        //defines the end of a shape/drawing action
        shape0.setEndPoint(mouseExampleCoordinateX, mouseExampleCoordinateY);



        //program draws lines between each start/End-point
        paintService.calculateDraw(shape1, mouseExampleCoordinateX, mouseExampleCoordinateY, Action.DRAW, 0);






    }
}