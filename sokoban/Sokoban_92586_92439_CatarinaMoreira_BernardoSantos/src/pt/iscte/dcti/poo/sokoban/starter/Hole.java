package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObjects implements InteractiveObjects{

	public Hole(Point2D position, boolean canInteract) {
		super(position,true,true);
	}

	@Override
	public String getName() {
		return "Hole";
	}

	@Override
	public void interact() {
		SokobanGame game = SokobanGame.getInstance();

		for(AbstractObjects object : game.getObjects()) 
			if(this.getPosition().equals(object.getPosition()) && object.getName() == "BigStone") {
				setInteract(false);
				return;
			}

		game.setEnergy(0);
		ImageMatrixGUI.getInstance().update();
		JOptionPane.showMessageDialog(null, "You have fallen into a hole. Press R to restart.");
		setInteract(false);
	}
}
