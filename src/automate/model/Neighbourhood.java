package automate.model;
import java.awt.Point;
import java.util.ArrayList;

/** 
 * Description de l'interface Neighbourhood
 */
public interface Neighbourhood {
	
	/** 
	 * Fonction cherchant les états des voisines d'une cellule d'un certain joueur selon le type de voisinage définit dans l'implémentation
	 * @param grid : Grille dans laquelle évolue l'automate
	 * @param cell_position : Point correspondant à la position de la cellule dont on cherche les voisines
	 * @param player : entier correspondant à l'identifiant du joueur à qui appartient l'automate
	 * @return les états des voisines d'une cellule
	 */
	ArrayList<State> getNeighbor(Grid grid, Point cell_position, int player);
}
