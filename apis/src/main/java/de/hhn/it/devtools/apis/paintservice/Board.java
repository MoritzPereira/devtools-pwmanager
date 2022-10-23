package de.hhn.it.devtools.apis.paintservice;


import java.util.Stack;

/**
 * represents und describes a board on which different drawing actions can be done
 */
public class Board {

  private int boardId;
  private double height;
  private double width;
  private ColorTriplet colorTriplet;
  private Stack<ShapeDescriptor> undoStack;
  private Stack<ShapeDescriptor> redoStack;


  /**
   * constructor of board
   * sets height and length and default color white
   * @param height of the board
   * @param width of the board
   */
  public Board(double height, double width) {
    this.height = height;
    this.width = width;
    setBoardColor(255,255,255);
    undoStack = new Stack<>();
    redoStack = new Stack<>();
    boardId = 0;
  }

  public void setBoardId(int boardId) {
    this.boardId = boardId;
  }

  public int getBoardId() {
    return boardId;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getHeight() {
    return height;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getWidth() {
    return width;
  }

  public void setBoardColor(double red, double green, double blue) {
    colorTriplet.setRed(red);
    colorTriplet.setGreen(green);
    colorTriplet.setBlue(blue);
  }

  public ColorTriplet getBoardColor() {
    return colorTriplet;
  }


  /**
   * add shape to the board
   * @param shapeDescriptor the shape to be added
   */
  public void addShape(ShapeDescriptor shapeDescriptor) {
    undoStack.push(shapeDescriptor);
  }

  /**
   * remove/delete shape from board
   * @param shapeDescriptor shape to be deleted
   */
  public void removeShape(ShapeDescriptor shapeDescriptor) {
    undoStack.remove(shapeDescriptor);
    redoStack.push(shapeDescriptor);
  }



  /**
   * @return a bunch of shapes that earlier be undoed
   */
  public Stack<ShapeDescriptor> getRedoStack() {
    return redoStack;
  }


  /**
   * @return all shapes and drawing actions of the board
   */
  public Stack<ShapeDescriptor> getUndoStack() {
    return undoStack;
  }


}
