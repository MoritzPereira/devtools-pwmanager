package de.hhn.it.devtools.apis.paint;



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
    undoHistory = new LinkedList<>();
    redoHistory = new LinkedList<>();
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

  public void setBoardColor(int red, int green, int blue) {
    color.setRed(red);
    color.setGreen(green);
    color.setBlue(blue);
  }

  public Color getBoardColor() {
    return color;
  }


  /**
   * add shape to the board
   * @param shapeDescriptor the shape to be added
   */
  public void addShape(ShapeDescriptor shapeDescriptor) {
    undoHistory.add(shapeDescriptor);
  }

  /**
   * remove/delete shape from board
   * @param shapeDescriptor shape to be deleted
   */
  public void removeShape(ShapeDescriptor shapeDescriptor) {
    undoHistory.remove(shapeDescriptor);
    redoHistory.add(shapeDescriptor);
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
  public LinkedList<ShapeDescriptor> getUndoStack() {
    return undoHistory;
  }


}
