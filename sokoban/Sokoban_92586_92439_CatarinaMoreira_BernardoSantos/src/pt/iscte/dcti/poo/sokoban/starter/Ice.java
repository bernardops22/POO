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

//	public void move(Point2D newPosition) {
//		System.out.println("Pode mover");
//		SokobanGame game = SokobanGame.getInstance();
//		for (AbstractObjects object: game.getObjects(newPosition)) {
//			if (!object.getName().equals(getName())) {
//				System.out.println("Vai mover");
//				object.setPosition(newPosition);
//			}
//		}
//	}

	@Override
	public void interact(AbstractObjects object) {
		// TODO Auto-generated method stub
		
	}
}
