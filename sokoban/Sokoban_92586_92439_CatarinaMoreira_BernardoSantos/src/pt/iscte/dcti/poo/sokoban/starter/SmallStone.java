package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends AbstractObjects implements ActiveObjects{

	public SmallStone(Point2D position) {
		super(position,false,false);
	}

	@Override
	public String getName() {
		return "SmallStone";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void move(int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());

		for(AbstractObjects object : game.getObjects()) {
			if(object.getPosition().equals(newPosition) && object.hashCode()!=hashCode()) {
				if((object.canMove(lastKeyPressed)  || object.isTransposable()))
					setPosition (newPosition);
				if(object.getName()=="Hole"){
					ImageMatrixGUI.getInstance().removeImage(this);
					setTransposable(true);
				}
			}
		}
	}
}
