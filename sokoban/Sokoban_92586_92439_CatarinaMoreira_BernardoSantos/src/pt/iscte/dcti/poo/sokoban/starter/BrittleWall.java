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
	public int getLayer() {
		return 2;
	}

	@Override
	public void interact() {
		SokobanGame game = SokobanGame.getInstance();
		setTransposable(false);
		if (game.hasHammer()) {
			ImageMatrixGUI.getInstance().removeImage(this);
			setInteract(false);
			setTransposable(true);
		}
	}

}
