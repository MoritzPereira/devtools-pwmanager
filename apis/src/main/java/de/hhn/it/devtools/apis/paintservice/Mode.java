package de.hhn.it.devtools.apis.paintservice;


/**
 * Enum stating all drawable shapes and erasablee modes
 */
public enum Mode {

    /**
     * Eraser
     */
    ERASE,

    /**
     * mode to delete whole drawing actions
     */
    DELETE,

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
     * has two radii
     */
    ELLIPSE,

    /**
     * normal circle
     */
    CIRCLE,

    /**
     * connection between different point
     */
    POLYLINE,

    /**
     * connection between different point. First and last point result to an additional line which closes the Polygon
     */
    POLYGON, SQUARE
}