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
    
	public int timer = 15;
	public int circleCount;
	public int score = 0;
	public int startTime;
	boolean timerStarted = false;
    
	//make cross-platform
    static FileSystem sys = FileSystems.getDefault();

    //the getSeperator() creates the appropriate back or forward slash based on the OS in which it is running -- OS X & Windows use same code :) 
    static String filePath = "mid" + sys.getSeparator(); // path to the midi file -- you can change this to your file location/name

	String[] midiFiles = {"circleclicked-piano", "startgame-piano", "gameover-piano", "circlescollide-piano", "bordercollide-piano"};
	
	MelodyManager melodyManager = new MelodyManager();

    public static void main(String[] args) {
        PApplet.main("com.soundgameproject.Main");    
    }

    public void settings() {
        size(800, 600); // Set up the canvas size for the application
		for (int i = 0; i < midiFiles.length; i++) 
		{
			melodyManager.addMidiFiles(filePath + midiFiles[i] + ".mid");
		}
	}

    //doing all the setup stuff for the midi and also make the background black
    public void setup() {

        // Initialize the game states
        titleState = new TitleState(this);
        circleState = new CircleState(this);
        gameover = new GameOverState(this);
        currentState = titleState; // Set the initial state to the title screen
    }

	public void setupMidiFiles()
	{
		for(int i=0; i<midiFiles.length; i++)
		{
			melodyManager.addMidiFiles(filePath + midiFiles[i] + ".mid");
		}
	}

    //play the melody in real-time
    public void draw() {
        melodyManager.playMelodies(); 

        background(255); // Clear the background to white
        currentState.draw(); // Call the draw method of the current game state

        // Check for end game condition only when in SquareState
        if (currentState instanceof CircleState) {
            checkEndCondition();
        }
    }

    //start the melody at the beginning again when a key is pressed
    public void keyPressed() {
        //melodyManager.player.reset();

        // Handle key presses to switch between game states
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to the circle round state
			timerStarted = false;
			melodyManager.start(1);

        } else if (key == 'R' || key == 'r') {
            currentState = titleState; // Switch back to the title screen state
			GameState.score = 0;
        }

		if (currentState instanceof CircleState && (key == 'E' || key == 'e')) 
		{
			currentState = new GameOverState(this);
			melodyManager.start(2);
		}

    }

    private void checkEndCondition() {
        // Check if the current state is SquareState and if it has no remaining shapes
        if (currentState instanceof CircleState) {
				if (!timerStarted) 
				{
					startTime = millis();
					timerStarted = true;
				}
			int currentTime = millis();
            if (currentTime - startTime > 8000) 
			{ 
				int finalScore = GameState.score; // Access the total score
                System.out.println("Final Score: " + finalScore);
				currentState = new GameOverState(this);
				melodyManager.start(2);
			}
			
            if (currentState.shapes.isEmpty()) 
			{
                int finalScore = GameState.score; // Access the total score
                System.out.println("Final Score: " + finalScore); // Debugging output for the final score
                currentState = new GameOverState(this); // Switch to the game-over state
				melodyManager.start(2);
            }
        }
    }

    public void mousePressed() {
        // Delegate mouse pressed event to the current state
        currentState.mousePressed(mouseX, mouseY);
    }

	public MelodyManager getMelodyManager()
	{
		return melodyManager;
	}

}
