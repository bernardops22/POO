package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements ActiveObjects{

	public Box(Point2D position) {
		super(position,false,true);
	}

	@Override
	public String getName() {
		return "Box";
	}
}