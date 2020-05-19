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

	public boolean isBlocked() {
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects object: game.getObjectsFromPosition(tpPosition()))
			if(object instanceof ActiveObjects) {
				return true;
			}
		return false;
	}
	
	public void setStuck() {
		SokobanGame game = SokobanGame.getInstance();
		setInteract(false);
		for(AbstractObjects object: game.getObjectsFromPosition(tpPosition()))
			if (object instanceof InteractiveObjects)
				object.setInteract(false);
	}

	public Point2D tpPosition() {
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects teleport: game.getObjects())
			if(!teleport.equals(this) && teleport.getName().equals(getName()))
				return teleport.getPosition();
		return getPosition();
	}
	
	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		if(object instanceof ActiveObjects) {
			if(!isBlocked()) {
				setInteract(true);
				object.setPosition(tpPosition());

				System.out.println(object.getName() + " posição: " + object.getPosition());
				System.out.println("Nova posição: " + object.getPosition());
				System.out.println(" ");
			}
			else setStuck();
		}
	}
}