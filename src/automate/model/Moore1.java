package automate.model;
import java.awt.Point;
import java.util.ArrayList;

/** 
 * Description de la classe Moore1
 */
public class Moore1 implements Neighbourhood{
	
	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur de Moore1
	 */
	public Moore1() {}
	
	/** 
	 * Fonction cherchant les �tats des voisines d'une cellule d'un certain joueur selon le type de voisinage Moore1
	 * @param grid : Grille dans laquelle �volue l'automate
	 * @param cell_position : Point correspondant � la position de la cellule dont on cherche les voisines
	 * @param player : entier correspondant � l'identifiant du joueur � qui appartient l'automate
	 * @return les �tats des voisines d'une cellule
	 */
	public ArrayList<State> getNeighbor(Grid grid, Point cell_position, int player){
		//On cr�er une liste contenant l'�tat des diff�rents voisines de la cellules pour un automate en particulier
		ArrayList<State> state_neighbors = new ArrayList<>();
		
		//On cherche les diff�rents voisines de la cellules
		//on cr�er donc les diff�rentes positions des voisines que l'on stocke dans une collection
		ArrayList<Point> pos_neighbors = new ArrayList<>();
		
		//Le type de voisinage de Moore1 corresponds aux 8 cellules encerclants la cellule cible
		Point pos_east = new Point(cell_position.x+1,cell_position.y);
		pos_neighbors.add(pos_east);
		Point pos_west = new Point(cell_position.x-1,cell_position.y);
		pos_neighbors.add(pos_west);
		Point pos_north = new Point(cell_position.x,cell_position.y-1);
		pos_neighbors.add(pos_north);
		Point pos_south = new Point(cell_position.x,cell_position.y+1);
		pos_neighbors.add(pos_south);
		Point pos_south_east = new Point(cell_position.x+1,cell_position.y+1);
		pos_neighbors.add(pos_south_east);
		Point pos_south_west = new Point(cell_position.x-1,cell_position.y+1);
		pos_neighbors.add(pos_south_west);
		Point pos_north_east = new Point(cell_position.x+1,cell_position.y-1);
		pos_neighbors.add(pos_north_east);
		Point pos_north_west = new Point(cell_position.x-1,cell_position.y-1);
		pos_neighbors.add(pos_north_west);
		
		//On parcours ensuite notre collection de voisines
		for (Point pos : pos_neighbors) {
			//On cherche l'�tat de chacune d'elle pour notre automate dans la collection de notre grille
			state_neighbors.add(grid.getCells().get(pos).getPlayersStates()[player]);

		}
		return state_neighbors;
	}
}
