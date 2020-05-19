package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class BrittleWall extends AbstractObjects implements InteractiveObjects{

	public BrittleWall(Point2D position) {
		super(position, false,true);
	}

	@Override
	public String getName() {
		return "Brittle_Wall";
	}

	@Override
	public void interact(AbstractObjects object, int lastKeyPressed) {
		SokobanGame game = SokobanGame.getInstance();
		if (game.hasHammer()) {
			ImageMatrixGUI.getInstance().removeImage(this);
			setTransposable(true);
		}
	}
}
