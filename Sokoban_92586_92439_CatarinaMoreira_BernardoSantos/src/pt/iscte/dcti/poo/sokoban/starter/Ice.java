package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Ice extends AbstractObjects implements InteractiveObjects{

	public Ice(Point2D position) {
		super(position, true,true);
	}

	@Override
	public String getName() {
		return "Ice";
	}

	@Override   					
	public int getLayer() {
		return 1;
	}


	//NEW
	//	@Override
	//	public void interact(AbstractObjects object, Point2D direction) {
	////		System.out.println("Pode mover");
	////		if (!object.getName().equals(getName())) {
	////			System.out.println("Vai mover");
	////			object.setPosition(newPosition);
	////		}
	//	}	

	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		for(AbstractObjects ice: game.getObjectsFromPosition((getPosition()))){
			for(AbstractObjects ice1: game.getObjectsFromPosition(newPosition))
				if(getPosition().equals(ice.getPosition()) && ice.getName().equals(getName()) && ice.isTransposable())
					setPosition(newPosition);
		}
	}
}
