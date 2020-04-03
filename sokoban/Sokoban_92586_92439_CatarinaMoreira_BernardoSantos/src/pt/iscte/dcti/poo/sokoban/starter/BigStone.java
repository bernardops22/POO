package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends Objetos implements ObjetosAnimados{
	
	public BigStone(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "BigStone";
	}
	
	@Override   					
	public int getLayer() {
		return 2;
	}

	@Override
	public void move(int lastKeyPressed) {
		// TODO Auto-generated method stub
		
	}
}