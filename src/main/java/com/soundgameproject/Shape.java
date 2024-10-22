// Shape.java
// Anika Krieger
// Sep 30
//Description: Parent class for all shapes

package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

public abstract class Shape {
    // Common properties for all shapes
    float x, y, size; // Position and size of the shape
    float xVel; // Horizontal velocity of the shape
    float yVel; // Vertical velocity of the shape
    PApplet main; // Reference to the main PApplet instance
    boolean isSelected; // State to track if the shape is selected

    // Constructor to initialize common attributes
    Shape(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) {
        this.x = x_; // Set the x-coordinate
        this.y = y_; // Set the y-coordinate
        this.size = size_; // Set the size of the shape
        this.main = main_; // Reference to the PApplet instance
        this.xVel = xVel_; // Set X velocity
        this.yVel = yVel_; // Set Y velocity
        this.isSelected = false; // Initialize selection state to false
    }

    // Create an ArrayList<Shape> to store different shapes based on the provided counts
    public static ArrayList<Shape> createShapes(int circleCount, int squareCount, int triangleCount, PApplet main) {
        ArrayList<Shape> shapes = new ArrayList<>(); // Initialize the list of shapes

        int[] shapeCounts = {circleCount, squareCount, triangleCount}; // Array to hold shape counts
        int totalShapes = circleCount + squareCount + triangleCount; // Calculate total shapes

        // Generate random shapes based on the specified counts
        for (int i = 0; i < totalShapes; i++) {
            float size = main.random(50, 80);  // Generate random size for shapes
            float x = main.random(main.width); // Generate random x-coordinate
            float y = main.random(main.height); // Generate random y-coordinate
            float speedX = main.random(-2, 2); // Generate random X velocity
            float speedY = main.random(-2, 2); // Generate random Y velocity

            // Create specific shapes and add them to the ArrayList<Shape>
            if (i < shapeCounts[0]) {
                shapes.add(new Circle(x, y, size, main, speedX, speedY));
            } else if (i < shapeCounts[0] + shapeCounts[1]) {
                shapes.add(new Square(x, y, size, main, speedX, speedY));
            } else {
                shapes.add(new Triangle(x, y, size, main, speedX, speedY));
            }
        }

        return shapes; // Return the created list of shapes
    }

    protected void drawShape() {
        // Set color based on selection state
        if (isSelected) {
            main.fill(0, 255, 0); // Green if selected
        } else {
            main.fill(0); // Default color is black
        }
    }

    // Abstract methods to be implemented by subclasses
    public abstract void draw();  // Each shape will implement its own drawing logic
    public abstract void update(ArrayList<Shape> shapes); // Method for shape updates, including collision checks
    public abstract void mouseClicked(); // Method to handle mouse click events

    // Common move behavior for all shapes
    public void move() {
        x += xVel; // Update x-coordinate based on velocity
        y += yVel; // Update y-coordinate based on velocity
        checkBoundary(); // Check if shape hits the screen boundaries
    }

    // Common boundary check for all shapes
    public void checkBoundary() {
        // Check for collision with left or right boundaries
        if (x < size / 2 || x > main.width - size / 2) {
            xVel *= -1; // Reverse X velocity
            x = PApplet.constrain(x, size / 2, main.width - size / 2); // Constrain x within boundaries
        }

        // Check for collision with top or bottom boundaries
        if (y < size / 2 || y > main.height - size / 2) {
            yVel *= -1; // Reverse Y velocity
            y = PApplet.constrain(y, size / 2, main.height - size / 2); // Constrain y within boundaries
        }
    }

    // Getter for size
    public float getSize() {
        return size; // Return size of the shape
    }

    // Setter for size
    public void setSize(float newSize) {
        this.size = newSize; // Update size of the shape
    }

    // Check if the mouse is over the shape
    public boolean isMouseOver(float mx, float my) {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2;
    }

    // Set color based on selection state and call draw method
    public void shapeColor() {
        if (isSelected) {
            main.fill(0, 255, 0); // Set color to green if selected
        } else {
            main.fill(0); // Default color is black
        }
        draw(); // Call the draw method
    }

    // Select the shape
    public void select() {
        isSelected = true; // Set selection state to true
    }

    // Deselect the shape
    public void deselect() {
        isSelected = false; // Set selection state to false
    }

    // Check if the shape is clicked based on mouse position
    public boolean isClicked(float mx, float my) {
        return isMouseOver(mx, my); // Return result of mouse over check
    }

    // Check for collision with another shape
    public boolean checkCollision(Shape other) {
        float distance = PApplet.dist(this.x, this.y, other.x, other.y); // Calculate distance between shapes
        return distance < (this.size / 2 + other.size / 2); // Return true if shapes collide
    }

    // Handle collision response between this shape and another
    public void handleCollision(Shape other) {
        // Reverse the direction of both shapes
        this.xVel *= -1;
        this.yVel *= -1;
        other.xVel *= -1;
        other.yVel *= -1;

        // Calculate overlap and adjust positions to separate the shapes
        float overlap = (this.size / 2 + other.size / 2) - PApplet.dist(this.x, this.y, other.x, other.y);
        if (overlap > 0) {
            float pushX = (other.x - this.x) / PApplet.dist(this.x, this.y, other.x, other.y);
            float pushY = (other.y - this.y) / PApplet.dist(this.x, this.y, other.x, other.y);
            this.x -= pushX * overlap / 2; // Push this shape away from the other
            this.y -= pushY * overlap / 2; // Push this shape away from the other
            other.x += pushX * overlap / 2; // Push the other shape away from this shape
            other.y += pushY * overlap / 2; // Push the other shape away from this shape
        }
    }
}