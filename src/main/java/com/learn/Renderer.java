package com.learn;

public class Renderer {
    private final Palette palette;

    public Renderer(Palette palette) {
        this.palette = palette;
    }

    public void drawLine() {
    }

    public Colour getForegroundColour() {
        return palette.getPrimaryColour();
    }

    public void method1(){
        System.out.println("New line is added to add branch code updated in 786");
    }
}
