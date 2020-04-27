package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
//import java.util.Observable;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {
 	
	private Player player; 
	
	public SokobanGame(){
		
		ArrayList<ImageTile> tiles = buildSampleLevel();
		player = new Player(new Point2D(1,1));
		tiles.add(player);
		ImageMatrixGUI.getInstance().addImages(tiles);
		
	}
	
	private ArrayList<ImageTile> buildSampleLevel(){
		
		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
		
		// Build 10x10 floor tiles
		for (int y=0; y!=10; y++)
			for (int x=0; x!=10 ; x++)
				sampleLevelTiles.add(new Chao(new Point2D(x,y)));
				
		return sampleLevelTiles;	
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
