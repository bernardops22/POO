package pt.iscte.dcti.poo.sokoban.starter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
		Map<String, String> map = readFromFile();
		if(map.containsKey(playerName) && Integer.parseInt(map.get(playerName))>game.getSteps()) {
			map.replace(playerName, Integer.toString(game.getSteps()));
			
			try {			
				FileWriter file = new FileWriter("points/level" + game.getnLevel() + ".txt");	
				BufferedWriter out = new BufferedWriter(file);
				for (String key : map.keySet()) {
					out.write(key + ":" + map.get(key));
					out.newLine();
				}
				out.close();
				file.close();
			}catch(IOException e) {}
		}
		if (!map.containsKey(playerName)) {
			try {			
				FileWriter file = new FileWriter("points/level" + game.getnLevel() + ".txt",true);	
				BufferedWriter out = new BufferedWriter(file);
				out.write(playerName + ":" + game.getSteps());
				out.newLine();
				out.close();
				file.close();
			}catch(IOException e) {}
		}
	}

	public static LinkedHashMap<String, String> readFromFile() {
		SokobanGame game = SokobanGame.getInstance();
		Map<String, String> map = new HashMap<>();
		LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
		
		try(Stream<String> lines = Files.lines(Paths.get("points/level" + game.getnLevel() + ".txt"))){
			lines.filter(line -> line.contains(":")).forEach(
					line -> map.putIfAbsent(line.split(":")[0], line.split(":")[1]));
			lines.close();
			
			List<String> mapKeys = new ArrayList<>(map.keySet());
		    List<String> mapValues = new ArrayList<>(map.values());
		    Collections.sort(mapValues);
		    
		    Iterator<String> valueIt = mapValues.iterator();
		    while (valueIt.hasNext()) {
		        String value = valueIt.next();
		        Iterator<String> keyIt = mapKeys.iterator();

		        while (keyIt.hasNext()) {
		            String key = keyIt.next();
		            String comp1 = map.get(key);
		            String comp2 = value;

		            if (comp1.equals(comp2)) {
		                keyIt.remove();
		                sortedMap.put(key, value);
		                break;
		            }
		        }
		    }
		}
		catch (IOException e) {}  
		return sortedMap;
	}	

	public static void highScores() {
		writeToFile();
		Map<String, String> map = readFromFile();
		String[] scores = new String[map.size()+1];
		String[] key = map.keySet().toArray(new String[0]);
		String[] value = map.values().toArray(new String[0]);

		for (int i = 1; i <= map.size(); i++) {
			scores[i] = i + "º " + key[i-1] + ": " + value[i-1];
			if (scores[i].contains(playerName)) scores[i] = i + "º " + key[i-1] + ": " + value[i-1] + "       <--";
		}
		scores[0] = "KINGS OF SOKOBAN";

		JOptionPane.showMessageDialog(null,scores);
	}
}