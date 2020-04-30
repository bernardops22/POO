package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements ActiveObjects{

	private boolean activated = false;

	public Box(Point2D position, boolean activated) {
		super(position,false);
		this.activated = activated;
	}

	@Override
	public String getName() {
		return "Box";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivation(boolean activated) {
		this.activated = activated;
	}

	@Override
	public void move(int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		for(AbstractObjects object : game.getObjects()) {
//			if (object.getPosition().equals(newPosition) && !object.canMove());
//				object.setMove(false);
			
			if(object.getPosition().equals(newPosition) && object.isTransposable()) {
					setPosition (newPosition);
					setActivation(false);
					if (object.getName() == "Target")
						setActivation(true);
			}
		}
	}
}