package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObjects implements InteractiveObjects{

	public Hole(Point2D position) {
		super(position,true);
	}

	@Override
	public String getName() {
		return "Hole";
	}

	@Override
	public void interact() {
		SokobanGame game = SokobanGame.getInstance();
		game.setEnergy(0);
		ImageMatrixGUI.getInstance().update();
		JOptionPane.showMessageDialog(null, "You have fallen into a hole.");
	}
	
}
