// CircleState.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Manages the state for circle shapes in the Particle Engine game.              Handles shape initialization, drawing, and input events.


package com.soundgameproject;

import java.util.ArrayList;
import java.util.Iterator;

public class CircleState extends GameState {
    
    // Constructor to initialize the CircleState
    public CircleState(Main main) {
        super(main); // Call the parent constructor
        shapes = new ArrayList<>(); // Initialize the list of shapes
        initializeShapes(); // Create initial circle shapes
    }

    // Method to create and initialize circle shapes
    public void initializeShapes() {
        for (int i = 0; i < 10; i++) {
            // Add new Circle objects with random positions and velocities
            shapes.add(new Circle(main.random(main.width), main.random(main.height), 80, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    @Override
    public void draw() {
        //main.draw(); // Draw the bathtub image (method should be implemented in Main)
        updateAndDrawShapes(); // Update and draw all shapes in the list
        displayScore(); // Display the current score

        displayTimer();

    }

    @Override
    public void update() {
        // Update logic for the CircleState (currently empty)
    }

    @Override
    public void handleInput() {
        // Input handling logic for the CircleState (currently empty)
    }

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        super.mousePressed(mouseX, mouseY); // Call parent class mousePressed implementation
        Iterator<Shape> iterator = shapes.iterator();
        while (iterator.hasNext()) 
        {
            Shape shape = iterator.next();
            if (shape instanceof Circle && shape.isClicked(mouseY, mouseY))
            {
                iterator.remove();
            }
        }
    }
    
}
