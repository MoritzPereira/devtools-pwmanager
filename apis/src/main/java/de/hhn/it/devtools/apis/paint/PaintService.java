package de.hhn.it.devtools.apis.paint;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;


import java.util.List;
import java.util.NoSuchElementException;


public interface PaintService {


    /**
     * Returns a list of registered shapes (undo list) of given board
     * @param boardId defines the board of where the shapes are should be returned
     * @return list of registered shapes
     * @throws IllegalParameterException if board id is invalid or a null reference
     */
    List<ShapeDescriptor> getShapes(int boardId) throws IllegalParameterException;


    /**
     * Returns shape with the given id of given board
     *
     * @param shapeId id of the shape
     * @param boardId id of the board
     * @return descriptor of the shape
     * @throws IllegalParameterException if the boardId or shapeId does not exist or is invalid
     */
    ShapeDescriptor getShape(int shapeId, int boardId) throws IllegalParameterException;

    int getRedoCounter();

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
     * @return shape that was last drawn.
     */
    ShapeDescriptor back(int boardId) throws IndexOutOfBoundsException, NoSuchElementException,
            IllegalParameterException;


    /**
     * @return shape that was drawn after the last shape that was returned from the back method.
     */
    ShapeDescriptor forward(int boardId) throws IndexOutOfBoundsException, NoSuchElementException,
            IllegalParameterException;


    /**
     * Deletes one whole drawing action of the given board
     *
     * @param shapeId represent the shape which should be deleted
     * @throws IllegalParameterException if the id of the shape or board does not exist or is invalid
     */
    void deleteShape(int shapeId, int boardId) throws IllegalParameterException;

    /**
     * delete all shapes from the board and push all of them to undo stack
     * @param boardId defines the board that should be cleared
     * @throws IllegalParameterException if y or y coordinates are invalid or null references
     */
    void clear(int boardId) throws IllegalParameterException;




    /**
     * Changes the color of an earlier drawing action on a given board
     *
     * @param shapeId which color should be changed
     * @param boardId defines the board where the shape is
     * @param color that the shape should get
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     * @throws IndexOutOfBoundsException if the transparency value is less than 0 or greater than 255
     */
    void changeColor(int shapeId, int boardId, Color color)
            throws IllegalParameterException, IndexOutOfBoundsException;


    /**
     * Changes the size of an earlier drawing action on a given board by deleting the shape
     * and drawing it in the new size
     *
     * the new size should getted via a slider that represent the pencil thickness
     *
     * @param shapeId which size should be changed
     * @param boardId defines the board where the shape is
     * @param size new size of the shape
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     */
    void changeSize(int shapeId, int boardId, double size) throws IllegalParameterException;


    /**
     * Undo last drawing action
     *
     * @param boardId defines the board where undo action should be done
     * @throws IllegalArgumentException if  undo History  is empty
     * @throws IllegalParameterException if boardIs is invalid or does not exist
     */
    void undo(int boardId) throws IllegalArgumentException, IllegalParameterException;


    /**
     * redo the last undo
     *
     * @param boardId defines the board where undo action should be done
     *
     * @throws IllegalArgumentException if the redo History is empty
     * @throws IllegalParameterException if boardId is invalid or does not exist
     */
    void redo(int boardId) throws IllegalArgumentException, IllegalParameterException;


    Board getActualBoard();
    Color getLastColor();
}
