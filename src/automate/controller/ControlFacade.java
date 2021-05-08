package automate.controller;

import java.awt.Point;
import java.util.ArrayList;

import automate.model.Automate;
import automate.model.Grid;
import automate.model.State;

public class ControlFacade {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param grid : Grille dans laquelle �voluent les automates
	 */
	private Grid grid;
	
	/** 
	 * @param players : Collection des diff�rents automates cr�es
	 */
	private ArrayList<Automate> players = new ArrayList<Automate>();
	
	/** 
	 * @param gridSize : Taille de la grille
	 */
	private int gridSize;
	
	/** 
	 * @param nbCells : nombre de cellules plac�es par les joueurs sur la grille
	 */
	private int nbCells = 0;
	
	/** 
	 * @param maxTurn : nombre d'it�ration maximum
	 */
	private int maxTurn;
	
	/** 
	 * @param nbTurn : nombre d'it�ration effectu�es
	 */
	private int nbTurn = 0;
	
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur du controlFacade
	 * @param size : entier correspondant � la taille de la grille
	 * @param maxIteration : entier correspondant au nombre d'it�ration maximum
	 */
	public ControlFacade(int size, int maxIteration) {
		gridSize = size;
		maxTurn = maxIteration;
	}
	
	/** 
	 * Fonction cr�ant un nouvel automate en fonction des impl�mentations choisit par le joueur et l'ajoute � la collection d'automates
	 * @param neighMethod : NeighborMethod correspondant au type de voisinage d'une cellule
	 * @param rule : RulesMethod correspondant au r�gles de changement d'�tat d'une cellule
	 * @param grid : Grille dans laquelle �volue l'automate
	 */
	public void createAutomate(NeighborMethod neighMethod, RulesMethod rule, Grid grid) {
		FabricAutomate automatesFabr = FabricAutomate.getInstance();
	    Automate automate = automatesFabr.createAutomate(neighMethod, rule, grid);
	    players.add(automate);
	}
	
	/** 
	 * Fonction cr�ant une grille en fonction des choix des joueurs
	 * @param typeOfExtend : GridExtend correspondant � la m�thode d'extension de la grille
	 */
	public void createGrid(GridExtend typeOfExtend) {
		FabricGrid gridFabr = FabricGrid.getInstance();
		grid = gridFabr.createGrid(typeOfExtend, gridSize);
	}
	
	/** 
	 * Fonction mettant � jour les automates
	 * @return -1 si le jeu est encore en cours
	 * 			Ou le joueur gagnant si l'un des deux n'as plus d'automate ou celui ayant le plus grand si le nombre maximal d'it�rations est atteint
	 */
	public int nextTurn() {
		int nbPlayers = players.size();
		int i;
		for (i = 0; i < nbPlayers-1; i++) {
			players.get(i).nextTurn();
		}
		//Pour le dernier automate on gere en plus les combats
		players.get(i).nextTurn();
		players.get(i).fight();
		nbTurn++;
		return endGame();
	}
	
	/** 
	 * Fonction cherchant si le jeu est fini
	 * @return -1 si le jeu est encore en cours
	 * 			Ou le joueur gagnant si l'un des deux n'as plus d'automate ou celui ayant le plus grand si le nombre maximal d'it�rations est atteint
	 */
	public int endGame() {
		int nbCell_player1 = 0;
		int nbCell_player2 = 0;
		//On parcours les cellules de la collection actuelle
		for(int i =0; i < gridSize; i++) {
			for (int j=0; j < gridSize; j++) {
				//On r�cup�re la position de notre cellule
				Point cell_pos = new Point(i,j);
				//Ainsi que son �tat actuel pour les diff�rents automates
				State[] cell_states = grid.getCells().get(cell_pos).getPlayersStates();
				if(cell_states[0] == State.VIVANT) {
					nbCell_player1++;
				}
				else if(cell_states[1] == State.VIVANT) {
					nbCell_player2++;
				}
			}
		}
		int returnValue = -1;
		//Les des deux automates n'as plus de cellules ou nombre maximal d'it�ration atteint
		if(nbCell_player1==0) {
			if( nbCell_player2 != 0) {
				returnValue = 1;
			}
			else {
				returnValue = 0;
			}
		}
		else if( nbCell_player2 ==0) {
			if( nbCell_player1 != 0) {
				returnValue = 2;
			}
			else {
				returnValue = 0;
			}
		}
		else if(nbTurn == maxTurn) {
			if(nbCell_player1 > nbCell_player2) {
				returnValue = 1;
			}
			else if(nbCell_player2 > nbCell_player1){
				returnValue = 2;
			}
			else {
				returnValue = 0;
			}
		}
		return returnValue;
	}
	
	//------------------------------ Accesseurs -------------------------------------	
	/** 
	 * Fonction permettant d'acc�der � la grille
	 * @return la grille
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/** 
	 * Fonction permettant d'acc�der � la taille de la grille
	 * @return la taille de la grille
	 */
	public int getGridSize() {
		return gridSize;
	}
	
	/** 
	 * Fonction permettant d'acc�der au nombre de cellules pos�es par les joueurs
	 * @return nombre de cellules pos�es par les joueurs
	 */
	public int getNbCells() {
		return nbCells;
	}	
	
	/** 
	 * Fonction permettant de modifier le nombre de cellules pos�es par les joueurs
	 * @param nouveau nombre de cellules pos�es par les joueurs
	 */
	public void setNbCells(int new_nbCells) {
		nbCells = new_nbCells;
	}
	
	/** 
	 * Fonction permettant d'acc�der au nombre maximal d'it�rations
	 * @return le nombre maximal d'it�ration
	 */
	public int getMaxTurn() {
		return maxTurn;
	}
	
	/** 
	 * Fonction permettant d'acc�der au nombre d'it�rations effectu�es
	 * @return le nombre d'it�ration effectu�es
	 */
	public int getNbTurn() {
		return nbTurn;
	}
	
}
