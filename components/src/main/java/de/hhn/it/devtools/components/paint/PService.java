package de.hhn.it.devtools.components.paint;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.paint.*;
import java.util.*;


public class PService implements PaintService {
    private final HashMap<Integer,Board> boards;
    private int backForwardCounter;
    int redoCounter;
    Color lastColor;
    boolean undoFlag;
    boolean backFlag;
    boolean forwardFlag;
    boolean first;
    boolean last;
    boolean move;
    boolean deleteFlag;
    boolean lastLast;
    boolean clearFlag;
    boolean changeColorFlag;

    public PService() {
        boards = new HashMap<>();
        Board defaultBoard = new Board(1500,1500);
        defaultBoard.setFlag(true);
        defaultBoard.setBoardId(0);
        boards.put(0,defaultBoard);
        backForwardCounter = 0;
        redoCounter = 0;
        undoFlag = false;
        backFlag = false;
        lastColor = null;
        first = true;
        last = false;
        forwardFlag = false;
        move = false;
        deleteFlag=false;
        lastLast = false;
        clearFlag = false;
        changeColorFlag = false;
    }

    /**
     * Returns a list of registered shapes (undo list) of given board
     *
     * @param boardId defines the board of where the shapes are should be returned
     * @return list of registered shapes
     * @throws IllegalParameterException if board id is invalid or a null reference
     */
    @Override
    public LinkedList<ShapeDescriptor> getShapes(int boardId) throws IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        return boards.get(boardId).getUndoHistory();
    }

    /**
     * Returns shape with the given id of given board
     * @param shapeId id of the shape
     * @param boardId id of the board
     * @return descriptor of the shape
     * @throws IllegalParameterException if the boardId or shapeId does not exist or is invalid
     */
    @Override
    public ShapeDescriptor getShape(int shapeId, int boardId) throws IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        for (ShapeDescriptor shape : getShapes(boardId)) {
            if (shape.getShapeId() == shapeId) {
                return shape;
            }
        }
        throw new IllegalParameterException("Shape with id " + shapeId + " not found on board with id " + boardId);
    }

    /**
     * adds the shape (could be drawing action or erase action as well) with all his coordinates to the given board
     *
     * @param shape   descriptor of the new shape
     * @param boardId defines the board where the shape should be added
     * @throws IllegalParameterException if x, y or boardId values are invalid/does not exist or shape/mode are a null
     *                                   reference
     */
    @Override
    public void addShape(ShapeDescriptor shape, int boardId) throws IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (shape == null) {
            throw new IllegalParameterException("Invalid shape: ");
        }
        if(move) {
            getShapes(boardId).get(backForwardCounter).setShapeColor(lastColor);
        }
        first=true;
        boards.get(boardId).addShape(shape);
        backForwardCounter = boards.get(boardId).getUndoHistory().size();
        move = false;
        deleteFlag=false;
        last=false;
    }

    @Override
    public ShapeDescriptor back(int boardId) throws IndexOutOfBoundsException,
            NoSuchElementException,IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (boards.get(boardId).getUndoHistory().isEmpty()) {
            throw new NoSuchElementException("There is no shape anymore");
        }
        if (backForwardCounter <=0) {
            throw new IndexOutOfBoundsException("There is no Shape anymore");
        }
        backForwardCounter = backForwardCounter -1;
        if (backForwardCounter==boards.get(boardId).getUndoHistory().size()-1) {
            first=true;
        }
        if ((!backFlag || backForwardCounter==0) &&!first ){
            lastColor = new Color(getShapes(boardId).get(backForwardCounter).getShapeColor().getRed(),
                    getShapes(boardId).get(backForwardCounter).getShapeColor().getGreen(),
                    getShapes(boardId).get(backForwardCounter).getShapeColor().getBlue(),
                    getShapes(boardId).get(backForwardCounter).getShapeColor().getTransparency());
            getShapes(boardId).get(backForwardCounter).setShapeColor(new Color(1,0,0,1));
            backFlag =true;
        }
        if (first) {
            getShapes(boardId).get(backForwardCounter).setShapeColor(new Color(1,0,0,1));
            backFlag = false;
            first = false;
        }
        if (backFlag) {
            getShapes(boardId).get(backForwardCounter+1).setShapeColor(lastColor);
            getShapes(boardId).get(backForwardCounter).setShapeColor(new Color(1,0,0,1));
            backFlag = false;
        }
        last = false;
        move = true;
        changeColorFlag = false;
        redoCounter = getActualBoard().getUndoHistory().size();
        return boards.get(boardId).getUndoHistory().get(backForwardCounter);
    }

    @Override
    public ShapeDescriptor forward(int boardId) throws IndexOutOfBoundsException,
            NoSuchElementException, IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (boards.get(boardId).getUndoHistory().isEmpty()) {
            throw new NoSuchElementException("There is no shape anymore");
        }
        if (backForwardCounter >= boards.get(boardId).getUndoHistory().size()-1) {
            throw new IllegalParameterException("There is no Shape anymore");
        }
        backForwardCounter = backForwardCounter +1;
        if ((!forwardFlag && !last)){
            lastColor = new Color(getShapes(boardId).get(backForwardCounter).getShapeColor().getRed(),
                    getShapes(boardId).get(backForwardCounter).getShapeColor().getGreen(),
                    getShapes(boardId).get(backForwardCounter).getShapeColor().getBlue(),
                    getShapes(boardId).get(backForwardCounter).getShapeColor().getTransparency());
            getShapes(boardId).get(backForwardCounter).setShapeColor(new Color(1,0,0,1));
            forwardFlag =true;
        }
        if (last) {
            if (backForwardCounter>0){
                backForwardCounter--;
                getShapes(boardId).get(backForwardCounter).setShapeColor(new Color(1,0,0,1));
            }
            last = false;
            forwardFlag=false;
        }
        if (forwardFlag) {
            getShapes(boardId).get(backForwardCounter-1).setShapeColor(lastColor);
            getShapes(boardId).get(backForwardCounter).setShapeColor(new Color(1,0,0,1));
            forwardFlag = false;
        }
        move = true;
        deleteFlag=false;
        first=false;
        redoCounter = getActualBoard().getUndoHistory().size();
        return boards.get(boardId).getUndoHistory().get(backForwardCounter);
    }


    /**
     * Deletes one whole drawing action of the given board
     *
     * @param shapeId represent the shape which should be deleted
     * @throws IllegalParameterException if the id of the shape or board does not exist or is invalid
     */
    @Override
    public void deleteShape(int shapeId, int boardId) throws IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (shapeId < 0) {
            throw new IllegalParameterException("Invalid shapeId: " + shapeId);
        }
        if (backForwardCounter==boards.get(boardId).getUndoHistory().size()-1) {
            first=true;
        }
        ShapeDescriptor deleteShape = getShape(shapeId, boardId);
        boards.get(boardId).push(deleteShape);
        deleteFlag=true;
        last = true;
        clearFlag=false;
        move=false;
    }


    public Color getLastColor() {
        return lastColor;
    }


    /**
     * Changes the color of an earlier drawing action on a given board
     *
     * @param shapeId   which color should be changed
     * @param boardId defines the board where the shape is
     * @param color   that the shape should get
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     * @throws IndexOutOfBoundsException if the one color value is less than 0 or greater than 255
     */
    @Override
    public void changeColor(int shapeId, int boardId, Color color) throws IllegalParameterException, IndexOutOfBoundsException {
        if (color.getBlue() < 0 || color.getBlue() > 1 ||
                color.getRed() < 0 || color.getRed() > 1||
                color.getGreen() < 0 || color.getGreen() > 1||
                color.getTransparency() < 0 || color.getTransparency() > 1) {
            throw new IndexOutOfBoundsException("invalid color value");
        }
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (shapeId < 0 ) {
            throw new IllegalParameterException("Invalid shapeId: " + shapeId);
        }
        getShape(shapeId, boardId).setShapeColor(color);
        move=false;
        changeColorFlag = true;
        first=true;
    }

    /**
     * Changes the size of an earlier drawing action on a given board by deleting the shape
     * and drawing it in the new size
     * the new size should getted via a slider that represent the pencil thickness
     * @param shapeId   which size should be changed
     * @param boardId defines the board where the shape is
     * @param size    new size of the shape
     * @throws IllegalParameterException if shape is a null reference or boardId is invalid or does not exist
     */
    @Override
    public void changeSize(int shapeId, int boardId, double size) throws IllegalParameterException {
        if (shapeId < 0 ) {
            throw new IllegalParameterException("Invalid shapeId: " + shapeId);
        }
        if (size < 0) {
            throw new IllegalParameterException("invalid size value");
        }
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        ShapeDescriptor shapeDescriptor = getShape(shapeId, boardId);
        shapeDescriptor.setLineThickness(size);
        move = false;
    }


    /**
     * Undo last drawing action
     * @param boardId defines the board where undo action should be done
     * @throws IllegalArgumentException  if  undo History  is empty
     * @throws IllegalParameterException if boardIs is invalid or does not exist
     */
    @Override
    public void undo(int boardId) throws NoSuchElementException, IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (boards.get(boardId).getUndoHistory().isEmpty()) {
            throw new NoSuchElementException("Undo-History is empty");
        }
        if(move && !deleteFlag) {
            getShapes(boardId).get(backForwardCounter).setShapeColor(lastColor);
        }
        boards.get(boardId).getRedoHistory().add(boards.get(boardId).getUndoHistory().pollLast());
        backForwardCounter = boards.get(boardId).getUndoHistory().size();
        undoFlag = true;
        first=true;
        move = false;
        deleteFlag=false;
    }

    /**
     * delete all shapes from the board and push all of them to undo stack
     *
     * @param boardId defines the board that should be cleared
     * @throws IllegalParameterException if y or y coordinates are invalid or null references
     */
    @Override
    public void clear(int boardId) throws IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
       if (!getActualBoard().getUndoHistory().isEmpty()) {
            redoCounter = getActualBoard().getUndoHistory().size();
       }
        for (int i =  boards.get(boardId).getUndoHistory().size() - 1; i >= 0; i--) {
            if (i!= backForwardCounter) {
                deleteShape(boards.get(boardId).getUndoHistory().get(i).getShapeId(), boardId);
            }
        }
        if (!boards.get(boardId).getUndoHistory().isEmpty()) {
            deleteShape(boards.get(boardId).getUndoHistory().get(0).getShapeId(), boardId);
        }
        undoFlag = false;
        first=true;
        clearFlag=true;
        move=false;
        deleteFlag=false;
    }


    /**
     * redo the last undo
     *
     * @param boardId defines the board where undo action should be done
     * @throws IllegalArgumentException  if the redo History is empty
     * @throws IllegalParameterException if boardId is invalid or does not exist
     */
    @Override
    public void redo(int boardId) throws NoSuchElementException, IllegalParameterException {
        if (!boards.containsKey(boardId)) {
            throw new IllegalParameterException("Invalid boardId: " + boardId);
        }
        if (boards.get(boardId).getRedoHistory().isEmpty()) {
            throw new NoSuchElementException("Redo-History is empty");
        }
        if (redoCounter>0 && !undoFlag)  {
            redoCounter--;
        }
        if (undoFlag || (deleteFlag && !clearFlag)  ) {
            redoCounter=0;
        }
        if ((deleteFlag && !move && !getActualBoard().getUndoHistory().isEmpty() && !clearFlag) ||
                (clearFlag && lastColor!=null)) {
            getActualBoard().getRedoHistory().getLast().setShapeColor(lastColor);
        }
        boards.get(boardId).getUndoHistory().add(boards.get(boardId).getRedoHistory().pollLast());
        backForwardCounter = boards.get(boardId).getUndoHistory().size();
        undoFlag = false;
        first=true;
        move=false;
        deleteFlag=false;
        clearFlag=false;
    }

    public int getRedoCounter(){
        return redoCounter;
    }

    public Board getActualBoard() {
        if (boards.isEmpty()) {
            throw new IllegalStateException("No boards available");
        }
        for (int i = 0; i < boards.size(); i++) {
            if (boards.get(i).getFlag()) {
                return boards.get(i);

            }
        }
        throw new IllegalStateException("No board with flag set to true");
    }
}
