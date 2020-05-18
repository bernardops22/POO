package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Teleport extends AbstractObjects implements InteractiveObjects{

	private String imageName;

	public Teleport(Point2D position) {
		super(position, true,true);
		imageName = "Green_Teleport";
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String getName() {
		return imageName;
	}

	@Override   					
	public int getLayer() {
		return 1;
	}

	//NEW
	public boolean isStuck() {
		SokobanGame game = SokobanGame.getInstance();
		//		for(AbstractObjects object1: game.getObjectsFromPosition(getPosition()))
		for(AbstractObjects object: game.getObjectsFromPosition(tpPosition()))
			if(object instanceof ActiveObjects /*|| object1 instanceof ActiveObjects*/) {
				setInteract(false);
				return true;
			}
		return false;
	}

	//NEW
	public Point2D tpPosition() {
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects teleport: game.getObjects())
			if(!teleport.equals(this) && teleport.getName().equals(getName()))
				return teleport.getPosition();
		return getPosition();
	}

	//NEW
	@Override
	public void interact(AbstractObjects object) {
		if(object instanceof ActiveObjects) {
			if(!isStuck()) {
				setInteract(true);
				object.setPosition(tpPosition());

				System.out.println(object.getName() + " posição: " + object.getPosition());
				System.out.println("Nova posição: " + object.getPosition());
				System.out.println(" ");
			}
		}
	}
}