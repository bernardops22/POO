package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends AbstractObjects implements AnimatedObjects{
	
	public SmallStone(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "SmallStone";
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	@Override
	public void move(int lastKeyPressed) {
		
	}
}
