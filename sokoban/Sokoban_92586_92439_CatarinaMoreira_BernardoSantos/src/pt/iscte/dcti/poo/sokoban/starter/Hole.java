package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObjects {
	
	private SokobanGame game;
	
	public Hole(Point2D position, SokobanGame game) {
		super(position,true);
		this.game = game;
	}

	@Override
	public String getName() {
		return "Hole";
	}

	public void disappear() {
		game.setEnergy(0);
		ImageMatrixGUI.getInstance().update();
		JOptionPane.showMessageDialog(null, "You have fallen into a hole. Press R to restart.");
	}
	
}
