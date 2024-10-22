// Square.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Square - sets up square particle and movement

package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

// Square class extends Shape to represent square particles
public class Square extends Shape {

    public int color; // Color of the square

    // Constructor for Square class
    public Square(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) {
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
        this.color = main.color(0); // Set initial color to black
    }

    // Draw method to render the square
    public void draw() {
        drawShape(); // Calls a method to handle any pre-draw setup (if applicable)
        main.rectMode(PApplet.CENTER); // Set rectangle mode to center
        main.rect(x, y, size, size); // Draw square centered at (x, y)
    }

    // Move method to update the position of the square
    @Override
    public void move() {
        x += xVel + 6; // Update x position based on velocity (with additional movement)
        y += yVel + 6; // Update y position based on velocity (with additional movement)
    }

    // Check if the square is clicked based on mouse position
    @Override
    public boolean isClicked(float mx, float my) {
        // Check for click within the bounding box of the square
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; 
    }

    // Update method to handle movement and collision detection
    @Override
    public void update(ArrayList<Shape> shapes) {
        move(); // Update position of the square
        checkBoundary(); // Check if the square is within boundaries
        
        // Check for collisions with other shapes
        for (Shape other : shapes) {
            if (other != this && checkCollision(other)) {
                handleCollision(other); // Handle collision with another square
            }
        }
    }

    // Mouse click event to select the square and increase its size
    public void mouseClicked() {
        select(); // Mark the square as selected
        // color = main.color(0, 255, 0); // Uncomment to change color on click
        size += 5; // Increase size of the square by 5 units
    }

    // Method to set the fill color for the square based on selection state
    protected void drawShape() {
        if (isSelected) {
            main.fill(0, 255, 0); // Green if selected
        } else {
            main.fill(0); // Black if not selected
        }
    }
}