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
        main.background(255); // Clear the background to white

        // Set up the title text properties
        main.textSize(48); // Set text size for the title
        main.fill(0); // Set text color to black
        main.textAlign(PApplet.CENTER); // Center align the text
        
        // Display the game title and instructions
        main.text("Particle Engine Game", main.width / 2, main.height / 2 - 50); // Title
        main.text("Press 'P' to Play", main.width / 2, main.height / 2 + 20); // Instructions
    }


    @Override
    public void update() {}

    @Override
    public void handleInput() {}

}