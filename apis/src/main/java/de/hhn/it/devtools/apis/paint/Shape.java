package de.hhn.it.devtools.apis.paint;


/**
 * Shape stating all drawable shapes
 */
public enum Shape {

    /**
     * normal pencil
     */
    SCRIBBLE,

    /**
     * straight line between two point
     */
    LINE,

    /**
     * rectangle with different height and width
     */
    RECTANGLE,

    /**
     * has two different radii
     */
    ELLIPSE,

    /**
     * normal circle with one radius
     */
    CIRCLE,

    /**
     * each direction change represents a new start point for a line
     * the bunch of lines are connected and build one polyline
     */
    POLYLINE,

    /**
     * connection between different point. First and last point result to an additional line which closes the polyline
     * and build a polygon
     */
    POLYGON,


    /**
     * rectangle with same height and width
     */
    SQUARE
}