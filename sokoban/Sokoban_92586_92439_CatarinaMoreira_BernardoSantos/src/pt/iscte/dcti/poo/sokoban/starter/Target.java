package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Target extends AbstractObjects {
	
	private boolean activation = false;
	
	public Target(Point2D position, boolean activation) {
		super(position,true);
		this.activation = activation;
	}

	@Override
	public String getName() {
		return "Target";
	}
	
	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	public boolean isActivation() {
		return activation;
	}
	
	
}