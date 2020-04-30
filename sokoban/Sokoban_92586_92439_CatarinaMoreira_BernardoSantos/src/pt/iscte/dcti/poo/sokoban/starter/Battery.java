package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractObjects implements InteractiveObjects{
	
	public Battery(Point2D position){
		super(position,true);
	}
	
	@Override
	public String getName() {
		return "Battery";
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void interact() {
			SokobanGame game = SokobanGame.getInstance();
		
		for (AbstractObjects object: game.getObjects())
			if (object.getName()==getName()) {
				game.setEnergy(101);
				ImageMatrixGUI.getInstance().removeImage(object);
				object.setTransposable(false);
			}	
		
	}
	
	
	
}
