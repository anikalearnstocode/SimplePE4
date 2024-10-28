// Class title: Circle
// Anika Krieger
// Oct 27
// Particle Engine 4
// Description: Represents circular particles in the game, extending the Shape class.

package com.soundgameproject;

import java.util.ArrayList;
//import processing.core.PApplet;

// Circle class extends Shape to represent circular particles
public class Circle extends Shape {

    public int color; // Color of the circle

    // Constructor for Circle class
    public Circle (float size_, float x_, float y_, Main main_, float xVel_, float yVel_) {
        super(main_, x_, y_, size_, xVel_, yVel_); 
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
            if (other != this)
            {
                collission(other, midiIndex); //handle collission
            }
        }
    }

    // Mouse click event to play sound effect
    @Override
    public void mouseClicked() {
        ((Main) main).getMelodyManager().start(0);
    }

    // handle collission (play sound effect)
    @Override
    void collission(Shape shape) {
       // ((Main) main).getMelodyManager().start(3);
    } 
}