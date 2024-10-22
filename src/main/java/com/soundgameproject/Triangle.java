// Triangle.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Triangle - sets up triangular particle and movement


package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

// Triangle class extends Shape to represent triangular particles
public class Triangle extends Shape {

    public int color; // Color of the triangle

    // Constructor for Triangle class
    public Triangle(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) {
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
        this.color = main.color(0); // Set initial color to black
    }

    // Draw method to render the triangle
    @Override
    public void draw() {
        // Draw the triangle
        drawShape(); // Calls a method to handle any pre-draw setup (if applicable)
        main.pushMatrix(); // Save the current transformation matrix
        main.translate(x, y); // Move to the triangle's position
        main.triangle(-size / 2, size / 2, size / 2, size / 2, 0, -size / 2); // Draw triangle centered at (x, y)
        main.popMatrix(); // Restore the previous transformation matrix
    }

    // Move method to update the position of the triangle
    @Override
    public void move() {
        x += xVel + 2; // Update x position based on velocity (with additional movement)
        y += yVel + 2; // Update y position based on velocity (with additional movement)
    }

    // Check if the triangle is clicked based on mouse position
    @Override
    public boolean isClicked(float mx, float my) {
        // Check for click within the bounding box of the triangle
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; 
    }

    // Update method to handle movement and collision detection
    @Override
    public void update(ArrayList<Shape> shapes) {
        move(); // Update position of the triangle
        checkBoundary(); // Check if the triangle is within boundaries
        
        // Check for collisions with other shapes
        for (Shape other : shapes) {
            if (other != this && checkCollision(other)) {
                handleCollision(other); // Handle collision with another Triangle
            }
        }
    }

    // Mouse click event to change color and size of the triangle
    public void mouseClicked() {
        color = main.color(0, 255, 0); // Change color to green on click
        size += 5; // Increase size of the triangle by 5 units
    }

    // Method to set the fill color for the triangle based on selection state
    protected void drawShape() {
        if (isSelected) {
            main.fill(0, 255, 0); // Green if selected
        } else {
            main.fill(0); // Black if not selected
        }
    }
}