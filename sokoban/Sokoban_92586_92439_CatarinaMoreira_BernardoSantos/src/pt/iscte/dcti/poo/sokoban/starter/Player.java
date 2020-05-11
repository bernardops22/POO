package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Player extends AbstractObjects implements ActiveObjects{

	private String imageName;

	public Player(Point2D initialPosition){
		super(initialPosition,false,false);
		imageName = "Player_U";
	}

	@Override
	public String getName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void move(Point2D newPosition) {
		setPosition (newPosition);
	}
}
