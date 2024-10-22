// TitleState.java
// Anika Krieger
// Sep 30
// Particle Engine III
// Description: Class representing the title screen state of the game. Displays the game title and instructions to start playing.

package com.soundgameproject;

import processing.core.PApplet;

public class TitleState extends GameState {

    // Constructor to initialize the TitleState
    public TitleState(Main main) {
        super(main); // Call the superclass constructor
    }

    // Draw method to render the title screen
    @Override
    public void draw() {
        //main.background(255); // Clear the background to white
        drawBucket(); // Get the bathtub image (if needed)

        // Set up the title text properties
        main.textSize(48); // Set text size for the title
        main.fill(0); // Set text color to black
        main.textAlign(PApplet.CENTER); // Center align the text
        
        // Display the game title and instructions
        main.text("Particle Engine Game", main.width / 2, main.height / 2 - 50); // Title
        main.text("Press 'P' to Play with Circles", main.width / 2, main.height / 2 + 20); // Instructions
    }

    // Update method, currently does nothing for the title screen
    @Override
    public void update() {
        // No updates needed on the title screen
    }

    // Method to handle key presses, currently empty
    public void keyPressed() {
        // Handle key presses if necessary (e.g., to start the game)
    }

    // Handle keyboard input for the title screen
    @Override
    public void handleInput() {
        // Implement input handling logic if necessary
        // Example: press 'P' to transition to the game state
    }

    // Mouse press event handling, currently does nothing
    @Override
    public void mousePressed(int mouseX, int mouseY) {
        // No mouse interaction needed on the title screen
    }

    // Mouse drag event handling, currently does nothing
    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        // No dragging interaction needed on the title screen
    }

    // Mouse release event handling, currently does nothing
    @Override
    public void mouseReleased() {
        // No mouse interaction needed on the title screen
    }
}