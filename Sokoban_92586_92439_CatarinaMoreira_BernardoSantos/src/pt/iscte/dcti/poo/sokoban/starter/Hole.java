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
	public int getLayer() {
		return 1;
	}

	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		if (canInteract()) {
			SokobanGame game = SokobanGame.getInstance();
			if(object instanceof BigStone) {
				object.setInteract(false);
				setInteract(false);
				return;
			}
			if(object instanceof Player) {
				game.setEnergy(0);
				ImageMatrixGUI.getInstance().update();
				ImageMatrixGUI.getInstance().removeImage(object);
				JOptionPane.showMessageDialog(null, "You have fallen into a hole. Press R to restart.");
				return;
			}
			else {
				object.setTransposable(true);
				object.setInteract(false);
				ImageMatrixGUI.getInstance().removeImage(object);
				ImageMatrixGUI.getInstance().update();
			}
		}
	}
}
