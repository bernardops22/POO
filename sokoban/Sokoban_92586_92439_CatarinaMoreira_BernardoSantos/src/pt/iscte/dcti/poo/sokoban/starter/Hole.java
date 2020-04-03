package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObjects {
	
	public Hole(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Hole";
	}
	
}
