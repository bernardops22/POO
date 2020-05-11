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
	public void interact(AbstractObjects object) {
		SokobanGame game = SokobanGame.getInstance();
		System.out.println(object.getName() + " entrou no buraco");
		if(object.getName() == "BigStone") {
			object.setInteract(false);
			setInteract(false);
			return;
		}
		if(object.getName().contains("Player")) {
			game.setEnergy(0);
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
