// SquareState.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Manages the state for square shapes in the Particle Engine game.              Handles shape initialization, drawing, and input events.



package com.soundgameproject;

import java.util.ArrayList;

public class SquareState extends GameState {
    // Constructor to initialize the SquareState
    public SquareState(Main main) {
        super(main); // Call the parent constructor
        shapes = new ArrayList<>(); // Initialize the list of shapes
        initializeShapes(); // Initialize square shapes
        getBathTubImage(); // Load bathtub image (currently not implemented)
    }

    // Method to create and initialize square shapes
    public void initializeShapes() {
        for (int i = 0; i < 10; i++) {
            // Add new Square objects with random positions and velocities
            shapes.add(new Square(main.random(main.width), main.random(main.height), 40, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    // Placeholder method for bathtub image loading
    private void getBathTubImage() {
        // Implementation for loading the bathtub image should be added here
    }

    @Override
    public void draw() {
       // main.background(255); // Clear the background to white
        main.draw();
       updateAndDrawShapes(); // Update and draw all shapes in the list
        displayScore(); // Display the current score
    }

    @Override
    public void update() {
        // Update logic for the SquareState (currently empty)
    }

    @Override
    public void handleInput() {
        // Input handling logic for the SquareState (currently empty)
    }

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        super.mousePressed(mouseX, mouseY); // Call parent class mousePressed implementation
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        super.mouseDragged(mouseX, mouseY); // Call parent class mouseDragged implementation
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased(); // Call parent class mouseReleased implementation
    }
}