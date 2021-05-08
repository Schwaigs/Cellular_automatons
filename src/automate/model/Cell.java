package automate.model;

/** 
 * Description de la classe Cell
 */
public class Cell {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param State[] : Tableau d'�tat de la cellule, chaque position dans le tableau correspond � un joueur/automate
	 */
	private State[] playersStates;
	
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur vide d'une cellule
	 */
	public Cell() {
		State[] playersS = {State.MORT, State.MORT };
		playersStates = playersS;
	}
	
	/** 
	 * Constructeur d'une cellule
	 * @param player1 : Etat de la cellule pour le joueur 1
	 * @param player2 : Etat de la cellule pour le joueur 2
	 */
	public Cell(State player1, State player2) {
		this.playersStates[0] = player1;
		this.playersStates[1] = player2;
	}
	
	
	//------------------------------ Accesseurs -------------------------------------	
	/** 
	 * Fonction permettant d'acc�der aux �tats de la cellule
	 * @return le tableau d'�tat
	 */
	public State[] getPlayersStates() {
		return playersStates;
	}
	
	/** 
	 * Fonction permettant de modifier le tableau d'�tats de la cellule
	 * @param new_states : le nouveau tableau d'�tats
	 */
	public void setPlayersStates(State[] new_states ) {
		this.playersStates = new_states;
	}

}