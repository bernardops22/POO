package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractObjects{
	
	private SokobanGame game;
	
	public Battery(Point2D position, SokobanGame game){
		super(position,true);
		this.game = game;
	}
	
	@Override
	public String getName() {
		return "Battery";
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

	public void overlap() {
		for (ImageTile object: game.getObjects())
			if (object.getName()==getName()) {
				game.setEnergy(101);
				ImageMatrixGUI.getInstance().removeImage(object);
				game.getObjects().remove(object);
			}		
	}
	
}
