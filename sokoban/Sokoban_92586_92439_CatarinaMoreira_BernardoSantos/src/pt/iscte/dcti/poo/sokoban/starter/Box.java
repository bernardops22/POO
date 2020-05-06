package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements ActiveObjects{

	private boolean activated = false;

	public Box(Point2D position, boolean activated) {
		super(position,false,false);
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

	public boolean isTargetActivated() {
		return activated;
	}

	public void setTargetActivation(boolean activated) {
		this.activated = activated;
	}

	@Override
	public void move(int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());

		for(AbstractObjects object : game.getObjects()) {
			if(object.getPosition().equals(newPosition) && object.hashCode()!=hashCode()){
				if((object.canMove(lastKeyPressed)  || object.isTransposable())) {
					setPosition (newPosition);
					setTargetActivation(false);
					if (object.getName() == "Target")
						setTargetActivation(true);
				}
				if(object.getName()=="Hole"){
					ImageMatrixGUI.getInstance().removeImage(this);
					setTransposable(true);
				}
			}
		}
	}
}