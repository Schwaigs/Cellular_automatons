package automate.model;

import java.awt.Point;
import java.util.HashMap;

/** 
 * Description de la classe Empty
 */
public class Empty implements Extension{

	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur de Empty
	 */
	public Empty() {}
	
	/** 
	 * Fonction créant une extention vide de la grille, toutes les cellules exterieurs sont mortes
	 * @param grid : HashMap<Point,Cell> collection des cellules qui composent notre grille avec pour clé leur position
	 * @param size : Nombre de lignes/colonnes de cellules exterieurs
	 */
	@Override
	public void addExtension(HashMap<Point, Cell> grid, int size) {
		//Parcours des lignes puis colonnes
		for(int line = -size; line < size*2; line++) {
			for(int column = -size; column < size*2; column++) {
				//si c'est hors de la grille
				if (!((-1 < line && line < size) && (-1 < column && column < size))) {
						grid.put(new Point(line,column), new Cell());
				}
			}
		}
	}

}