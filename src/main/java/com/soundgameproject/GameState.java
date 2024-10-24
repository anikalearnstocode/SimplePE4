// GameState.java
// com.particleengineREAL2
// Anika Krieger
// Particle Engine 3
// Description: Abstract class representing the game state. Manages shape initialization, score handling, input handling, and state transitions for different shapes.


package com.soundgameproject;

import java.util.ArrayList;
import processing.core.PApplet;

public abstract class GameState {
    protected Main main; // Reference to the main application
    protected static int score; // 
    protected ArrayList<Shape> shapes; // List to store shapes in the current state
    protected Shape selectedShape; // Currently selected shape
    protected boolean isEndState = false; // Flag to indicate if the game has ended
    protected int startTime;
    
    // Constructor to link back to the Main application
    public GameState(Main main) {
        this.main = main; // Initialize main reference
        this.shapes = new ArrayList<>(); // Initialize the shapes list
    }

    // Abstract method for initializing shapes, to be implemented by subclasses
    public void initializeShapes() {}

    // Increment the score by one
    public static void incrementScore() {
        score++;
    }

    // Display the current score on the screen
    public void displayScore() {
        main.textSize(30); // Set text size
        main.fill(0); // Set text color to black
        main.textAlign(PApplet.LEFT, PApplet.TOP); // Align text to top-right corner
        main.text("Score: " + score, 20, 20); // Display score at specified position
        
        main.textAlign(PApplet.CENTER, PApplet.BOTTOM); // Align text to the bottom center
        main.text("Press E or e to quit", main.width / 2, main.height - 20); // Adjust position for bottom center
    
    }

    public void resetGame()
    {
        score = 0;
        main.startTime = main.millis();

    }

    public void displayTimer() {
        int timeLeft = main.timer - (main.millis() - main.startTime) / 1000; // Calculate time left
        if (timeLeft < 0) timeLeft = 0; // Ensure the time left doesn't go negative

        main.textSize(24); // Set text size
        main.fill(0); // Set text color to black
        main.textAlign(PApplet.RIGHT, PApplet.TOP); // Align text to top-right corner
        main.text("Time Left: " + timeLeft, main.width - 20, 20); // Display time
    }

    // Main draw method to handle the rendering logic
    public void draw() {

        if (!isEndState) {
            updateAndDrawShapes(); // Update and draw shapes if the game is not in end state
        } else {
            handleEndState(); // Handle the end state of the game
            PApplet.println("called");
        }

        
    }
    
    // Abstract method for updating game logic, to be implemented by subclasses
    public abstract void update();

    // Abstract method for handling input, to be implemented by subclasses
    public abstract void handleInput(); 

    // Update shapes and check if they are in the bathtub
    public void updateAndDrawShapes() {
        // Iterate backwards through the shapes list for safe removal
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i); // Get the current shape
            shape.update(shapes); // Call the shape's update method
            shape.draw();   // Call the shape's draw method
            
        }
        // Check if there are no shapes left
        if (shapes.isEmpty()) {
            handleEndState(); // Handle the end of the game
        }
    }

    // Handle the end state of the game
    protected void handleEndState() {
        String endMessage = ""; // Message to display at the end
        String nextStateMessage = ""; // Message for the next state

        // Set end state flag if not already set
        if (!isEndState) {
            isEndState = true; // Update flag
            finalScoreCount(); // Final score counting logic
        }

        // Set messages based on the current state
        if (this instanceof CircleState) {
         {
            endMessage = "Your time ran out!";
            nextStateMessage = "Game over!";
            finalScoreCount(); // Count final score in square state
        } 

        // Display end state messages
        main.textSize(40); // Set larger text size for end state
        main.fill(0); // Set text color to black
        main.textAlign(PApplet.CENTER); // Center align text
        main.text(endMessage, main.width / 2, main.height / 2); // Display end message
        main.text(nextStateMessage, main.width / 3, main.height / 3); // Display next state message
        
    }
}

    // Mouse press event handling
    public void mousePressed(int mouseX, int mouseY) {
        // Check if any shape is clicked and select it
        for (int i = shapes.size() - 1; i >= 0; i--)
        {
            Shape shape = shapes.get(i);
            if (shape.isMouseOver(mouseX, mouseY))
            {
                shapes.remove(i);
                incrementScore();
                System.out.println("Shape clicked and removed. Current Score: " + score);
                break;
            }
        }
    }


    // Display the final score count at game over
    public void finalScoreCount() {
        if (isEndState) {
            main.text("Game Over!", main.width / 2, main.height / 2 + 20); // Display game over message
            //main.text("Total Score: " + totalScore, main.width / 2, main.height / 2 - 50); // Total score (commented out)
        }
    }
}
