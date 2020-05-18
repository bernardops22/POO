package pt.iscte.dcti.poo.sokoban.starter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

public class Scores {

	private static String playerName = "";	

	public static String getPlayerName() {
		return playerName;
	}

	public static void playerName() {
		String response = JOptionPane.showInputDialog(null,"What is your name?",
				"Sokoban by Hiroyuki Imabayashi (1981)",JOptionPane.QUESTION_MESSAGE);
		if(response == null) throw new NullPointerException("See you soon my friend");
		if (response.length()==0) {
			JOptionPane.showMessageDialog(null,"You must have a gamer name");
			playerName();
			playerName = response;
		}
		playerName = response;
	}

	public static void writeToFile() {
		SokobanGame game = SokobanGame.getInstance();
		try {
			FileWriter file = new FileWriter("points/level" + game.getnLevel() + ".txt",true);	
			BufferedWriter out = new BufferedWriter(file);
			out.write(playerName + ":" + Integer.toString(game.getSteps()));
			out.newLine();
			out.close();
			file.close();
		}
		catch(IOException e) {}
	}

	public static void readFromFile() {
		SokobanGame game = SokobanGame.getInstance();
		Map<String, String> map = new HashMap<>();

		try(Stream<String> lines = Files.lines(Paths.get("points/level" + game.getnLevel() + ".txt"))){
			lines.filter(line -> line.contains(":")).forEach(
					line -> map.putIfAbsent(line.split(":")[0], line.split(":")[1]));
			
			map.entrySet().stream().sorted((k1, k2) -> k1.getValue().compareTo(k2.getValue()))
			.forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
		} 
		catch (IOException e) {}
//		JOptionPane.showMessageDialog(null,map);    
	}
}