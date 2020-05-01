package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObjects implements InteractiveObjects{
	
	private boolean canInteract;
	
	public Hole(Point2D position, boolean canInteract) {
		super(position,true);
		this.canInteract = canInteract;
	}

	@Override
	public String getName() {
		return "Hole";
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
		game.setEnergy(0);
		ImageMatrixGUI.getInstance().update();
		JOptionPane.showMessageDialog(null, "You have fallen into a hole. Press R to restart.");
		setInteract(false);
	}

}
