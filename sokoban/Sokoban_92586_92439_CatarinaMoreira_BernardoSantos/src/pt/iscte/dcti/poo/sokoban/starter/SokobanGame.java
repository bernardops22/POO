package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

	public static final int HEIGHT = 10;
	public static final int WIDTH = 10;
	
	private static SokobanGame INSTANCE;
	
	private Player player;
	private ArrayList<AbstractObjects> objects = new ArrayList<AbstractObjects>();
	private int energy = 100;
	private int steps = 0;
	private int nLevel = 0;
	private int nTarget = 0;
	private String playerName = "";

	private SokobanGame(){
		playerName();

		ImageMatrixGUI.getInstance().setName("Best Sokoban Ever");
		ImageMatrixGUI.getInstance().setStatusMessage("   Player: " + playerName + 
				"   |    Level: " + nLevel + "    |    Energy: " + 
				energy + "    |    Steps: " + steps);

		buildSampleLevel();
	}
	
	public static SokobanGame getInstance() {
		if (INSTANCE == null)
			 INSTANCE = new SokobanGame();
		return INSTANCE;
	}

	public ArrayList<AbstractObjects> getObjects() {
		return objects;
	}

	public int getEnergy() {
		return energy;
	}

	public int getSteps() {
		return steps;
	}

	public void setEnergy(int energia) {
		this.energy = energia;
	}

	public void setSteps(int passos) {
		this.steps = passos;
	}

	public void playerName() {
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

	public void buildSampleLevel() {
		try {

			String filename = "levels/level" + nLevel + ".txt";
			File file = new File(filename);
			Scanner s =  new Scanner(file);

			String line;
			String[] symbols;
			int y = 0;

			this.steps=0;
			this.energy=100;

			while(s.hasNextLine()) {

				line = s.nextLine();
				symbols = line.split("");

				for(int x = 0; x!=10 ; x++) {
					switch(symbols[x]) {
					case "#" :
						objects.add(new Wall(new Point2D(x,y)));
						break;
					case "C":
						objects.add(new Box(new Point2D(x,y),false));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "O":
						objects.add(new Hole(new Point2D(x,y)));
						break;
					case "X":
						objects.add(new Target(new Point2D(x,y)));
						nTarget++;
						break;
					case "E":
						player = new Player(new Point2D(x,y));
						objects.add(new Floor(new Point2D(x,y)));
						objects.add(player);
						break;
					case "b":
						objects.add(new Battery(new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "S":
						objects.add(new BigStone(new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "s":
						objects.add(new SmallStone(new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case " ":
						objects.add(new Floor(new Point2D(x,y)));
					}
				}
				y++;
			}
			s.close();
			for(ImageTile image: objects)
				ImageMatrixGUI.getInstance().addImage(image);

		}catch (FileNotFoundException e){
		}
	}

	public void repeatLevel() {
		Object[] options = {"Repeat level","Leave"};
		int response = JOptionPane.showOptionDialog(null,"What do you want to do?","GAME OVER",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(response == 0) {
			ImageMatrixGUI.getInstance().clearImages();
			objects.removeAll(objects);
			nTarget = 0;
			buildSampleLevel();
		}
		else ImageMatrixGUI.getInstance().dispose();
	}
	
	public boolean levelComplete() {
		int a = 0;
		for (AbstractObjects object: getObjects())
			if (object.getName()=="Box" && ((Box)object).isActivation()==true) {
				System.out.println(a);
				a++;
			}
		if (a==nTarget)	return true;
		else return false;
	}
	
	public void nextLevel() {	
		if (levelComplete()==true) {			
			System.out.println("You just completed the level.");
			try {
				FileWriter file = new FileWriter("points/punctuation.txt");
				file.write(playerName + " " + steps + " " + nLevel);
				file.close();

				JOptionPane.showMessageDialog(null, " Name: "+ playerName + "    Steps: "  + steps);

				ImageMatrixGUI.getInstance().clearImages();
				objects.removeAll(objects);
				nLevel++;
				nTarget=0;
				buildSampleLevel();

			}catch(IOException e) {}
		}
	}

	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		if (player != null) {
			if(energy==0 || lastKeyPressed == KeyEvent.VK_R || lastKeyPressed == KeyEvent.VK_ESCAPE) {
				repeatLevel();	
				ImageMatrixGUI.getInstance().update();
			}
			if (Direction.isDirection(lastKeyPressed)) {
				player.move(lastKeyPressed);
				ImageMatrixGUI.getInstance().update();
				nextLevel();
			}
			ImageMatrixGUI.getInstance().setStatusMessage("   Player: " + playerName +
					"   |    Level: " + nLevel + "    |    Energy: " + energy + "    |    "
					+ "Steps: " + steps);
		}
	}
}