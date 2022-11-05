package de.hhn.it.devtools.apis.paint;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.util.ArrayList;
import java.util.Stack;


public interface PaintService {


    /**
     * Returns a stack of registered shapes (undo stack) of given board
     * @param boardId defines the board of where the shapes are should be returned
     * @return stack of registered shapes
     * @throws IllegalParameterException if board id is invalid or a null reference
     */
    ArrayList<ShapeDescriptor> getShapes(int boardId) throws IllegalParameterException;


    /**
     * Returns shape with the given id of given board
     *
     * @param shapeId id of the shape
     * @param boardId id of the board
     * @return descriptor of the shape
     * @throws IllegalParameterException if the boardId or shapeId does not exist or is invalid
     */
    ShapeDescriptor getShape(int shapeId, int boardId) throws IllegalParameterException;


    /**
     * Adds a listener to get updates on the shape and action type for next action
     *
     * @param shapeId id of the shape
     * @param listener object implementing the listener interface
     * @param boardId id of the board
     * @throws IllegalParameterException if either the shapeId or boardId does not exist
     *                                   or the listener is a null reference.
     */
    void addCallback(int shapeId, ActionListener listener, int boardId) throws IllegalParameterException;


    /**
     * Removes a listener.
     *
     * @param id id of the shape
     * @param listener listener to be removed
     * @param boardId id of the board
     * @throws IllegalParameterException if either the shapeId or boardId does not exist
     *                                   or the listener is a null reference.
     */
    void removeCallback(int id, ActionListener listener, int boardId) throws IllegalParameterException;


    /**
     * calculate the draw or erase points on the board and adds the shape/action with all his coordinates to the given board
     *
     * @param shape descriptor of the new shape
     * @param x x coordinates of the mouse
     * @param y y coordinates of the mouse
     * @param action defining the drawing mode
     * @param boardId defines the board where the shape should be added
     * @throws IllegalParameterException if x, y or boardId values are invalid/does not exist or shape/mode are a null
     *                                   reference
     */
    void calculateDraw(ShapeDescriptor shape, double x, double y, Action action, int boardId)
            throws IllegalParameterException;


    /**
     * control if shape points contains mouse point
     *
     * @param x coordinate of the mouse
     * @param y coordinate of the mouse
     * @param boardId defines the board where the points should be checked
     * @return shape if mouse point identical with one of the points of any shape
     * @throws IllegalParameterException if boardId, x or y coordinates invalid or does not exist
     *
     */
    ShapeDescriptor containsPoint(double x, double y, int boardId) throws IllegalParameterException;


    /**
     * Deletes one whole drawing action of the given board
     *
     * @param shape which should be deleted
     * @param action define the deleting mode
     * @param boardId defines the board of the shape
     * @throws IllegalParameterException if the id of the shape or board does not exist or is invalid
     */
    void deleteShape(ShapeDescriptor shape, Action action, int boardId) throws IllegalParameterException;

    /**
     * delete all shapes from the board and push all of them to undo stack
     * @param boardId defines the board that should be cleared
     * @throws IllegalParameterException if y or y coordinates are invalid or null references
     */
    void clear(int boardId, ArrayList<ShapeDescriptor> shapeDescriptorStack) throws IllegalParameterException;




    /**
     * Changes the color of an earlier drawing action on a given board
     *
     * @param shape which color should be changed
     * @param action define the deleting mode
     * @param boardId defines the board where the shape is
     * @param red proportion of RGB
     * @param green proportion of RGB
     * @param blue proportion of RGB
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     */
    void changeColor(ShapeDescriptor shape, Action action, int boardId, double red, double green, double blue)
            throws IllegalParameterException;


    /**
     * Changes the size of an earlier drawing action on a given board
     *
     * @param shape which size should be changed
     * @param action define the deleting mode
     * @param boardId defines the board where the shape is
     * @param size new size of the shape
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     */
    void changeSize(ShapeDescriptor shape, Action action, int boardId, double size) throws IllegalParameterException;


    /**
     * Undo last drawing action
     *
     * @param boardId defines the board where undo action should be done
     * @throws ArrayIndexOutOfBoundsException if  undo History  is empty
     * @throws IllegalParameterException if boardIs is invalid or does not exist
     */
    void undo(int boardId) throws ArrayIndexOutOfBoundsException, IllegalParameterException;


    /**
     * redo the last undo
     *
     * @param boardId defines the board where undo action should be done
     *
     * @throws ArrayIndexOutOfBoundsException if the redo History is empty
     * @throws IllegalArgumentException if boardId is invalid or does not exist
     */
    void redo(int boardId) throws ArrayIndexOutOfBoundsException, IllegalParameterException;


}
