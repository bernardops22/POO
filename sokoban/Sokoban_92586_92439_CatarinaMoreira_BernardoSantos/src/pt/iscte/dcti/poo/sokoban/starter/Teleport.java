package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
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

	//A ser revisto
	public boolean setStuck(AbstractObjects teleport) {
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects object: game.getObjects())
			if (object.getPosition()==teleport.getPosition())
				return true;
		return false;
	}

	//A ser revisto
	public Point2D tpPosition() {
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects teleport: game.getObjects())
			if(teleport.hashCode()!=this.hashCode() && teleport.getName()=="Blue_Teleport")
				return teleport.getPosition();
		return getPosition();
	}

	//A ser revisto
	@Override
	public void interact() {
		System.out.println("Estou a interagir");
		SokobanGame game = SokobanGame.getInstance();
		for(AbstractObjects object: game.getObjects()) {
			if (object.getPosition().equals(getPosition()) && object.hashCode()!= hashCode() && canInteract()) {
				System.out.println("Posso interagir");
				System.out.println(object.getName() + " posição: " + object.getPosition());
				object.setPosition(tpPosition());
				System.out.println("Nova posição: " + object.getPosition());
				ImageMatrixGUI.getInstance().update();
			}
		}
		for(AbstractObjects object: game.getObjects()) {
			if(object.getPosition().equals(getPosition()) && object.hashCode()!= hashCode())
				if (object.getName()==getName()) {
					if (setStuck(object)) {
						setInteract(false);
						System.out.println("Vou mudar para verde");
						setImageName("Green_Teleport");
					}
					else {
						setInteract(true);
						System.out.println("Vou mudar para azul");
						setImageName("Blue_Teleport");
					}
				}
		}
	}
}