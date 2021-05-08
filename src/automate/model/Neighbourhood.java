package automate.model;
import java.awt.Point;
import java.util.ArrayList;

/** 
 * Description de l'interface Neighbourhood
 */
public interface Neighbourhood {
	
	/** 
	 * Fonction cherchant les �tats des voisines d'une cellule d'un certain joueur selon le type de voisinage d�finit dans l'impl�mentation
	 * @param grid : Grille dans laquelle �volue l'automate
	 * @param cell_position : Point correspondant � la position de la cellule dont on cherche les voisines
	 * @param player : entier correspondant � l'identifiant du joueur � qui appartient l'automate
	 * @return les �tats des voisines d'une cellule
	 */
	ArrayList<State> getNeighbor(Grid grid, Point cell_position, int player);
}
