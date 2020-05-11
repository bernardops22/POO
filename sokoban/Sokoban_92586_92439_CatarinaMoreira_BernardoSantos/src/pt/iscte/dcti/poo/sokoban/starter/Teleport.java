package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Teleport extends AbstractObjects implements InteractiveObjects{

	private String imageName;

	public Teleport(Point2D position) {
		super(position, true,true);
		imageName = "Blue_Teleport";
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

//	public boolean isStuck(AbstractObjects object) {
//		if (object instanceof ActiveObjects && object.getPosition().equals(tpPosition()))
//			return true;
//		return false;
//	}
//
//	public Point2D tpPosition() {
//		SokobanGame game = SokobanGame.getInstance();
//		for(AbstractObjects teleport: game.getObjects(getPosition()))
//			if(!teleport.equals(this) && teleport.getName().equals(getName()))
//				return teleport.getPosition();
//		return getPosition();
//	}

	@Override
	public void interact(AbstractObjects object) {
//		if(object instanceof ActiveObjects) {
//			if (isStuck(object)) {
//				setInteract(false);
//				System.out.println("Vou mudar para verde");
//				setImageName("Green_Teleport");
//			}
//			if(!isStuck(object) && canInteract()) {
//				setInteract(true);
//				System.out.println("Vou mudar para azul");
//				setImageName("Blue_Teleport");
//				System.out.println(object.getName() + " posição: " + object.getPosition());
//				object.setPosition(tpPosition());
//				System.out.println("Nova posição: " + object.getPosition());
//				System.out.println(" ");
//			}
//		}
	}
}