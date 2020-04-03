package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {
 	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	private List<ImageTile> tiles = new ArrayList<ImageTile>();
	private Empilhadora player; 

	public SokobanGame(){		
		tiles = buildSampleLevel();
		player = new Empilhadora(new Point2D(1,1));
		tiles.add(player);
		ImageMatrixGUI.getInstance().addImages(tiles);	
	}
	
	private List<ImageTile> buildSampleLevel(){
		// Build 10x10 floor tiles
		for (int y = 0; y != HEIGHT; y++)
			for (int x = 0; x != WIDTH ; x++)
				tiles.add(new Chao(new Point2D(x,y)));		
		return tiles;	
	}
	
	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		System.out.println("Key pressed " + lastKeyPressed);
		// VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT
		if (lastKeyPressed == KeyEvent.VK_ENTER ) {
			if (player != null)
				player.move();
		}		
	}
}
