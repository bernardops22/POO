package pt.iscte.dcti.poo.sokoban.starter;

import java.util.Random;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Empilhadora implements ImageTile{

	private Point2D position;
	private String imageName;
	
	public Empilhadora(Point2D initialPosition){
		position = initialPosition;
		imageName = "Empilhadora_D";
	}
	
	@Override
	public String getName() {
		return imageName;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 2;
	}

	public void move() {

		Direction randomDirection = Direction.random();
				
		Point2D newPosition = position.plus(randomDirection.asVector());
		if (newPosition.getX() >= 0 && newPosition.getX() < SokobanGame.WIDTH && 
				newPosition.getY() >= 0 && newPosition.getY() < SokobanGame.HEIGHT ){
			position = newPosition;
		}
		
		ImageMatrixGUI.getInstance().update();
	}
}
