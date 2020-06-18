package com.mrh0.musiciansaddition.midi;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class MidiHandler {
	public IMidiEvent midiEvent;
	private ArrayList<MidiDevice> devices; 
	public MidiHandler(IMidiEvent midiEvent) {
		this.midiEvent = midiEvent;
		devices = new ArrayList<MidiDevice>();
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			try {
				MidiDevice device;
				device = MidiSystem.getMidiDevice(infos[i]);
				System.out.println(infos[i]);
				List<Transmitter> transmitters = device.getTransmitters();
				for(int j = 0; j<transmitters.size();j++) {
					transmitters.get(j).setReceiver(
					new MidiInputReceiver(device.getDeviceInfo().toString()));
				}
				
				Transmitter trans = device.getTransmitter();
				trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
				device.open();
				devices.add(device);
				System.out.println(device.getDeviceInfo());
			} 
			catch (MidiUnavailableException e) {}
		}
	}
	
	public void close() {
		for(MidiDevice d : devices)
			d.close();
	}
	
	public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	public class MidiInputReceiver implements Receiver {
		public String name;
		public MidiInputReceiver(String name) {
			this.name = name;
		}
		public void send(MidiMessage msg, long timeStamp) {
			if (msg instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) msg;
                //System.out.print("Channel: " + sm.getChannel() + " ");
                if (sm.getCommand() == NOTE_ON) {
                    int key = sm.getData1();
                    int octave = (key / 12)-1;
                    int note = key % 12;
                    String noteName = NOTE_NAMES[note];
                    int velocity = sm.getData2();
                    int mcnote = playNote(noteName, octave);
                    if(midiEvent != null)
                    	midiEvent.minecraftNote(mcnote,  velocity > 0);
                    //System.out.println("Played: " + mcnote); // playNote(noteName, octave)
                    //System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
                } else if (sm.getCommand() == NOTE_OFF) {
                    int key = sm.getData1();
                    int octave = (key / 12)-1;
                    int note = key % 12;
                    String noteName = NOTE_NAMES[note];
                    int velocity = sm.getData2();
                    //System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
                } else {
                    //System.out.println("Command:" + sm.getCommand());
                }
            } else {
                System.out.println("Midi Controller: " + msg.getClass());
            }
			
		}
		public void close() {}
	}
	
	public int playNote(String note, int octave) {
		int r = -1;
		if(octave == 2) {
			switch(note) {
				case "F#":
					r = 0; break;
				case "G":
					r = 1; break;
				case "G#":
					r = 2; break;
				case "A":
					r = 3; break;
				case "A#":
					r = 4; break;
				case "B":
					r = 5; break;
			}
		}
		if(octave == 3) {
			switch(note) {
				case "F#":
					r = 12; break;
				case "G":
					r = 13; break;
				case "G#":
					r = 14; break;
				case "A":
					r = 15; break;
				case "A#":
					r = 16; break;
				case "B":
					r = 17; break;
				case "C":
					r = 6; break;
				case "C#":
					r = 7; break;
				case "D":
					r = 8; break;
				case "D#":
					r = 9; break;
				case "E":
					r = 10; break;
				case "F":
					r = 11; break;
			}
		}
		
		if(octave == 4) {
			switch(note) {
				case "F#":
					r = 24; break;
				case "C":
					r = 18; break;
				case "C#":
					r = 19; break;
				case "D":
					r = 20; break;
				case "D#":
					r = 21; break;
				case "E":
					r = 22; break;
				case "F":
					r = 23; break;
			}
		}

		return r;
	}
}