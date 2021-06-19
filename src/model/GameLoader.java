package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import util.Constants;

public class GameLoader {
	
	public Game loadGame() {
		Game game = null;
		FileInputStream fi = null;
		ObjectInputStream oi = null;
		try {
			fi = new FileInputStream(new File(Constants.GAME_SAVE_FILEPATH));
			oi = new ObjectInputStream(fi);
			// Read game objects
			game = (Game) oi.readObject();
			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("Save file not found");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			System.out.println("Save file corrupted");
			//e.printStackTrace();
		}
		return game;
	}

}
