package automate.model;
import java.util.ArrayList;

/** 
 * Description de l'interface Rule
 */
public interface Rule {
	
	/** 
	 * Fonction cherchant l'état d'une cellule au tour suivant selon les règles définies dans l'implémentation
	 * @param currentState : Etat de la cellule pour le tour de jeu courant
	 * @param states : Etats des différents voisines de la cellules pour le tour de jeu courant
	 * @return l'état de la cellule au tour suivant
	 */
	State next(State currentState, ArrayList<State> states);
}
