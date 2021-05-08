package automate.model;

import java.util.ArrayList;

/** 
 * Description de la classe JeuDeLaVie
 */
public class JeuDeLaVie implements Rule {
	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur du JeuDeLaVie
	 */
	public JeuDeLaVie() {}
	
	/** 
	 * Fonction cherchant l'état d'une cellule au tour suivant selon les règles du jeu de la vie
	 * @param currentState : Etat de la cellule pour le tour de jeu courant
	 * @param states : Etats des différents voisines de la cellules pour le tour de jeu courant
	 * @return l'état de la cellule au tour suivant
	 */
	public State next(State currentState, ArrayList<State> states) {
		//On compte le nombre de voisines ayant l'état vivant
		int nbNeighbor = 0;
		for (State neighbor_state : states){
			if (neighbor_state == State.VIVANT) {
				nbNeighbor++;
			}
		}
		//Si la cellule possède exatement 2 voisines vivantes alors son état au tour suivant restera le même
		if (nbNeighbor == 2) {
			return currentState;
		}
		//Si la cellule possède exatement 3 voisines vivantes alors son état au tour suivant sera vivante qu'importe son état actuel
		else if(nbNeighbor == 3) {
			return State.VIVANT;
		}
		//Sinon la cellule aura pour état mort au tour suivant qu'importe son état actuel
		else {
			return State.MORT;
		}
	}
	
}
