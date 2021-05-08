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
	 * @param grid : Grille dans laquelle �volue l'automate
	 */
	private Grid grid;
	
	/**
	* @param neighborType : Neighbourhood correspondant au type de voisinage d'une cellule de l'automate
	*/
	private Neighbourhood neighborType;
	
	/**
	* @param ruleType : Rule correspondant au r�gles de changement d'�tat d'une cellule de l'automate
	*/
	private Rule ruleType;
	
	/**
	* @param player : entier correspondant � l'identifiant du joueur � qui appartient l'automate
	*/
	private int player;
	
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur d'un automate
	 * @param neighborType : Neighbourhood correspondant au type de voisinage d'une cellule de l'automate
	 * @param grid : Grille dans laquelle �volue l'automate
	 * @param player : entier correspondant � l'identifiant du joueur � qui appartient l'automate
	 * @param ruleType : Rule correspondant au r�gles de changement d'�tat d'une cellule de l'automate
	 * @return le nouvel automate cr��
	 */
	public Automate(Neighbourhood neighborType, Grid grid, int player, Rule ruleType) {
		this.grid = grid;
		this.neighborType = neighborType;
		this.ruleType = ruleType;
		this.player = player;
	}
	
	/** 
	 * Fonction mettant � jour l'�tat des cellules de la grille pour cet automate
	 */
	public void nextTurn() {
		//On cr�er une nouvelle collection de cellules pour y stocker les �tats du tour suivant
		HashMap<Point,Cell> cells_next_turn = new HashMap<>();
		//On met � jour les cellules externes en fonction des �tats actuels des cellules
		grid.getExtendType().addExtension( grid.getCells(), grid.getSize());
		
		//On parcours les cellules de la collection actuelle
		for(int i =0; i < grid.getSize(); i++) {
			for (int j=0; j < grid.getSize(); j++) {
				
				//On r�cup�re la position de notre cellule
				Point cell_pos = new Point(i,j);
				
				//Ainsi que son �tat actuel pour le cet automate
				State cell_state = grid.getCells().get(cell_pos).getPlayersStates()[player];
				
				//On cherche les �tats de ses voisines
				ArrayList<State> neigh_states = this.neighborType.getNeighbor(grid, cell_pos, player);
				//On cherche ensuite l'�tat au tour suivant de notre cellule
				State cell_new_state = this.ruleType.next(cell_state, neigh_states);
				
				//On cr�er un nouveau tableau d'�tat pour cette cellule et on change uniquement celui li� � cet automate
				Cell cell_new = new Cell();
				State[] cell_new_tab_state = cell_new.getPlayersStates();
				System.arraycopy(grid.getCells().get(cell_pos).getPlayersStates(), 0, cell_new_tab_state, 0, 2);
				cell_new_tab_state[player] = cell_new_state;
				
				//On ajoute notre cellule � la collection pour le tour suivant
				cells_next_turn.put(cell_pos,cell_new);
			}
		}

		//Enfin on met � jour la collection de cellule de notre grille
		grid.setCells(cells_next_turn);
	}
	

	/** 
	 * Fonction g�rant les combats si plusieurs automates utilisent les m�mes cellules
	 */
	public void fight() {
		//On cr�er une nouvelle collection de cellules pour y stocker les �tats apr�s r�sulotion des combats
		HashMap<Point,Cell> cells_after_fight = new HashMap<>();
		//On met � jour les cellules externes en fonction des �tats actuels des cellules
		grid.getExtendType().addExtension(grid.getCells(), grid.getSize());
		
		//On parcours les cellules de la collection actuelle
		for(int i =0; i < grid.getSize(); i++) {
			for (int j=0; j < grid.getSize(); j++) {
				
				//On r�cup�re la position de notre cellule
				Point cell_pos = new Point(i,j);
				
				//Ainsi que son �tat actuel pour les diff�rents automates
				State[] cell_states = grid.getCells().get(cell_pos).getPlayersStates();
					
				//Puis on regarde s'il y a un combat
				if (cell_states[0] == State.VIVANT && cell_states[1] == State.VIVANT) {
					Random rand = new Random();
					int playerFail = rand.nextInt(2);
					//On cr�er un nouveau tableau d'�tat pour cette cellule
					Cell cell_new = new Cell();
					State[] cell_new_tab_state = cell_new.getPlayersStates();
					System.arraycopy(cell_states, 0, cell_new_tab_state, 0, 2);
					//Et le joueur perdant perds la cellule
					cell_new_tab_state[playerFail] = State.MORT;
					//On ajoute notre cellule � la collection d'apr�s les combats
					cells_after_fight.put(cell_pos,cell_new);
				}
				else {
					//On ajoute notre cellule � la collection d'apr�s les combats telle quelle
					Cell cell = grid.getCells().get(cell_pos);
					cells_after_fight.put(cell_pos,cell);
				}
			}
		}
		grid.setCells(cells_after_fight);
	}
	
	
}