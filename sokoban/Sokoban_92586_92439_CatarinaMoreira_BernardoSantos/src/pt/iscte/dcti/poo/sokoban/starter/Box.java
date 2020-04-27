package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements ActiveObjects{

	private boolean activation = false;
	
	public Box(Point2D position, boolean activation) {
		super(position,false);
		this.activation = activation;
	}

	@Override
	public String getName() {
		return "Box";
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	public boolean isActivation() {
		return activation;
	}
	
	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	@Override
	public void move(int lastKeyPressed) {
		
		SokobanGame game = SokobanGame.getInstance();
		//NAO DESATIVA QUANDO SAI DO SITIO
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		for(AbstractObjects object : game.getObjects()) {
			if(object.isTransposable() && object.getPosition().equals(newPosition)) {
					setPosition (newPosition);
					setActivation(false);
					if (object.getName() == "Target")
						setActivation(true);
				}
		}
	}
}