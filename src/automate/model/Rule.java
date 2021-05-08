package automate.model;
import java.util.ArrayList;

/** 
 * Description de l'interface Rule
 */
public interface Rule {
	
	/** 
	 * Fonction cherchant l'�tat d'une cellule au tour suivant selon les r�gles d�finies dans l'impl�mentation
	 * @param currentState : Etat de la cellule pour le tour de jeu courant
	 * @param states : Etats des diff�rents voisines de la cellules pour le tour de jeu courant
	 * @return l'�tat de la cellule au tour suivant
	 */
	State next(State currentState, ArrayList<State> states);
}
