package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Hammer extends AbstractObjects implements InteractiveObjects{

	public Hammer(Point2D position) {
		super(position, true,true);
	}
	
	@Override
	public String getName() {
		return "Hammer";
	}
	
	@Override   					
	public int getLayer() {
		return 1;
	}
	
	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		game.setHammer(true);
		ImageMatrixGUI.getInstance().removeImage(this);
		setInteract(false);
	}
}
