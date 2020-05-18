package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractObjects implements ImageTile{

	private Point2D position;
	private boolean transposable;
	private boolean interact;

	public AbstractObjects(Point2D position, boolean transposable, boolean interact) {
		super();
		this.position = position;
		this.transposable = transposable;
		this.interact = interact;
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
		return 2;
	}

	public boolean isTransposable() {
		return transposable;
	}

	public void setTransposable(boolean transposable) {
		this.transposable=transposable;
	}

	public boolean canInteract() {
		return interact;
	}

	public void setInteract(boolean interact) {
		this.interact = interact;
	}

	public boolean canMove(Point2D newPosition) {
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects object : game.getObjectsFromPosition(newPosition))
			if(!object.equals(this) && !object.isTransposable()) return false;
		return true;
	}

	public void move(Point2D newPosition) {
		setPosition (newPosition);
	}
}