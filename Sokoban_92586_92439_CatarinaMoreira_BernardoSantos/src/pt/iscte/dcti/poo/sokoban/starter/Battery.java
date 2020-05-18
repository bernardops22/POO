package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractObjects implements InteractiveObjects{
	
	public Battery(Point2D position, boolean canInteract){
		super(position,true,true);		
	}

	@Override
	public String getName() {
		return "Battery";
	}
	
	@Override   					
	public int getLayer() {
		return 1;
	}

	@Override
	public void interact(AbstractObjects object) {
		SokobanGame game = SokobanGame.getInstance();
		game.setEnergy(100);
		ImageMatrixGUI.getInstance().removeImage(this);
		setInteract(false);
		
	}
}