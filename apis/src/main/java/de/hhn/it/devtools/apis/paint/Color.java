package de.hhn.it.devtools.apis.paint;

/**
 * this is a helper class for ShapeDescriptor to save the colors as RGB triplets
 */
public class Color {

    private int red;
    private int green;
    private int blue;

    private int transparency;


    public Color(int red, int green, int blue, int transparency) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.transparency = transparency;
    }


    public void setTransparency(int transparency){
        this.transparency = transparency;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

}
