// File/Class title: Main
// Anika Krieger
// Oct 27
// Particle Engine 4
// Game instructions and description: 
	// First game state: title page. To move from the title page to the game, you click 'p' to play. 
	// Second game state: circle state! this is the actual game. there is a timer on the right and score count on the left. the game works as follows: the goal is to click (and therefore remove) as many circles as possible before your time is up. 
	// Extra little feature: to exit the game and move to the third game state (the game over state/page), you can either click 'e' to escape or just wait until the timer ends and you run out of playing time. both of these trigger the game over sound
	// Third game state: game over. you can click 'r' to restart, which takes you back to the title screen/first game state
// Sound implementation:
	// 1. Sound for each circle colliding with the borders of the screen.
	// 2. Sound for each circle collding with another circle.
	// 3. Sound for when you go from gamestate 1 to gamestate 2 (triggered on keyPressed of 'p')
	// 4. Sound for when you click/remove a circle
	// 5. Sound for when you enter the gameover gamestate, achieved through either keyPressed of 'e' or through timer running out and automatic transfer to gameover page.
	// 6. cute little sound when you first run the program and enter the title page!
// Important tip for testing:
	// it can be a little hard to hear the mouseClicked sound because of the two collission sounds that are also happening in circlestate, so I would recommend playing over and over because when there are fewer circles on the screen, there is less collission, so the mouseClicked sound trigger is easier to recognize.
	// it can also be a little bit hard to hear the keyPressed sound that comes from starting the game by pressing 'p' - same advice applies. just when there's a lot of collission happening, it sounds "messier" to me


package com.soundgameproject;

//importing the JMusic stuff
//import jm.music.data.*;
//import jm.util.*;

//import FileSystem for cross-platform file referencing
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

//Processing
import processing.core.*;

//make sure this class name matches your file name, if not fix.
public class Main extends PApplet {

    GameState currentState; // Reference to the current game state
    TitleState titleState; // State for the title screen
    CircleState circleState; // State for the circle round
    GameOverState gameover; // State for the game-over screen
    
	public int timer = 10; //duration of game timer
	public int circleCount; //count of circles in game
	public int score = 0; //player's score
	public int startTime; //timestamp to mark when timer begins
	boolean timerStarted = false; //indication if timer has started
    
	//make cross-platform
    static FileSystem sys = FileSystems.getDefault();

    //the getSeperator() creates the appropriate back or forward slash based on the OS in which it is running -- OS X & Windows use same code :) 
    static String filePath = "mid" + sys.getSeparator(); // path to the midi file -- you can change this to your file location/name

	// array of midi file names
	String[] midiFiles = {"circleclicked-piano", "startgame-piano", "gameover-piano", "circlescollide-piano", "bordercollide-piano"};
	
	// instance to manage midi melodies
	MelodyManager melodyManager = new MelodyManager();


	public static void main(String[] args) {
        PApplet.main("com.soundgameproject.Main");    
    }

    public void settings() {
        size(800, 600); // Set up the canvas size for the application
		
		// adds midi files to manager:
		for (int i = 0; i < midiFiles.length; i++) 
		{
			melodyManager.addMidiFiles(filePath + midiFiles[i] + ".mid");
		}
	}

	//initialize game states
	public void setup() {
        titleState = new TitleState(this);
        circleState = new CircleState(this);
        gameover = new GameOverState(this);
        currentState = titleState; // Set the initial state to the title screen
	}

	// setup midi files
	public void setupMidiFiles() {
		for(int i=0; i<midiFiles.length; i++)
		{
			melodyManager.addMidiFiles(filePath + midiFiles[i] + ".mid"); // add files to manager
		}
	}

    //draw method to render game
    public void draw() { 
        background(255); // Clear the background to white
        currentState.draw(); // Call the draw method of the current game state
		melodyManager.playMelodies(); //play melodies for current game state

        // Check for end game condition
        if (currentState instanceof CircleState) {
            checkEndCondition();
        }
    }

    //start the melody at the beginning again when a key is pressed
    public void keyPressed() {

        // Handle key presses to switch between game states
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to the circle round state
			timerStarted = false; // reset timer
			melodyManager.start(1); // play midi file

        } else if (key == 'R' || key == 'r') {
            currentState = titleState; // Switch back to the title screen state
			GameState.score = 0; // reset score to 0
        }

		// if currently in circlestate, switch to gameover if e is pressed
		if (currentState instanceof CircleState && (key == 'E' || key == 'e')) {
			currentState = new GameOverState(this);
			melodyManager.start(2); //play midi file too
		}

    }

	//method to check if game is over/should end
    private void checkEndCondition() {
        // Check if the current state is circlestate and if it has no remaining shapes
        if (currentState instanceof CircleState) {
				if (!timerStarted) 
				{
					startTime = millis(); //record start time
					timerStarted = true; //set timer as started
				}
			int currentTime = millis(); //get current time

			//if timer exceeds allotted time or if there are no more circles left
            if (currentTime - startTime > 10000 || currentState.shapes.isEmpty()) { 
				int finalScore = GameState.score; // Access the total score
                System.out.println("Final Score: " + finalScore); //print final score
				currentState = new GameOverState(this); //transfer states
				melodyManager.start(2); //play state transfer midi file
			}
        }
    }

    public void mousePressed() {
        // Delegate mouse pressed event to the current state
        currentState.mousePressed(mouseX, mouseY);
    }

	public MelodyManager getMelodyManager()	{
		//getter method for melody manager
		//return melodymanager instance
		return melodyManager;
	}
}
