package automate.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import automate.controller.ControlFacade;
import automate.controller.GridExtend;
import automate.controller.NeighborMethod;
import automate.controller.RulesMethod;
import automate.model.State;

/** 
 * Description de la classe Game
 */
public class Game extends JFrame{
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param serialVersionUID : Nombre servant à la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * @param playerID : Entier correspondant au joueur qui doit jouer
	 */
	private int playerID;
	
	/** 
	 * @param buttonGrid : HashMap<Point,JButton> collection de boutons pour la grille à affichée
	 */
	private HashMap<Point,JButton> buttonGrid;
	
	/** 
	 * @param facade : ControlFacade avec lequel interragit notre vue pour réaliser les différentes fonctionnalités
	 */
	private ControlFacade facade;
	
	/** 
	 * @param textAboveGrid : JLabel au dessus de la grille qui donne des infos au joueurs
	 */
	private JLabel textAboveGrid = new JLabel();
	
	/** 
	 * @param textAboveGrid : Timer de mise à jour de la grille
	 */
	private Timer timerUpdate; 

	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur de Game
	 * @param gridSize : entier correspondant à la taille de la grille
	 * @param numberCells : entier correspondant au nombre de cellules composant un automate
	 * @param maxIteration : entier correspondant au nombre d'itération maximum
	 * @param extendMet : GridExtend correspondant à la méthode d'extension de la grille
	 * @param neighMetJ1 : NeighborMethod correspondant au type de voisinage d'une cellule du joueur 1
	 * @param rulesMet1 : RulesMethod correspondant au règles de changement d'état d'une cellule du joueur 1
	 * @param neighMetJ2 : NeighborMethod correspondant au type de voisinage d'une cellule du joueur 2
	 * @param rulesMet2 : RulesMethod correspondant au règles de changement d'état d'une cellule du joueur 2
	 */
	public Game(int gridSize, int numberCells, int maxIteration, GridExtend extendMet,
			    NeighborMethod neighMetJ1, RulesMethod rulesMetJ1, NeighborMethod neighMetJ2, RulesMethod rulesMetJ2) {
		
		this.setTitle("Game");
	    this.setSize(1000, 600);
	    this.setLocationRelativeTo(null);  
	    
	    //On tire au hasard le joueur qui commence a placer ses cellules
	    Random rand = new Random();
		playerID = rand.nextInt(2);
	    textAboveGrid.setText("Le joueur "+(playerID+1)+" commence ! C'est à lui de placer la première cellule");
	    
	    facade = new ControlFacade(gridSize,maxIteration);
	    facade.createGrid(extendMet);
	    facade.createAutomate(neighMetJ1, rulesMetJ1,facade.getGrid());
	    facade.createAutomate(neighMetJ2, rulesMetJ2,facade.getGrid());
	    
	    JPanel gridPanel = new JPanel();
	    GridLayout layout = new GridLayout(gridSize,gridSize);
        layout.setHgap(2);
        layout.setVgap(2);
        gridPanel.setLayout(layout);
        
        buttonGrid = new HashMap<Point,JButton>();
        JButton bNext = new JButton("Start");
	    bNext.setEnabled(false);
	    bNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerUpdate = new Timer();
				timerUpdate.schedule(new TimerTask() { //Mise en place du timer
				    @Override
				    public void run() {
				    	int numberGameEnd = facade.nextTurn();
						stopButton();
						updateGrid();
						changeLabel(); //Mettre à jour le nombre d'itération restante
						if (numberGameEnd != (-1)) { //Si la partie est finie
							new EndGame(numberGameEnd).setVisible(true);
							exit();
						}
				    }
				}, 0, 750); //Mise à jour toute les 0.75 secondes
				bNext.setEnabled(false);
			}
	    	
	    });
	    
        //Initialistion de la grille avec gridSize x gridSize cases
        for (int i = 0; i < gridSize;i++) {
		    for (int j = 0; j < gridSize;j++) {
		    	
			    JButton b = new JButton("");
			    buttonGrid.put(new Point(i,j),b);
			    b.setName(i + ","+j);
	    	    b.setBackground(Color.WHITE);
	    	    final int line = i;
	    	    final int column = j;
	    	    facade.getGrid().getCells().get(new Point(i,j)).getPlayersStates()[0] = State.MORT; // ENLEVER ??????????
	    	    b.addActionListener(new ActionListener() {
	    		   
	    		  	//Changer la couleur et l'état d'initilisation
					@Override
					public void actionPerformed(ActionEvent e) {
						if (b.getBackground() == Color.WHITE && facade.getNbCells() < numberCells*2) {
							facade.getGrid().getCells().get(new Point(line,column)).getPlayersStates()[playerID] = State.VIVANT;
							if(playerID == 0) {
								b.setBackground(Color.BLUE);
								playerID++;
							}
							else {
								b.setBackground(Color.RED);
								playerID=0;
							}
							b.setEnabled(false);
							if (facade.getNbCells()+1 == numberCells*2) {
								bNext.setEnabled(true);
								changeLabel();
							}
							facade.setNbCells(facade.getNbCells()+1);
						}
					}
	    	    });
	    	    gridPanel.add(b);
	        }
        }
        gridPanel.setSize(200, 200);
        gridPanel.setVisible(true);
	    this.getContentPane().add(gridPanel, BorderLayout.CENTER);
	    this.getContentPane().add(bNext, BorderLayout.SOUTH);
	    this.getContentPane().add(textAboveGrid, BorderLayout.NORTH);

	    //this.setContentPane(pan);   
	}
	
	/** 
	 * Fonction permettant de fermer l'application et de stopper le timer de mise à jour de la grille
	 */
	protected void exit() {
		timerUpdate.cancel();
		this.dispose();
	}

	/** 
	 * Fonction mettant à jour les boutons pour qu'on ne puisse plus cliquer dessus
	 */
	private void stopButton() {
		for (int i = 0; i < facade.getGridSize();i++) {
		    for (int j = 0; j < facade.getGridSize();j++) {
		    	buttonGrid.get(new Point(i,j)).setEnabled(false);
		    }
		}
	}
	
	/** 
	 * Fonction mettant à jour les cellules sur la grille en fonction des nouveaux états
	 */
	private void updateGrid() {
		for (int i = 0; i < facade.getGridSize();i++) {
		    for (int j = 0; j < facade.getGridSize();j++) {
		    	if (facade.getGrid().getCells().get(new Point(i,j)).getPlayersStates()[0] == State.VIVANT) {
		    		buttonGrid.get(new Point(i,j)).setBackground(Color.BLUE);
		    	}
		    	else if (facade.getGrid().getCells().get(new Point(i,j)).getPlayersStates()[1] == State.VIVANT) {
		    		buttonGrid.get(new Point(i,j)).setBackground(Color.RED);
		    	}
		    	else {
		    		buttonGrid.get(new Point(i,j)).setBackground(Color.WHITE);
		    	}
		    }
		}
	}
	
	/** 
	 * Fonction changeant le texte en haut de la grille
	 */
	private void changeLabel() {
		textAboveGrid.setText("Nombre de tours restants : "+(facade.getMaxTurn() - facade.getNbTurn()));
	}
}