// GameOverState.java
// Anika Krieger
// Oct 27
// Particle Engine 4
// Description: Manages the game-over state, displaying the final score and allowing the player to restart the game.

package com.soundgameproject;

import processing.core.PApplet;

public class GameOverState extends GameState {

    // Constructor to initialize the GameOverState with a reference to the main class
    public GameOverState(Main main) {
        super(main);
    }

    @Override
    public void draw() {
        //main.background(255); // Clear the screen with a white background
        main.textSize(40); // Set the font size for the game-over text
        main.fill(0); // Set the text color to black
        main.textAlign(PApplet.CENTER); // Center-align the text horizontally
        
        // Display the game-over message and the final score in the center of the screen
        main.text("Game Over!", main.width / 2, main.height / 2 - 20);
        main.text("Final Score: " + score, main.width / 2, main.height / 2 + 20);
        main.text("Press 'R' to Restart", main.width / 2, main.height / 2 + 60);
    }

    @Override
    public void update() {
        // No updates are necessary in the game-over state as the game has ended
    }

    @Override
    public void handleInput() {
        // This method can be used to handle input (e.g., restart game) if needed
    }

    // Method to handle key press events, specifically for restarting the game
    public void keyPressed() {
        // Check if the 'R' key or 'r' key is pressed to restart the game
        if (main.key == 'R' || main.key == 'r') {
            main.currentState = main.titleState; // Return to the title screen or reset the game state
            resetGame(); //self explanatory
        }
    }
}
