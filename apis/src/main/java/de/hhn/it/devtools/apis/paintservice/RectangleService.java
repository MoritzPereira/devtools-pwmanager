package de.hhn.it.devtools.apis.paintservice;

public interface  RectangleService extends ShapeService {

    /**
     * @param width of the shape
     */
    void setWidth(double width);


    /**
     *
     * @param height of the shape
     */
    void setHeight(double height);


    /**
     *
     * @return height of the shape
     */
    double getHeight();


    /**
     *
     * @return width of the shape
     */
    double getWidth();
}