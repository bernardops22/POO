package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends AbstractObjects implements ActiveObjects{

	private boolean stuck = false;

	public BigStone(Point2D position) {
		super(position,false,false);
	}

	@Override
	public String getName() {
		return "BigStone";
	}

	@Override   					
	public int getLayer() {
		return 2;
	}

	public boolean isStuck() {
		return stuck;
	}
	public void setStuck (boolean stuck) {
		this.stuck = stuck;
	}

	@Override
	public void move(int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		for(AbstractObjects object : game.getObjects()) 
			if(getPosition().equals(object.getPosition())) {
				if(object.getName() == "Hole") {
					((InteractiveObjects)object).interact();
					setStuck(true);
				}
				if((object.hashCode() != hashCode()) && object.canMove(lastKeyPressed)  || object.isTransposable())
						if(!isStuck()) setPosition (newPosition);
			}
	}
}