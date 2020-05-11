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

	//Magic numbers
	public static final int HEIGHT = 10;
	public static final int WIDTH = 10;
	private static final int NUMBER_OF_LEVELS = 4; //Max number of levels to play

	private static SokobanGame INSTANCE;

	private Player player;
	private ArrayList<AbstractObjects> objects = new ArrayList<AbstractObjects>();
	private int energy = 100;
	private int steps = 0;
	private int nLevel = 0;
	private int nTarget = 0;
	private String playerName = "";
	private boolean hammer;

	//Constructor
	private SokobanGame(){
		playerName();

		ImageMatrixGUI.getInstance().setName("Best Sokoban Ever");
		ImageMatrixGUI.getInstance().setStatusMessage("   Player: " + playerName + 
				"   |    Level: " + nLevel + "    |    Energy: " + 
				energy + "    |    Steps: " + steps + "    |    R/ESC to restart!");

		buildSampleLevel();
	}

	//Singleton
	public static SokobanGame getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SokobanGame();
		return INSTANCE;
	}

	//Getters and setters
	public ArrayList<AbstractObjects> getObjects(Point2D position) {
		ArrayList<AbstractObjects> objsp = new ArrayList<AbstractObjects>();
		for(AbstractObjects object: objects) {
			if (object.getPosition().equals(position))
				objsp.add(object);
		}
		return objsp;
	}

	//NEW
	public boolean hasHammer() {
		return hammer;
	}

	//NEW
	public void setHammer(boolean hammer) {
		this.hammer = hammer;
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

	//Player info
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

	//Build the level
	public void buildSampleLevel() {
		setHammer(false);
		try {

			String filename = "levels/level" + nLevel + ".txt";
			File file = new File(filename);
			Scanner s =  new Scanner(file);

			String line;
			String[] symbols;
			int y = 0;

			this.nTarget=0;
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
						objects.add(new Hole(new Point2D(x,y),true));
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
						objects.add(new Battery(new Point2D(x,y),true));
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
					case "g":
						objects.add(new Ice(new Point2D(x,y)));
						break;
					case "%":
						objects.add(new BrittleWall(new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "m":
						objects.add(new Hammer (new Point2D(x,y)));
						objects.add(new Floor(new Point2D(x,y)));
						break;
					case "t":
						objects.add(new Teleport(new Point2D(x,y)));
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

	//When the user loses 
	public void repeatLevel() {
		Object[] options = {"Repeat level","Leave"};
		int response = JOptionPane.showOptionDialog(null,"What do you want to do?","GAME OVER",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(response == 0) {
			ImageMatrixGUI.getInstance().clearImages();
			objects.removeAll(objects);
			buildSampleLevel();
		}
		else ImageMatrixGUI.getInstance().dispose();
	}

	//Targets activated?
	public boolean levelCompleted() {
		int targetActivated = 0;
		for (AbstractObjects object: objects)
			if (object.getName()=="Box" && ((Box)object).isTargetActivated()) {
				targetActivated++;
			}
		if (targetActivated==nTarget)	return true;
		else return false;
	}

	//When the user completes a level
	public void nextLevel() {	
		if (levelCompleted()) {			
			System.out.println("You just completed the level.");
			try {
				FileWriter file = new FileWriter("points/punctuation.txt");
				file.write(playerName + " " + steps + " " + nLevel);
				file.close();

				JOptionPane.showMessageDialog(null, " Name: "+ playerName + "    Steps: "  + steps);

				nLevel++;

				//Level limitation
				if (nLevel > NUMBER_OF_LEVELS) {
					JOptionPane.showMessageDialog(null, "You won the game. Congrats!!" );
					ImageMatrixGUI.getInstance().dispose();
				}
				ImageMatrixGUI.getInstance().clearImages();
				objects.removeAll(objects);		
				buildSampleLevel();

			}catch(IOException e) {}
		}
	}

	//When the user presses a key
	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		if (player != null && Direction.isDirection(lastKeyPressed)) {	

			switch(lastKeyPressed){
			case KeyEvent.VK_LEFT:
				player.setImageName("Player_L");
				break;
			case KeyEvent.VK_UP:
				player.setImageName("Player_U");
				break;
			case KeyEvent.VK_RIGHT:
				player.setImageName("Player_R");
				break;
			case KeyEvent.VK_DOWN:
				player.setImageName("Player_D");
				break;
			}

			Point2D newPlayerPosition = player.getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
			for(AbstractObjects object: getObjects(newPlayerPosition)) {		

				Point2D newPosition = object.getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());

				if (newPosition.getX()>=0 && newPosition.getX()<WIDTH && newPosition.getY()>=0 && newPosition.getY()<HEIGHT) {	
					if(object.canMove(newPosition) && object.canInteract() && object instanceof ActiveObjects) { 
						((ActiveObjects)object).move(newPosition);
						for(AbstractObjects intObject: getObjects(newPosition))
							if (intObject instanceof InteractiveObjects && intObject.canInteract())
								((InteractiveObjects)intObject).interact(object);
						ImageMatrixGUI.getInstance().update();
					}
				}
			}
			
			for(AbstractObjects intObject: getObjects(newPlayerPosition))
				if (intObject.canInteract()) ((InteractiveObjects)intObject).interact(player);
			
			if (player.canMove(newPlayerPosition)) {								
				player.move(newPlayerPosition);
				setSteps(getSteps() + 1);
				setEnergy(getEnergy() - 1);
				
				ImageMatrixGUI.getInstance().update();
				
				//ERRO BRITTLE WALL vs ERRO HOLE
			}

		}

		if(energy<=0 || lastKeyPressed == KeyEvent.VK_R || lastKeyPressed == KeyEvent.VK_ESCAPE) {
			repeatLevel();	
			ImageMatrixGUI.getInstance().update();
		}
		nextLevel();
		ImageMatrixGUI.getInstance().update();
		ImageMatrixGUI.getInstance().setStatusMessage("   Player: " + playerName +
				"   |    Level: " + nLevel + "    |    Energy: " + energy + "    |    "
				+ "Steps: " + steps + "    |    R to restart!");
	}
}