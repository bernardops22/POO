package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Objetos implements ImageTile{

	private Point2D position;
	
	public Objetos(Point2D position) {
		super();
		this.position = position;
	}
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override   					
	public int getLayer() {
		return 1;
	}
}