package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Ice extends AbstractObjects implements InteractiveObjects{

	public Ice(Point2D position) {
		super(position, true,true);
	}
	
	@Override
	public String getName() {
		return "Ice";
	}

	@Override
	public void interact() {
//		SokobanGame game = SokobanGame.getInstance();
		
	}
}
