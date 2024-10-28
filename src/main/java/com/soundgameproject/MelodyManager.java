// Class title: MelodyManager
// Anika Krieger
// Oct 27
// Particle Engine 4
// Description: 

package com.soundgameproject;

import java.util.ArrayList;


public class MelodyManager {

     MelodyPlayer player; //play a midi sequence
     ArrayList<MidiFileToNotes> midiNotes; //read a midi file
     ArrayList<MelodyPlayer> players;
    //static int noteCount = 0; //index into the array of notes to send to the MIDI file.

    MelodyManager()
    {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    public void playMelodies()
    {
       assert(player != null);
       for (MelodyPlayer player : players)
            player.play(); 
    }

    //opens the midi file, extracts a voice, then initializes a melody player with that midi voice (e.g. the melody)
    void addMidiFiles(String filePath) {

        int index = players.size();

        players.add( new MelodyPlayer(100, "Bus 1")); //sets up the player with your bus. 
        //player.listDevices(); //prints available midi devices to the console -- find your device

        midiNotes.add(new MidiFileToNotes(filePath)); // creates a new MidiFileToNotes

        int noteCount = midiNotes.get(index).getPitchArray().size(); //get the number of notes in the midi file

        assert(noteCount > 0); // make sure it got some notes

        //sets the player to the melody to play the voice grabbed from the midi file above
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());

        players.get(index).reset();
    }

    void start(int index)
    {
        players.get(index).reset();
    }
}
