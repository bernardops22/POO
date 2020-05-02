package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Teleport extends AbstractObjects implements InteractiveObjects{

	private String imageName;
	
	public Teleport(Point2D position) {
		super(position, true);
		imageName = "Portal_Azul";
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	@Override
	public String getName() {
		return imageName;
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
