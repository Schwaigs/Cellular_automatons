package automate.model;

import java.awt.Point;
import java.util.HashMap;

public class Grid {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param extendType : Extension correspondant à la méthode d'extension de la grille
	 */
	private Extension extendType;
	
	/** 
	 * @param size : entier correspondant à la taille de la grille
	 */
	private int size;
	
	/** 
	 * @param cells : HashMap<Point,Cell> collection des cellules qui composent notre grille avec pour clé leur position
	 */
	private HashMap<Point,Cell> cells;
	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur d'une grille
	 * @param extend : Extension correspondant à la méthode d'extension de la grille
	 * @param n : entier correspondant à la taille de la grille
	 */
	public Grid(Extension extend, int n) {
		this.size = n;
		extendType = extend;
		cells = new HashMap<Point,Cell>();
		
		//Inititalisation de la grille de jeu
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				cells.put(new Point(i,j), new Cell());
			}
		}
		//Initalisation de l'extension de la grille en fonction de la méthode demandée
		extendType.addExtension(cells, size);
	}
	
	
	//------------------------------ Accesseurs -------------------------------------	
	/** 
	 * Fonction permettant d'accéder à la collection de cellules de la grille
	 * @return la collection de cellules
	 */
	public HashMap<Point,Cell> getCells() {
		return cells;
	}
	
	/** 
	 * Fonction permettant de modifier la collection de cellules de la grille
	 * @param new_cells : HashMap<Point,Cell> la nouvelle collection de cellules
	 */
	public void setCells(HashMap<Point,Cell> new_cells ) {
		this.cells = new_cells;
	}
	
	/** 
	 * Fonction permettant d'accéder à la taille de la grille
	 * @return la taille
	 */
	public int getSize() {
		return size;
	}
	
	/** 
	 * Fonction permettant d'accéder au type d'extension de la grille
	 * @return le type d'extension
	 */
	public Extension getExtendType() {
		return extendType;
	}
	
}