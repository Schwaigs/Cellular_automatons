package automate.model;

import java.util.ArrayList;

/** 
 * Description de la classe JeuDeLaVie
 */
public class JeuDeLaVie implements Rule {
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur du JeuDeLaVie
	 */
	public JeuDeLaVie() {}
	
	/** 
	 * Fonction cherchant l'�tat d'une cellule au tour suivant selon les r�gles du jeu de la vie
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
		//Si la cellule poss�de exatement 2 voisines vivantes alors son �tat au tour suivant restera le m�me
		if (nbNeighbor == 2) {
			return currentState;
		}
		//Si la cellule poss�de exatement 3 voisines vivantes alors son �tat au tour suivant sera vivante qu'importe son �tat actuel
		else if(nbNeighbor == 3) {
			return State.VIVANT;
		}
		//Sinon la cellule aura pour �tat mort au tour suivant qu'importe son �tat actuel
		else {
			return State.MORT;
		}
	}
	
}
