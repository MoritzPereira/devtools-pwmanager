package de.hhn.it.devtools.apis.paintservice;

/**
 * this is a helper class for Shapedescriptor to save the colors as RGB triplets
 */
public class ColorTriplet {

    protected double red;
    protected double green;
    protected double blue;


    public ColorTriplet() {}

    public void setRed(double red) {
        this.red = red;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

}
