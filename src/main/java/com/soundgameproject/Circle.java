// Circle.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Circle - sets up circular particle and movement

package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

// Circle class extends Shape to represent circular particles
public class Circle extends Shape {

    //float speedX;
    //float speedY;
    public int color; // Color of the circle

    // Constructor for Circle class
    public Circle(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) {
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
        this.color = main.color(0); // Set initial color to black
    }

    // Draw method to render the circle
    @Override
    public void draw() {
        //drawShape(); // Calls a method to handle any pre-draw setup (if applicable)
        // Set the fill color for the circle
        main.fill(color); // Fill the circle with its color
        main.ellipse(x, y, size, size); // Draw the circle at its position with the given size
    }
    
    // Move method to update the position of the circle
    @Override
    public void move() {
        x += xVel; // Update x position based on velocity
        y += yVel; // Update y position based on velocity
    }

    // Check if the circle is clicked based on mouse position
    @Override
    public boolean isClicked(float mx, float my) {
        return isMouseOver(mx, my); // Calls method to check if mouse is over the circle
    }

    // Update method to handle movement and collision detection
    @Override
    public void update(ArrayList<Shape> shapes) {
        move(); // Update position of the circle
        checkBoundary(); // Check if the circle is within boundaries
        
        // Check for collisions with other shapes
        for (Shape other : shapes) {
            if (other != this && checkCollision(other)) {
                handleCollision(other); // Handle collision with another Circle
            }
        }
    }

    // Mouse click event to change color and size of the circle
    @Override
    public void mouseClicked() {} 
}