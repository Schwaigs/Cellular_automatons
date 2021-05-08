package automate.controller;

import java.awt.Point;
import java.util.ArrayList;

import automate.model.Automate;
import automate.model.Grid;
import automate.model.State;

public class ControlFacade {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param grid : Grille dans laquelle évoluent les automates
	 */
	private Grid grid;
	
	/** 
	 * @param players : Collection des différents automates crées
	 */
	private ArrayList<Automate> players = new ArrayList<Automate>();
	
	/** 
	 * @param gridSize : Taille de la grille
	 */
	private int gridSize;
	
	/** 
	 * @param nbCells : nombre de cellules placées par les joueurs sur la grille
	 */
	private int nbCells = 0;
	
	/** 
	 * @param maxTurn : nombre d'itération maximum
	 */
	private int maxTurn;
	
	/** 
	 * @param nbTurn : nombre d'itération effectuées
	 */
	private int nbTurn = 0;
	
	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur du controlFacade
	 * @param size : entier correspondant à la taille de la grille
	 * @param maxIteration : entier correspondant au nombre d'itération maximum
	 */
	public ControlFacade(int size, int maxIteration) {
		gridSize = size;
		maxTurn = maxIteration;
	}
	
	/** 
	 * Fonction créant un nouvel automate en fonction des implémentations choisit par le joueur et l'ajoute à la collection d'automates
	 * @param neighMethod : NeighborMethod correspondant au type de voisinage d'une cellule
	 * @param rule : RulesMethod correspondant au règles de changement d'état d'une cellule
	 * @param grid : Grille dans laquelle évolue l'automate
	 */
	public void createAutomate(NeighborMethod neighMethod, RulesMethod rule, Grid grid) {
		FabricAutomate automatesFabr = FabricAutomate.getInstance();
	    Automate automate = automatesFabr.createAutomate(neighMethod, rule, grid);
	    players.add(automate);
	}
	
	/** 
	 * Fonction créant une grille en fonction des choix des joueurs
	 * @param typeOfExtend : GridExtend correspondant à la méthode d'extension de la grille
	 */
	public void createGrid(GridExtend typeOfExtend) {
		FabricGrid gridFabr = FabricGrid.getInstance();
		grid = gridFabr.createGrid(typeOfExtend, gridSize);
	}
	
	/** 
	 * Fonction mettant à jour les automates
	 * @return -1 si le jeu est encore en cours
	 * 			Ou le joueur gagnant si l'un des deux n'as plus d'automate ou celui ayant le plus grand si le nombre maximal d'itérations est atteint
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
	 * 			Ou le joueur gagnant si l'un des deux n'as plus d'automate ou celui ayant le plus grand si le nombre maximal d'itérations est atteint
	 */
	public int endGame() {
		int nbCell_player1 = 0;
		int nbCell_player2 = 0;
		//On parcours les cellules de la collection actuelle
		for(int i =0; i < gridSize; i++) {
			for (int j=0; j < gridSize; j++) {
				//On récupère la position de notre cellule
				Point cell_pos = new Point(i,j);
				//Ainsi que son état actuel pour les différents automates
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
		//Les des deux automates n'as plus de cellules ou nombre maximal d'itération atteint
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
	 * Fonction permettant d'accéder à la grille
	 * @return la grille
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/** 
	 * Fonction permettant d'accéder à la taille de la grille
	 * @return la taille de la grille
	 */
	public int getGridSize() {
		return gridSize;
	}
	
	/** 
	 * Fonction permettant d'accéder au nombre de cellules posées par les joueurs
	 * @return nombre de cellules posées par les joueurs
	 */
	public int getNbCells() {
		return nbCells;
	}	
	
	/** 
	 * Fonction permettant de modifier le nombre de cellules posées par les joueurs
	 * @param nouveau nombre de cellules posées par les joueurs
	 */
	public void setNbCells(int new_nbCells) {
		nbCells = new_nbCells;
	}
	
	/** 
	 * Fonction permettant d'accéder au nombre maximal d'itérations
	 * @return le nombre maximal d'itération
	 */
	public int getMaxTurn() {
		return maxTurn;
	}
	
	/** 
	 * Fonction permettant d'accéder au nombre d'itérations effectuées
	 * @return le nombre d'itération effectuées
	 */
	public int getNbTurn() {
		return nbTurn;
	}
	
}
