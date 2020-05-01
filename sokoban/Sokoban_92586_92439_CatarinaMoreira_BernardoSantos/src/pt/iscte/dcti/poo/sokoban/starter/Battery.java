package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractObjects implements InteractiveObjects{

	private boolean canInteract;
	
	public Battery(Point2D position, boolean canInteract){
		super(position,true);
		this.canInteract = canInteract;
		
	}

	@Override
	public String getName() {
		return "Battery";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	public boolean canInteract() {
		return canInteract;
	}

	public void setInteract(boolean canInteract) {
		this.canInteract = canInteract;
	}

	@Override
	public void interact() {
		SokobanGame game = SokobanGame.getInstance();
		game.setEnergy(101);
		ImageMatrixGUI.getInstance().removeImage(this);
		setInteract(false);
	}
}