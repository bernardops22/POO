package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractObjects{
	
	public Battery(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return "Battery";
	}
	
	@Override
	public int getLayer() {
		return 2;
	}
	
}
