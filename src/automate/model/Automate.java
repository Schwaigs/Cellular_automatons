package automate.model;
import java.awt.Point;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

/** 
 * Description de la classe Automate
 */
public class Automate {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param grid : Grille dans laquelle évolue l'automate
	 */
	private Grid grid;
	
	/**
	* @param neighborType : Neighbourhood correspondant au type de voisinage d'une cellule de l'automate
	*/
	private Neighbourhood neighborType;
	
	/**
	* @param ruleType : Rule correspondant au règles de changement d'état d'une cellule de l'automate
	*/
	private Rule ruleType;
	
	/**
	* @param player : entier correspondant à l'identifiant du joueur à qui appartient l'automate
	*/
	private int player;
	
	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur d'un automate
	 * @param neighborType : Neighbourhood correspondant au type de voisinage d'une cellule de l'automate
	 * @param grid : Grille dans laquelle évolue l'automate
	 * @param player : entier correspondant à l'identifiant du joueur à qui appartient l'automate
	 * @param ruleType : Rule correspondant au règles de changement d'état d'une cellule de l'automate
	 * @return le nouvel automate créé
	 */
	public Automate(Neighbourhood neighborType, Grid grid, int player, Rule ruleType) {
		this.grid = grid;
		this.neighborType = neighborType;
		this.ruleType = ruleType;
		this.player = player;
	}
	
	/** 
	 * Fonction mettant à jour l'état des cellules de la grille pour cet automate
	 */
	public void nextTurn() {
		//On créer une nouvelle collection de cellules pour y stocker les états du tour suivant
		HashMap<Point,Cell> cells_next_turn = new HashMap<>();
		//On met à jour les cellules externes en fonction des états actuels des cellules
		grid.getExtendType().addExtension( grid.getCells(), grid.getSize());
		
		//On parcours les cellules de la collection actuelle
		for(int i =0; i < grid.getSize(); i++) {
			for (int j=0; j < grid.getSize(); j++) {
				
				//On récupère la position de notre cellule
				Point cell_pos = new Point(i,j);
				
				//Ainsi que son état actuel pour le cet automate
				State cell_state = grid.getCells().get(cell_pos).getPlayersStates()[player];
				
				//On cherche les états de ses voisines
				ArrayList<State> neigh_states = this.neighborType.getNeighbor(grid, cell_pos, player);
				//On cherche ensuite l'état au tour suivant de notre cellule
				State cell_new_state = this.ruleType.next(cell_state, neigh_states);
				
				//On créer un nouveau tableau d'état pour cette cellule et on change uniquement celui lié à cet automate
				Cell cell_new = new Cell();
				State[] cell_new_tab_state = cell_new.getPlayersStates();
				System.arraycopy(grid.getCells().get(cell_pos).getPlayersStates(), 0, cell_new_tab_state, 0, 2);
				cell_new_tab_state[player] = cell_new_state;
				
				//On ajoute notre cellule à la collection pour le tour suivant
				cells_next_turn.put(cell_pos,cell_new);
			}
		}

		//Enfin on met à jour la collection de cellule de notre grille
		grid.setCells(cells_next_turn);
	}
	

	/** 
	 * Fonction gérant les combats si plusieurs automates utilisent les mêmes cellules
	 */
	public void fight() {
		//On créer une nouvelle collection de cellules pour y stocker les états après résulotion des combats
		HashMap<Point,Cell> cells_after_fight = new HashMap<>();
		//On met à jour les cellules externes en fonction des états actuels des cellules
		grid.getExtendType().addExtension(grid.getCells(), grid.getSize());
		
		//On parcours les cellules de la collection actuelle
		for(int i =0; i < grid.getSize(); i++) {
			for (int j=0; j < grid.getSize(); j++) {
				
				//On récupère la position de notre cellule
				Point cell_pos = new Point(i,j);
				
				//Ainsi que son état actuel pour les différents automates
				State[] cell_states = grid.getCells().get(cell_pos).getPlayersStates();
					
				//Puis on regarde s'il y a un combat
				if (cell_states[0] == State.VIVANT && cell_states[1] == State.VIVANT) {
					Random rand = new Random();
					int playerFail = rand.nextInt(2);
					//On créer un nouveau tableau d'état pour cette cellule
					Cell cell_new = new Cell();
					State[] cell_new_tab_state = cell_new.getPlayersStates();
					System.arraycopy(cell_states, 0, cell_new_tab_state, 0, 2);
					//Et le joueur perdant perds la cellule
					cell_new_tab_state[playerFail] = State.MORT;
					//On ajoute notre cellule à la collection d'après les combats
					cells_after_fight.put(cell_pos,cell_new);
				}
				else {
					//On ajoute notre cellule à la collection d'après les combats telle quelle
					Cell cell = grid.getCells().get(cell_pos);
					cells_after_fight.put(cell_pos,cell);
				}
			}
		}
		grid.setCells(cells_after_fight);
	}
	
	
}