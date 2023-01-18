package de.hhn.it.devtools.apis.paint;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * represents und describes a board on which different drawing actions can be done
 */
public class Board {

  private int boardId;
  private double height;
  private double width;
  private Color color;
  private LinkedList<ShapeDescriptor> undoHistory;
  private LinkedList<ShapeDescriptor> redoHistory;
  private int idCounter;
  boolean flag;

  /**
   * constructor of board
   * sets height and length and default color white
   * @param height of the board
   * @param width of the board
   */
  public Board(double height, double width) {
    this.height = height;
    this.width = width;
    Color color = new Color(255, 255,255,255);
    setBoardColor(color);
    undoHistory = new LinkedList<>();
    redoHistory = new LinkedList<>();
    boardId = 0;
    idCounter=0;



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

  public void setBoardColor(Color color) {
    this.color = color;
  }

  public Color getBoardColor() {
    return color;
  }


  /**
   * add shape to the board
   * @param shapeDescriptor the shape to be added
   */
  public void addShape(ShapeDescriptor shapeDescriptor){
    undoHistory.add(shapeDescriptor);
    idCounter++;




  }

  public int getIdCounter() {
    return idCounter;
  }

  public void setIdCounter(int idCounter) {
    this.idCounter = idCounter;
  }

  /**
   * push to undo
   * @param shapeDescriptor shape to be deleted
   */
  public void push(ShapeDescriptor shapeDescriptor) {
    undoHistory.remove(shapeDescriptor);
    redoHistory.add(shapeDescriptor);
  }


  /**
   * pull from redo, push to undo
   * @param shapeDescriptor shape to be restored
   */
  public void pull(ShapeDescriptor shapeDescriptor) {
    redoHistory.remove(shapeDescriptor);
    undoHistory.add(shapeDescriptor);

  }



  /**
   * @return a bunch of shapes that earlier be undoed
   */
  public LinkedList<ShapeDescriptor> getRedoHistory() {
    return redoHistory;
  }


  /**
   * @return all shapes and drawing actions of the board
   */
  public LinkedList<ShapeDescriptor> getUndoHistory() {
    return undoHistory;
  }


  public void setFlag(boolean flag){
    this.flag = flag;
  }

  public boolean getFlag() {
    return flag;
  }

  public int getShapeIdCounter() {
    return idCounter;
  }
}

