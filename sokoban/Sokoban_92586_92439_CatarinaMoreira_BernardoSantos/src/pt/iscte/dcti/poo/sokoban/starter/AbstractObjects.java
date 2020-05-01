package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractObjects implements ImageTile{

	private Point2D position;
	private boolean transposable;

	public AbstractObjects(Point2D position, boolean transposable) {
		super();
		this.position = position;
		this.transposable = transposable;
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

	public boolean isTransposable() {
		return transposable;
	}

	public void setTransposable(boolean transposable) {
		this.transposable = transposable;
	}

	//NEW
	public boolean canMove(int lastKeyPressed) {
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects object : game.getObjects())
			if(object.getPosition().equals(newPosition) && !object.equals(this) && object.getName()!="Player")
				if (object.getName() == "Wall" || object.getLayer()>=getLayer())
					return false;
		return true;
	}
}