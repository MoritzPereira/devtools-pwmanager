package de.hhn.it.devtools.apis.paint;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.util.ArrayList;
import java.util.LinkedList;


public interface PaintService {


    /**
     * Returns a list of registered shapes (undo list) of given board
     * @param boardId defines the board of where the shapes are should be returned
     * @return list of registered shapes
     * @throws IllegalParameterException if board id is invalid or a null reference
     */
    LinkedList<ShapeDescriptor> getShapes(int boardId) throws IllegalParameterException;


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
     * adds the shape (could be drawing action or erase action as well) with all his coordinates to the given board
     *
     * @param shape descriptor of the new shape
     * @param boardId defines the board where the shape should be added
     * @throws IllegalParameterException if x, y or boardId values are invalid/does not exist or shape/mode are a null
     *                                   reference
     */
    void addShape(ShapeDescriptor shape, int boardId)
            throws IllegalParameterException;


    /**
     * control if shape points contains mouse point
     *
     * @param boardId defines the board where the points should be checked
     * @param xCoordinate of the mouse
     * @param yCoordinate of the mouse
     * @return all shapes that have identical points with the mouse coordinates
     * @throws IllegalParameterException if boardId, x or y coordinates of point are invalid or does not exist
     *
     */
    ArrayList<ShapeDescriptor> containsPoint(int xCoordinate, int yCoordinate, int boardId) throws IllegalParameterException;


    /**
     * Deletes one whole drawing action of the given board
     *
     * @param shape which should be deleted
     * @param boardId defines the board of the shape
     * @throws IllegalParameterException if the id of the shape or board does not exist or is invalid
     */
    void deleteShape(ShapeDescriptor shape, int boardId) throws IllegalParameterException;

    /**
     * delete all shapes from the board and push all of them to undo stack
     * @param boardId defines the board that should be cleared
     * @throws IllegalParameterException if y or y coordinates are invalid or null references
     */
    void clear(int boardId, LinkedList<ShapeDescriptor> shapeDescriptorList) throws IllegalParameterException;




    /**
     * Changes the color of an earlier drawing action on a given board
     *
     * @param shape which color should be changed
     * @param boardId defines the board where the shape is
     * @param red proportion of RGB
     * @param green proportion of RGB
     * @param blue proportion of RGB
     * @param transparency defines the transparency of the given shapedescriptor object
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     * @throws IndexOutOfBoundsException if the transparency value is less than 0 or greater than 255
     */
    void changeColor(ShapeDescriptor shape, int boardId, int red, int green, int blue, int transparency)
            throws IllegalParameterException, IndexOutOfBoundsException;


    /**
     * Changes the size of an earlier drawing action on a given board
     *
     * @param shape which size should be changed
     * @param boardId defines the board where the shape is
     * @param size new size of the shape
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     */
    void changeSize(ShapeDescriptor shape, int boardId, double size) throws IllegalParameterException;


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
