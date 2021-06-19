package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import util.Constants;

public class GameSaver {
	
	public boolean saveGame(Game game) {
		boolean isSaveSuccess = false;
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream(new File(Constants.GAME_SAVE_FILEPATH));
			o = new ObjectOutputStream(f);
			// Write the game state to file
			o.writeObject(game);
			o.close();
			f.close();
			isSaveSuccess = true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error initializing stream");
		}
		return isSaveSuccess;
	}
	
}
