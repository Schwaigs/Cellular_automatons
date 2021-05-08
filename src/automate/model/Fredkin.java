package automate.model;
import java.util.ArrayList;

/** 
 * Description de la classe Fredkin
 */
public class Fredkin implements Rule {
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur de Fredkin
	 */
	public Fredkin() {}
	
	/** 
	 * Fonction cherchant l'�tat d'une cellule au tour suivant selon les r�gles du jeu de Fredkin
	 * @param currentState : Etat de la cellule pour le tour de jeu courant
	 * @param states : Etats des diff�rents voisines de la cellules pour le tour de jeu courant
	 * @return l'�tat de la cellule au tour suivant
	 */
	public State next(State currentState, ArrayList<State> states) {
		//On compte le nombre de voisines ayant l'�tat vivant 
		int nbNeighbor = 0;
		for (State neighbor_state : states){
			if (neighbor_state == State.VIVANT) {
				nbNeighbor++;
			}
		}
		
		//Si la cellule poss�de entre 1 et 3 cellules voisines vivante alors elle sera vivante au tour suivant
		if (nbNeighbor >= 1 && nbNeighbor <= 3) {
			return State.VIVANT;
		}
		//Sinon elle sera morte
		else {
			return State.MORT;
		}
	}
	
}
