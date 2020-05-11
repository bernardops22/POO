package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements ActiveObjects{

	private boolean activated = false;

	public Box(Point2D position, boolean activated) {
		super(position,false,true);
		this.activated = activated;
	}

	@Override
	public String getName() {
		return "Box";
	}

	public boolean isTargetActivated() {
		return activated;
	}

	public void setTargetActivation(boolean activated) {
		this.activated = activated;
	}

	@Override
	public void move(Point2D newPosition) {
		SokobanGame game = SokobanGame.getInstance();

		for(AbstractObjects object : game.getObjects(newPosition)) {
			setPosition (newPosition);
			setTargetActivation(false);
			if (object.getName().equals("Target"))
				setTargetActivation(true);

		}
	}
}