package org.example;

public class Wall {
    private double height;
    private double width;
    private double obstacleArea;

    // Constructor to initialise the Wall with height and width
    public Wall(double height, double width) {
        this.height = height;
        this.width = width;
        this.obstacleArea = 0;
    }

    // Method to add an obstacle to the wall
    public void addObstacle(double height, double width) {
        this.obstacleArea += height * width;
    }

    // Method to calculate the net area of the wall (excluding obstacles)
    public double getNetArea() {
        return (height * width) - obstacleArea;
    }

    // Method to calculate the total area of the wall
    public double getArea() {
        return height * width;
    }
}

