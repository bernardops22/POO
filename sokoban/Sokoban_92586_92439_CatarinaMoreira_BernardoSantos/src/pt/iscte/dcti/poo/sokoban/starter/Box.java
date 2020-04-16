package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements ActiveObjects{

	private SokobanGame game;

	public Box(Point2D position,SokobanGame game) {
		super(position,false);
		this.game=game;
	}

	@Override
	public String getName() {
		return "Box";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void move(int lastKeyPressed) {
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		for(AbstractObjects object : game.getObjects()) {
			if(object.isTransposable())
				if(object.getPosition().equals(newPosition) && object.getLayer()<getLayer())
					setPosition (newPosition);
		}
	}
	
	public boolean activate() {
		boolean val = false;
		for (AbstractObjects object: game.getObjects()){
			if (object.getName()=="Target") {
				if (getPosition() == object.getPosition()) {
					val = true;
					break;
				}
			}
		}
		return val;
	}

}