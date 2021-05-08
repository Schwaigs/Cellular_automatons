package automate.model;

import java.awt.Point;
import java.util.HashMap;

public interface Extension {
	/** 
	 * Fonction cr�ant une extention de la grille
	 * @param grid : HashMap<Point,Cell> collection des cellules qui composent notre grille avec pour cl� leur position
	 * @param size : Nombre de lignes/colonnes de cellules exterieurs
	 */
	public void addExtension(HashMap<Point,Cell> grid, int size);

}
