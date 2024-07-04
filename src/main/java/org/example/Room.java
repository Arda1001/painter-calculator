package org.example;

public class Room {
    private Wall[] walls;
    private double paintCoverage;
    private int numberOfCoats;

    // Constructor to initialise the Room with the number of walls, paint coverage, and number of coats
    public Room(int numberOfWalls, double paintCoverage, int numberOfCoats) {
        this.walls = new Wall[numberOfWalls];
        this.paintCoverage = paintCoverage;
        this.numberOfCoats = numberOfCoats;
    }

    // Method to add a wall to the room
    public void addWall(int index, Wall wall) {
        walls[index] = wall;
    }

    // Method to calculate the total area of all walls in the room
    public double getTotalArea() {
        double totalArea = 0;
        for (Wall wall : walls) {
            if (wall != null) {
                totalArea += wall.getNetArea();
            }
        }
        return totalArea * numberOfCoats;
    }

    // Method to calculate the amount of paint needed
    public double getPaintNeeded() {
        return getTotalArea() / paintCoverage;
    }
}

