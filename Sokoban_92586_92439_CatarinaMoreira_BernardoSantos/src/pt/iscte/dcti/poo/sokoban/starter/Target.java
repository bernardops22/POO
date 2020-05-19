package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Target extends AbstractObjects implements InteractiveObjects {
	
	private boolean activated = false;
	
	public Target(Point2D position) {
		super(position,true,true);
	}

	@Override
	public String getName() {
		return "Target";
	}	

	@Override   					
	public int getLayer() {
		return 1;
	}
	
	public boolean isTargetActivated() {
		return activated;
	}

	public void setTargetActivation(boolean activated) {
		this.activated = activated;
	}

	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		setTargetActivation(false);
		if (object.getName().equals("Box"))
			setTargetActivation(true);		
	}


}