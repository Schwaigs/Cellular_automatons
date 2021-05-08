package automate.controller;

import automate.model.Grid;
import automate.model.Neighbourhood;
import automate.model.VanNeumann;
import automate.model.Moore1;
import automate.model.Rule;
import automate.model.JeuDeLaVie;
import automate.model.Automate;
import automate.model.Fredkin;


/** 
 * Description de la classe FabricAutomate
 */
public class FabricAutomate {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param nbPlayer : entier correspondant au nombre de joueurs et ainsi au nombre d'automates
	 */
	private int nbPlayer = 0;
	
	/** 
	 * @param instance : unique instance de notre fabrique d'automate qui est un singleton
	 */
	private static final FabricAutomate instance = new FabricAutomate();
	

	//------------------------------ M�thodes -------------------------------------
	/** 
	 * Constructeur priv� du singleton FabricAutomate
	 */
	private FabricAutomate() {}
	
	
	/** 
	 * Fonction cr�ant un nouvel automate en fonction des impl�m�ntations choisit par le joueur
	 * @param neighMethod : NeighborMethod correspondant au type de voisinage d'une cellule
	 * @param rule : RulesMethod correspondant au r�gles de changement d'�tat d'une cellule
	 * @param grid : Grille dans laquelle �volue l'automate
	 * @return le nouvel automate cr��
	 */
	public Automate createAutomate(NeighborMethod neighMethod, RulesMethod rule, Grid grid) {
		//Cr�ation de l'implementation du type de voisinage choisit par le joueur pour son automate
		Neighbourhood neighborType;
		switch (neighMethod) {
			case VanNeumann : 
				neighborType = new VanNeumann();
				break;
			default : 
				neighborType = new Moore1();
				break;
		}
		//Cr�ation de l'implementation des r�gles d'�volution choisies par le joueur pour son automate
		Rule ruleType;
		switch (rule) {
			case Fredkin : 
				ruleType = new Fredkin();
				break;
			default :
				ruleType = new JeuDeLaVie();
				break;
		}
		//Cr�ation de l'automate
		Automate automate = new Automate(neighborType,grid,nbPlayer,ruleType);
		
		//incr�mentation du nombre de joueur pour le prochain identifant
		nbPlayer++;
		return automate;
	}
	
	//------------------------------ Accesseurs -------------------------------------
	/** 
	 * Fonction permettant de manipuler la fabrique d'automate
	 * @return l'unique instance de FabricAutomate
	 */
	public static final FabricAutomate getInstance() {
		return instance;
	}
	
	/** 
	 * Fonction permettant d'acc�der au nombre de joueurs 
	 * @return le nombre de joueurs
	 */
	public int getNbPlayer() {
		return nbPlayer;
	}
	
}
