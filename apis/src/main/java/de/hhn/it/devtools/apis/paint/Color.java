package de.hhn.it.devtools.apis.paint;

/**
 * this is a helper class for ShapeDescriptor to save the colors as RGB triplets
 */
public class Color {

    private double red;
    private double green;
    private double blue;


    private double trancparency;


    public Color() {}


    public void setTrancparency(double trancparency){
        this.trancparency = trancparency;
    }

    public double getTrancparency() {
        return trancparency;
    }

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
