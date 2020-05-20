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
	 public void interact(AbstractObjects object, int lastKeyPressed) {
	        SokobanGame game = SokobanGame.getInstance();
	        for(AbstractObjects obj: game.getObjectsFromPosition(getPosition())) {
	            if(obj instanceof Player) {
	                game.setEnergy(100);
	                setInteract(false);
	                ImageMatrixGUI.getInstance().removeImage(this);
	            }
	        }
	    }
}