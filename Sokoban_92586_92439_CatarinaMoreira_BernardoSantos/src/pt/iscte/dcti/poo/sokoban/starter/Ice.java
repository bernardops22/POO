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
	public int getLayer() {
		return 1;
	}


	//NEW
	@Override
	public void interact(AbstractObjects object) {
//		System.out.println("Pode mover");
//		if (!object.getName().equals(getName())) {
//			System.out.println("Vai mover");
//			object.setPosition(newPosition);
//		}
	}	
	
	
}
