package automate.controller;

import automate.model.Empty;
import automate.model.Extension;
import automate.model.Grid;
import automate.model.Periodicity;

public class FabricGrid {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param instance : unique instance de notre fabrique de grille qui est un singleton
	 */
	private static FabricGrid instance = new FabricGrid();
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	* Constructeur priv� du singleton FabricGrid
	*/
	private FabricGrid() {}
	
	/** 
	 * Fonction cr�ant une grille en fonction des choix des joueurs
	 * @param typeOfExtend : GridExtend correspondant � la m�thode d'extension de la grille
	 * @param size : entier correspondant � la taille de la grille
	 * @return la grille cr��
	 */
	public Grid createGrid(GridExtend typeOfExtend, int size) {
		Grid cells;
		Extension extendType;
		switch (typeOfExtend) {
			case Periodicity: 
				extendType = new Periodicity();
				break;
			default: 
				extendType = new Empty();
				break;
		}
		cells = new Grid(extendType,size);
		return cells;
	}
	
	//------------------------------ Accesseurs -------------------------------------
	/** 
	 * Fonction permettant de manipuler la fabrique de grille
	 * @return l'unique instance de FabricGrid
	 */
	public static FabricGrid getInstance() {
		return instance;
	}
	
}
