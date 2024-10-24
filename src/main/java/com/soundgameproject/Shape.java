// Shape.java
// Anika Krieger
// Sep 30
//Description: Parent class for all shapes

package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

public abstract class Shape {
    float x, y, size; // Position and size
    float xVel; // Horizontal velocity
    float yVel; // Vertical velocity
    PApplet main; // Reference to PApplet instance
    int circleCount;
    int midiIndex;
    //boolean isSelected; // State to track if circle is selected

    MelodyManager melodies = null;


    // Constructor to initialize common attributes
    Shape(Main main_, float x_, float y_, float size_, float xVel_, float yVel_) {
        this.x = x_; // Set the x-coordinate
        this.y = y_; // Set the y-coordinate
        this.size = size_; // Set the size of the shape
        this.main = main_; // Reference to the PApplet instance
        this.xVel = xVel_; // Set X velocity
        this.yVel = yVel_; // Set Y velocity
        // circleCount = circleCount;
        //this.isSelected = false; // Initialize selection state to false
        melodies = main_.getMelodyManager();

    }

    // Create an ArrayList<Shape> to store different shapes based on the provided counts
    public static ArrayList<Circle> createShapes(int circleCount, Main main) {
        ArrayList<Circle> shapes = new ArrayList<>(); // Initialize the list of shapes

        int[] shapeCounts = {circleCount}; // Array to hold shape counts
        int totalShapes = circleCount; // Calculate total shapes

        // Generate random shapes based on the specified counts
        for (int i = 0; i < totalShapes; i++) {
            float size = main.random(4, 5);  // Generate random size for shapes
            float x = main.random(main.width); // Generate random x-coordinate
            float y = main.random(main.height); // Generate random y-coordinate
            float speedX = main.random(-2, 2); // Generate random X velocity
            float speedY = main.random(-2, 2); // Generate random Y velocity

            // Create specific shapes and add them to the ArrayList<Shape>
            if (i < shapeCounts[0]) {
                shapes.add(new Circle(x, y, size, main, speedX, speedY));
            } 
        }

        return shapes; // Return the created list of shapes
    }

    // protected void drawShape() {
    //     // Set color based on selection state
    //     if (isSelected) {
    //         main.fill(0, 255, 0); // Green if selected
    //     } else {
    //         main.fill(0); // Default color is black
    //     }
    // }

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
            melodies.start(4);

        }

        // Check for collision with top or bottom boundaries
        if (y < size / 2 || y > main.height - size / 2) {
            yVel *= -1; // Reverse Y velocity
            y = PApplet.constrain(y, size / 2, main.height - size / 2); // Constrain y within boundaries
            melodies.start(4);

        }

    }

    // Getter for size
    // public float getSize() {
    //     return size; // Return size of the shape
    // }

    // // Setter for size
    // public void setSize(float newSize) {
    //     this.size = newSize; // Update size of the shape
    // }

    // Check if the mouse is over the shape
    public boolean isMouseOver(float mx, float my) {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2;
    }

    // Check if the shape is clicked based on mouse position
    public boolean isClicked(float mx, float my) {
        return isMouseOver(mx, my); // Return result of mouse over check
    }

    // Check for collision with another shape
    public void collission(Shape other, int midiIndex) {
        float distance = PApplet.dist(this.x, this.y, other.x, other.y); // Calculate distance between shapes

        if (distance < (this.size / 2 + other.size / 2))
        {
            this.xVel *= -1;
            this.yVel *= -1;
            other.xVel *= -1;
            other.yVel *= -1;

            melodies.start(midiIndex);

        }
        // Return true if shapes collide
    }

    abstract void collission(Shape shape);

}