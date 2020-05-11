package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Target extends AbstractObjects {
	
	public Target(Point2D position) {
		super(position,true,false);
	}

	@Override
	public String getName() {
		return "Target";
	}	
	
	@Override   					
	public int getLayer() {
		return 1;
	}
}