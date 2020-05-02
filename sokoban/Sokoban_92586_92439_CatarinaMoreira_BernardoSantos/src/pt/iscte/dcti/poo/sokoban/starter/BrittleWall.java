package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class BrittleWall extends AbstractObjects implements InteractiveObjects{

	public BrittleWall(Point2D position) {
		super(position, false);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canInteract() {
		// TODO Auto-generated method stub
		return false;
	}

}
