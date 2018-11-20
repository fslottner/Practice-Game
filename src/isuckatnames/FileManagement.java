package isuckatnames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileManagement {

	private static final String filePathHighscores = "C:\\Users\\defaultuser0\\Documents\\Wave\\highscores.txt";
	private static final String filePathSpawn ="C:\\Users\\defaultuser0\\Documents\\Wave\\waves\\4.txt";
	
	
	
	private static String fetchFile(String filePath) throws IOException {
		
		FileInputStream reader = new FileInputStream(new File(filePath));
		InputStreamReader ut8_reader = new InputStreamReader(reader, "UTF-8");
		BufferedReader buffrad_reader = new BufferedReader(ut8_reader);
		
		String textLäst = "";
		String radFrånFil = buffrad_reader.readLine();
		while (radFrånFil != null) {
		    textLäst += radFrånFil;
		    radFrånFil = buffrad_reader.readLine();
		    if (radFrånFil != null) radFrånFil += "\n";
		}
		
		//String textLäst = buffrad_reader.readLine();
		
		// stäng filen..

		buffrad_reader.close();
				
		System.out.println(textLäst);
		return textLäst;
	}
	
	private static void editFile(String toWrite, String filePath) throws IOException {
		
		FileOutputStream writer = new FileOutputStream(new File(filePath));
		OutputStreamWriter ut8_writer = new OutputStreamWriter(writer, "UTF-8");
		BufferedWriter buffrad_writer = new BufferedWriter(ut8_writer);

		// läs in text i fil
		buffrad_writer.write(toWrite);
		buffrad_writer.close();
	}
	
	public static int[] getHighestScores(int score) {
		
		int[] highscores = fetchHighscores();
		int[] newHighscores = insertWithMaxLength(score, highscores, 3);
		
		try {
			editFile(numArrayToSaveFormat(newHighscores), filePathHighscores);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newHighscores;
		
	}
	
	private static String numArrayToSaveFormat(int[] numArray) {
		String outString = "";
		for (int number : numArray) {
			outString += Integer.toString(number) + "*";
			
		}
		
		return outString;
		
	}
	
	private static int[] insertWithMaxLength(int num, int[] numList, int maxNums) {	
		
		int currentNum = num;
		int[] outArray;
		boolean discardLowest = false;
		
		if (numList.length == maxNums) {
			outArray = new int[numList.length];
			discardLowest = true;
		} else {
			outArray = new int[numList.length + 1];
		}
				
		for (int i = 0; i < numList.length; i++) {
			
			if (currentNum > numList[i]) {
				outArray[i] = currentNum;
				currentNum = numList[i];
			} else {
				outArray[i] = numList[i];
			}
			
		}
						
		if (!discardLowest) {
			outArray[outArray.length - 1] = currentNum;
		}
		
		return outArray;
	}
	
	private static int[] fetchHighscores() {
		
		String highscores = null;
		try {
			highscores = fetchFile(filePathHighscores);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] highscoreArrayString = highscores.split("\\*");
		int[] highscoreArrayNum = new int[highscoreArrayString.length];
		
		// last element is a newline that should be excluded
		for (int i = 0; i < highscoreArrayString.length; i++) {
			//System.out.println(highscoreArrayString[i]);
			highscoreArrayNum[i] = Integer.parseInt(highscoreArrayString[i]);
		}
		
		return highscoreArrayNum;

	}
	
}
