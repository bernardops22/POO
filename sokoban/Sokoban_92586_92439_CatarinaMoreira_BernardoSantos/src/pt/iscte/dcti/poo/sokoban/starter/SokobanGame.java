package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

	private Player player;
	private ArrayList<Objetos> objetos = new ArrayList<Objetos>();
	private int energia = 100;
	private int passos = 0;
	private int nLevel = 0;
	private String playerName = "";

	public SokobanGame(){
		playerName();

		ImageMatrixGUI.getInstance().setStatusMessage("   Empilhadora: " + playerName + 
				"   |    Nivel: " + nLevel + "    |    Energia: " + 
				energia + "    |    Passos: " + passos);

		buildSampleLevel();

		for(ImageTile imagem: objetos)
			ImageMatrixGUI.getInstance().addImage(imagem);
	}
	
	public ArrayList<Objetos> getObjetos() {
		return objetos;
	}

	public int getEnergia() {
		return energia;
	}

	public int getPassos() {
		return passos;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public void setPassos(int passos) {
		this.passos = passos;
	}

	public void playerName() {
		String response = JOptionPane.showInputDialog(null,"Qual o nome da tua empilhadora?",
				"Sokoban by Hiroyuki Imabayashi (1981)",JOptionPane.QUESTION_MESSAGE);
		if(response == null) throw new NullPointerException();
		if (response.length()==0) {
			JOptionPane.showMessageDialog(null,"Tens de ter um nome de gamer");
			//			SokobanGame sokoban = new SokobanGame();
		}

		playerName = response;
	}

	public void buildSampleLevel() {
		try {

			String filename = "levels/level" + nLevel + ".txt";
			File file = new File(filename);
			Scanner s =  new Scanner(file);

			String linha;
			String[] simbolos;
			int y = 0;

			while(s.hasNextLine()) {

				linha = s.nextLine();
				simbolos = linha.split("");

				for(int x = 0; x!=10 ; x++) {
					switch(simbolos[x]) {
					case "#" :
						objetos.add(new Parede(new Point2D(x,y)));
						break;
					case "C":
						objetos.add(new Caixote(new Point2D(x,y),this));
						objetos.add(new Chao(new Point2D(x,y)));
						break;
					case "O":
						objetos.add(new Buraco(new Point2D(x,y)));
						objetos.add(new Chao(new Point2D(x,y)));
						break;
					case "X":
						objetos.add(new Alvo(new Point2D(x,y)));
						break;
					case "E":
						player = new Player(new Point2D(x,y),this);
						objetos.add(new Chao(new Point2D(x,y)));
						objetos.add(player);
						break;
					case "b":
						objetos.add(new Bateria(new Point2D(x,y)));
						objetos.add(new Chao(new Point2D(x,y)));
						break;
					case "S":
						objetos.add(new BigStone(new Point2D(x,y)));
						objetos.add(new Chao(new Point2D(x,y)));
						break;
					case "s":
						objetos.add(new SmallStone(new Point2D(x,y)));
						objetos.add(new Chao(new Point2D(x,y)));
						break;
					case " ": 
						objetos.add(new Chao(new Point2D(x,y)));
						break;
					}
				}
				y++;
			}
			s.close();

		}catch (FileNotFoundException e){
		}
	}

	//Quando ficarmos sem energia na empilhadora
	public void semEnergia() {
		if (energia == 0) {
			passos = 0;
			energia = 100;

			Object[] opcoes = {"Repetir n�vel","Sair"};
			int n = JOptionPane.showOptionDialog(null,"O que queres fazer?","GAME OVER",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opcoes,opcoes[0]);

			if(n == 0) {
				for(ImageTile imagem: objetos)
					ImageMatrixGUI.getInstance().removeImage(imagem);

				objetos.removeAll(objetos);
				buildSampleLevel();
			}
			else ImageMatrixGUI.getInstance().dispose();


		}
	}

	//	public void restart(Observed arg0) {
	//		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
	//		if(lastKeyPressed == KeyEvent.VK_R) {
	//			for(ImageTile imagem: objetos)
	//				ImageMatrixGUI.getInstance().removeImage(imagem);
	//			
	//			objetos.removeAll(objetos);
	//			buildSampleLevel();
	//		}
	//			
	//	}

	//Quando o n�vel � completado
	public void nivelCompleto() {

		ArrayList<ImageTile> caixotes = new ArrayList<ImageTile>();
		ArrayList<ImageTile> alvos = new ArrayList<ImageTile>();

		int n = 0;
		for(Objetos objeto: objetos) {
			if (objeto.getName()=="Caixote")
				caixotes.add(objeto);
			if (objeto.getName()=="Alvo")
				alvos.add(objeto);
		}
		for(ImageTile caixote: caixotes)
			for(ImageTile alvo: alvos)
				if (caixote.getPosition()==alvo.getPosition())
					n++;

		if(caixotes.size() == n) {
			objetos.removeAll(objetos);
			nLevel++;

			try {
				FileWriter file = new FileWriter("pontos/pontuacao.txt");
				file.write(playerName + " " + passos);
				file.close();
				JOptionPane.showMessageDialog(null, " Nome: "+ playerName + "    Passos: "  + passos);

			}catch(IOException e) {}

			buildSampleLevel();
		}
	}

	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		if (player != null) {
			semEnergia();	
			//			restart(arg0);
			player.move(lastKeyPressed);
			ImageMatrixGUI.getInstance().update();
			ImageMatrixGUI.getInstance().setStatusMessage("   Empilhadora: " + playerName +
					"   |    Nivel: " + nLevel + "    |    Energia: " + energia + "    |    "
					+ "Passos: " + passos);
		}
	}
}