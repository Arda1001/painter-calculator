package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PaintCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfWalls = 0;
        double coveragePerLitre = 0.0;
        int numberOfCoats = 0;

        // Gather information about the room with input validation
        while (true) {
            try {
                System.out.print("Enter the number of walls: ");
                numberOfWalls = scanner.nextInt();
                if (numberOfWalls <= 0) {
                    System.out.println("You must have at least one wall to paint.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        while (true) {
            try {
                System.out.print("Enter the coverage of the paint (in square metres per litre): ");
                coveragePerLitre = scanner.nextDouble();
                if (coveragePerLitre <= 0) {
                    System.out.println("Coverage must be positive.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive number.");
                scanner.next(); // Clear the invalid input
            }
        }

        while (true) {
            try {
                System.out.print("Enter the number of coats: ");
                numberOfCoats = scanner.nextInt();
                if (numberOfCoats <= 0) {
                    System.out.println("Must have at least one coat of paint.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        Room room = new Room(numberOfWalls, coveragePerLitre, numberOfCoats);

        for (int i = 0; i < numberOfWalls; i++) {
            double height = 0.0;
            double width = 0.0;

            while (true) {
                try {
                    System.out.println("Wall " + (i + 1) + ":");
                    System.out.print("Enter the height of the wall (in metres): ");
                    height = scanner.nextDouble();
                    if (height <= 0) {
                        System.out.println("Height must be positive.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a positive number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the width of the wall (in metres): ");
                    width = scanner.nextDouble();
                    if (width <= 0) {
                        System.out.println("Width must be positive.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a positive number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            Wall wall = new Wall(height, width);

            while (true) {
                try {
                    System.out.print("Enter the number of obstacles (e.g., windows, doors) on this wall: ");
                    int numberOfObstacles = scanner.nextInt();
                    if (numberOfObstacles < 0) {
                        System.out.println("Number of obstacles cannot be negative.");
                        continue;
                    }

                    double totalObstacleArea = 0.0;

                    for (int j = 0; j < numberOfObstacles; j++) {
                        double obstacleHeight = 0.0;
                        double obstacleWidth = 0.0;

                        while (true) {
                            try {
                                System.out.println("Obstacle " + (j + 1) + ":");
                                System.out.print("Enter the height of the obstacle (in metres): ");
                                obstacleHeight = scanner.nextDouble();
                                if (obstacleHeight <= 0) {
                                    System.out.println("Height must be positive.");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a positive number.");
                                scanner.next(); // Clear the invalid input
                            }
                        }

                        while (true) {
                            try {
                                System.out.print("Enter the width of the obstacle (in metres): ");
                                obstacleWidth = scanner.nextDouble();
                                if (obstacleWidth <= 0) {
                                    System.out.println("Width must be positive.");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a positive number.");
                                scanner.next(); // Clear the invalid input
                            }
                        }

                        double obstacleArea = obstacleHeight * obstacleWidth;
                        totalObstacleArea += obstacleArea;

                        if (totalObstacleArea > wall.getArea()) {
                            System.out.println("Total area of obstacles exceeds wall area. Please re-enter the dimensions.");
                            totalObstacleArea -= obstacleArea; // Remove the last added obstacle area
                            j--; // Decrement the obstacle counter to retry the input for the same obstacle
                            continue;
                        }

                        wall.addObstacle(obstacleHeight, obstacleWidth);

                        if (totalObstacleArea == wall.getArea()) {
                            System.out.println("Total obstacle area equals wall area. No more obstacles can be added.");
                            break;
                        }
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a non-negative integer.");
                    scanner.next(); // Clear the invalid input
                }
            }

            room.addWall(i, wall);  // Add the wall to the room
        }

        // Output the result
        System.out.println("Total area to paint (for " + numberOfCoats + " coats): " + room.getTotalArea() + " square metres");
        System.out.println("You will need approximately " + room.getPaintNeeded() + " litres of paint.");

        scanner.close();
    }
}


