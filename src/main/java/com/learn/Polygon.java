package com.learn;

public class Polygon {
    private int numberOfSides;
    private Renderer renderer;

    Polygon(int numberOfSides) {
        if (numberOfSides <= 2) {
            throw new TooFewSidesException("The shape must have more than resolved sides", numberOfSides);
        }
        this.numberOfSides = numberOfSides;
    }

    public Polygon(int numberOfSides, Renderer renderer) {
        this.numberOfSides = numberOfSides;
        this.renderer = renderer;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void draw() {
        for (int i = 0; i < numberOfSides; i++) {
            renderer.drawLine();
        }
    }
}
