package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
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
	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());

		if(object.canMove(newPosition))	object.setPosition(newPosition);

		for(AbstractObjects object1: game.getObjectsFromPosition(newPosition)) {
			if (object1 instanceof InteractiveObjects && object1.canInteract() && object.canMove(newPosition)) {
				((InteractiveObjects)object1).interact(object,lastKeyPressed);
				System.out.println("Oi Gato");
			}
		}
		ImageMatrixGUI.getInstance().update();
	}
}
