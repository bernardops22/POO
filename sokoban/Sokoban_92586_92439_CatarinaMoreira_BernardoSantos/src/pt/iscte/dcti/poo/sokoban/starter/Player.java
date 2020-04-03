package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends Objetos implements ObjetosAnimados{

	private String imageName;
	private SokobanGame jogo;

	public Player(Point2D initialPosition, SokobanGame jogo){
		super(initialPosition);
		this.jogo = jogo;
		imageName = "Empilhadora_U";
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
		
		if (Direction.isDirection(lastKeyPressed)) {
			switch(lastKeyPressed){
			case KeyEvent.VK_LEFT:
				imageName = "Empilhadora_L";
				break;
			case KeyEvent.VK_UP:
				imageName = "Empilhadora_U";
				break;
			case KeyEvent.VK_RIGHT:
				imageName ="Empilhadora_R";
				break;
			case KeyEvent.VK_DOWN:
				imageName ="Empilhadora_D";
				break;
			}

			for(Objetos objeto: jogo.getObjetos()) {
				if (newPosition.getX()>=0 && newPosition.getX()<10 && newPosition.getY()>=0 
						&& newPosition.getY()<10 ) {
					if(objeto.getPosition().equals(newPosition) && objeto.getLayer()<getLayer()) {
						setPosition(newPosition);
						jogo.setPassos(jogo.getPassos() + 1);
						jogo.setEnergia(jogo.getEnergia() - 1);
					}
					if(objeto.getPosition().equals(newPosition) && objeto.getName()=="Caixote")
						((Caixote)objeto).move(lastKeyPressed);
				}
			}
			System.out.println(getPosition() + " " + imageName + " " + lastKeyPressed);
		}			
	}
}