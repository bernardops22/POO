package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends AbstractObjects implements ActiveObjects{

	private String imageName;

	public Player(Point2D initialPosition){
		//NEW LAYER PART
		super(initialPosition,false,false);
		imageName = "Player_U";
	}

	@Override
	public String getName() {
		return imageName;
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void move(int lastKeyPressed) {

		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		SokobanGame game = SokobanGame.getInstance();

		switch(lastKeyPressed){
		case KeyEvent.VK_LEFT:
			imageName = "Player_L";
			break;
		case KeyEvent.VK_UP:
			imageName = "Player_U";
			break;
		case KeyEvent.VK_RIGHT:
			imageName ="Player_R";
			break;
		case KeyEvent.VK_DOWN:
			imageName ="Player_D";
			break;
		}

		for(AbstractObjects object: game.getObjects()) {
			if (newPosition.getX()>=0 && newPosition.getX()<SokobanGame.WIDTH && newPosition.getY()>=0 
					&& newPosition.getY()<SokobanGame.HEIGHT && object.getPosition().equals(newPosition)
					&& object.hashCode()!=hashCode()) {
				
				if(object.canMove(lastKeyPressed))
					if (object instanceof ActiveObjects)
						((ActiveObjects)object).move(lastKeyPressed);

				if (canMove(lastKeyPressed)){

					setPosition(newPosition);

					game.setSteps(game.getSteps() + 1);
					game.setEnergy(game.getEnergy() - 1);

					System.out.println(getPosition() + " " + imageName + " " + lastKeyPressed);
					System.out.println();
				}
				
				if (object instanceof InteractiveObjects && object.canInteract())
					((InteractiveObjects)object).interact();

				
			}
		}
	}	
}
