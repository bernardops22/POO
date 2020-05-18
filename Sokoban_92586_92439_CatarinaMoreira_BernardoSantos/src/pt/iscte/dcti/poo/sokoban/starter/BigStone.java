package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends AbstractObjects implements ActiveObjects{

	public BigStone(Point2D position) {
		super(position,false,true);
	}

	@Override
	public String getName() {
		return "BigStone";
	}
}
