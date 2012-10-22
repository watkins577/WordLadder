package uk.me.andrewwatkins.input;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileInput {

	private static FileInput inst = new FileInput();
	
	private FileInput() {
		
	}
	
	public static FileInput getInstance() {
		return inst;
	}
	
	public List<String> readFile(String fileLoc) {
		boolean foundFile = false;
		List<String> retVal = new ArrayList<String>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileLoc);
			foundFile = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (foundFile) {
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader inp = new BufferedReader(new InputStreamReader(dis));
			String nextLine;
			try {
				while ((nextLine = inp.readLine()) != null) {
					retVal.add(nextLine);
				}
				inp.close();
				dis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
}
