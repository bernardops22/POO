package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends AbstractObjects implements ActiveObjects{
	
	private SokobanGame game;
	
	public BigStone(Point2D position, SokobanGame game) {
		super(position,false);
		this.game = game;
	}

	@Override
	public String getName() {
		return "BigStone";
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
	
	
}
