package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {

	public static void main(String[] args){
		
		ImageMatrixGUI.setSize(10, 10);
		SokobanGame s = new SokobanGame();
		ImageMatrixGUI.getInstance().registerObserver(s);
		ImageMatrixGUI.getInstance().go();

	}

}