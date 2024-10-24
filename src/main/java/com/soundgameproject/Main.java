package com.soundgameproject;

//importing the JMusic stuff
import jm.music.data.*;
import jm.util.*;

//import FileSystem for cross-platform file referencing
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

//Processing
import processing.core.*;

//make sure this class name matches your file name, if not fix.
public class Main extends PApplet {

    static MelodyPlayer player; //play a midi sequence
    static MidiFileToNotes midiNotes; //read a midi file
    static int noteCount = 0; //index into the array of notes to send to the MIDI file.

    GameState currentState; // Reference to the current game state
    TitleState titleState; // State for the title screen
    CircleState circleState; // State for the circle round
    GameOverState gameover; // State for the game-over screen
    
	public int timer = 10;
	public int circleCount;
	public int score = 0;
	public int startTime;
	boolean timerStarted = false;
    
	//make cross-platform
    static FileSystem sys = FileSystems.getDefault();

    //the getSeperator() creates the appropriate back or forward slash based on the OS in which it is running -- OS X & Windows use same code :) 
    static String filePath = "mid" + sys.getSeparator() + "MaryHadALittleLamb.mid"; // path to the midi file -- you can change this to your file location/name

    public static void main(String[] args) {
        PApplet.main("com.soundgameproject.Main");    
    }

    public void settings() {
        size(800, 600); // Set up the canvas size for the application
        midiSetup(filePath);
    }

    //doing all the setup stuff for the midi and also make the background black
    public void setup() {
        player.reset();

        // Initialize the game states
        titleState = new TitleState(this);
        circleState = new CircleState(this);
        gameover = new GameOverState(this);

        currentState = titleState; // Set the initial state to the title screen

        // Set the initial position for the rectangle (same as the bathtub image)
    
    }

    //play the melody in real-time
    public void draw() {
        playMelody(); 

        background(255); // Clear the background to white
        currentState.draw(); // Call the draw method of the current game state

        // Check for end game condition only when in SquareState
        if (currentState instanceof CircleState) {
            checkEndCondition();
        }
    }

    //play the midi file using the player -- so sends the midi to an external synth such as Kontakt or a DAW like Ableton or Logic
    static public void playMelody() {
        assert(player != null); //this will throw an error if player is null -- eg. if you haven't called setup() first
        player.play(); //play each note in the sequence -- the player will determine whether is time for a note onset
    }

    //opens the midi file, extracts a voice, then initializes a melody player with that midi voice (e.g. the melody)
    static void midiSetup(String filePath) {
        player = new MelodyPlayer(100, "Bus 1"); //sets up the player with your bus. 
        player.listDevices(); //prints available midi devices to the console -- find your device

        midiNotes = new MidiFileToNotes(filePath); // creates a new MidiFileToNotes

        midiNotes.setWhichLine(0); // this assumes the melody is midi channel 0
        noteCount = midiNotes.getPitchArray().size(); //get the number of notes in the midi file

        assert(noteCount > 0); // make sure it got some notes

        //sets the player to the melody to play the voice grabbed from the midi file above
        player.setMelody(midiNotes.getPitchArray());
        player.setRhythm(midiNotes.getRhythmArray());
        player.setStartTimes(midiNotes.getStartTimeArray());
    }

    //start the melody at the beginning again when a key is pressed
    public void keyPressed() {
        player.reset();

        // Handle key presses to switch between game states
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to the circle round state
			timerStarted = false;

        } else if (key == 'R' || key == 'r') {
            currentState = titleState; // Switch back to the title screen state
			//GameState.totalScore = 0;
        }

		if (currentState instanceof CircleState && (key == 'E' || key == 'e')) 
		{
			currentState = new GameOverState(this);
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
			}
			
            if (currentState.shapes.isEmpty()) 
			{
                int finalScore = GameState.score; // Access the total score
                System.out.println("Final Score: " + finalScore); // Debugging output for the final score
                currentState = new GameOverState(this); // Switch to the game-over state
            }
        }
    }

    public void mousePressed() {
        // Delegate mouse pressed event to the current state
        currentState.mousePressed(mouseX, mouseY);
    }

}
