package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Wall extends AbstractObjects{
	
	public Wall(Point2D position) {
		super(position,false,false);
	}

	@Override
	public String getName() {
		return "Wall";
	}
	
}