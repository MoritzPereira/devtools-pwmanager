package de.hhn.it.devtools.apis.paint;

/**
 * this is a helper class for ShapeDescriptor to save the colors as RGB triplets
 */
public class Color {

    private double red;
    private double green;
    private double blue;
    private double transparency;


    public Color(double red, double green, double blue, double transparency) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.transparency = transparency;
    }


    public void setTransparency(double transparency){
        this.transparency = transparency;
    }

    public double getTransparency() {
        return transparency;
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
