package automate.model;

import java.awt.Point;
import java.util.HashMap;

/** 
 * Description de la classe Periodicity
 */
public class Periodicity implements Extension{
	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur de Periodicity
	 */
	public Periodicity() {}
	
	/** 
	 * Fonction créant une extention périodique de la grille
	 * @param grid : HashMap<Point,Cell> collection des cellules qui composent notre grille avec pour clé leur position
	 * @param size : Nombre de lignes/colonnes de cellules exterieurs (égal à la taille de la grille)
	 */
	@Override
	public void addExtension(HashMap<Point, Cell> grid, int size) {
		//Parcours des lignes puis colonnes
		for(int line = -size; line < size*2; line++) {
			for(int column = -size; column < size*2; column++) {
				//si c'est hors de la grille
				int line_copy;
				int column_copy;
				if (!((-1 < line && line < size) && (-1 < column && column < size))) {
					//On identifie la cellule à recopier
					if (column < 0) {
						column_copy = column+size;
					}
					else if(column > size-1) {
						column_copy = column-size;
					}
					else {
						column_copy = column;
					}
					if (line < 0) {
						line_copy = line+size;
					}
					else if(line > size-1) {
						line_copy = line-size;
					}
					else {
						line_copy = line;
					}
					Point cell_to_copy_pos = new Point(line_copy, column_copy);
					Cell cell_to_copy = grid.get(cell_to_copy_pos);
					//On creer la nouvelle cellule qui sera hors de la grille
					Cell new_cell = new Cell();
					State[] cell_states = new_cell.getPlayersStates();
					//cell_states[0] = cell_to_copy.getPlayersStates()[0];
					//cell_states[1] = cell_to_copy.getPlayersStates()[1];
					//Ses états seront les mêmes que ceux de la cellule à copier
					System.arraycopy(cell_to_copy.getPlayersStates(), 0, cell_states, 0, 2);
					grid.put(new Point(line,column), new_cell);
				}
			}
		}
	}

}