// Class title: Shape
// Anika Krieger
// Oct 27
// Particle Engine 4
// Description: Abstract class representing shape in the particle engine game. Provides properties like: position, size, velocity, and methods for movement, drawing, and collision detection.

package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

public abstract class Shape {
    float x, y, size; // Position and size
    float xVel; // Horizontal velocity
    float yVel; // Vertical velocity
    PApplet main; // Reference to PApplet instance
    int circleCount; // counter of circles
    int midiIndex; // index for MIDI sound effects

    MelodyManager melodies = null; //reference to MelodyManager for sound control

    // Constructor to initialize common attributes
    Shape(Main main_, float x_, float y_, float size_, float xVel_, float yVel_) {
        this.x = x_; // Set the x-coordinate
        this.y = y_; // Set the y-coordinate
        this.size = size_; // Set the size of the shape
        this.main = main_; // Reference to the PApplet instance
        this.xVel = xVel_; // Set X velocity
        this.yVel = yVel_; // Set Y velocity
        melodies = main_.getMelodyManager(); //initialize melody manager

    }

    // ArrayList<Shape> to store circles
    public static ArrayList<Circle> createShapes(int circleCount, Main main) {
        ArrayList<Circle> shapes = new ArrayList<>(); // Initialize the list of circles

        int[] shapeCounts = {circleCount}; // Array to hold shape counts
        int totalShapes = circleCount; // Calculate total shapes

        // Generate random shapes (in this case, circles) based on specified counts
        for (int i = 0; i < totalShapes; i++) {
            float x = main.random(main.width); // Generate random x-coordinate
            float y = main.random(main.height); // Generate random y-coordinate
            float speedX = main.random(-2, 2); // Generate random X velocity
            float speedY = main.random(-2, 2); // Generate random Y velocity
            float size = 40; // set default size to 40

            // Add circles to the ArrayList<Shape> if circlecount reaches 0
            if (i < shapeCounts[0]) {
                shapes.add(new Circle(x, y, size, main, speedX, speedY)); //add new circle to list
            } 
        }

        return shapes; // Return the created list of shapes
    }


    // Abstract methods to be implemented by subclasses
    public abstract void draw(); 
    public abstract void update(ArrayList<Shape> shapes); 
    public abstract void mouseClicked(); 

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
            melodies.start(4); // if boundary is hit, trigger midi file
        }

        // Check for collision with top or bottom boundaries
        if (y < size / 2 || y > main.height - size / 2) {
            yVel *= -1; // Reverse Y velocity
            y = PApplet.constrain(y, size / 2, main.height - size / 2); // Constrain y within boundaries
            melodies.start(4); // if boundary is hit, trigger midi file
        }

    }

    // Check if the mouse is over the shape
    public boolean isMouseOver(float mx, float my) {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // return true if mouse is over the shape
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
            //reverse both shapes' velocities
            this.xVel *= -1; 
            this.yVel *= -1;
            other.xVel *= -1;
            other.yVel *= -1;
            melodies.start(midiIndex); // play midi sound effect on collission - see subclass

        }
    }

    abstract void collission(Shape shape);

}