// TriangleState.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Manages the state for triangle shapes in the Particle Engine game.              Handles shape initialization, drawing, and input events.


package com.soundgameproject;

import java.util.ArrayList;

public class TriangleState extends GameState {
    // Constructor to initialize the TriangleState
    public TriangleState(Main main) {
        super(main); // Call the parent constructor
        shapes = new ArrayList<>(); // Initialize the list of shapes
        getBathTubImage(); // Load bathtub image (currently not implemented)
        initializeShapes(); // Initialize triangle shapes
    }

    // Method to create and initialize triangle shapes
    public void initializeShapes() {
        for (int i = 0; i < 10; i++) {
            // Add new Triangle objects with random positions and velocities
            shapes.add(new Triangle(main.random(main.width), main.random(main.height), 60, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    // Placeholder method for bathtub image loading
    private void getBathTubImage() {
        // Implementation for loading the bathtub image should be added here
    }

    @Override
    public void draw() {
        //main.background(255); // Clear the background to white
        main.draw();
        updateAndDrawShapes(); // Update and draw all shapes in the list
        displayScore(); // Display the current score
    }

    @Override
    public void update() {
        // Update logic for the TriangleState (currently empty)
    }

    @Override
    public void handleInput() {
        // Input handling logic for the TriangleState (currently empty)
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