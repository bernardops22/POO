package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends AbstractObjects implements ActiveObjects{

	public SmallStone(Point2D position) {
		super(position,false,true);
	}

	@Override
	public String getName() {
		return "SmallStone";
	}
}
