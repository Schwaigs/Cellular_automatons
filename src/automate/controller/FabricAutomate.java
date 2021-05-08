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
	

	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur privé du singleton FabricAutomate
	 */
	private FabricAutomate() {}
	
	
	/** 
	 * Fonction créant un nouvel automate en fonction des impléméntations choisit par le joueur
	 * @param neighMethod : NeighborMethod correspondant au type de voisinage d'une cellule
	 * @param rule : RulesMethod correspondant au règles de changement d'état d'une cellule
	 * @param grid : Grille dans laquelle évolue l'automate
	 * @return le nouvel automate créé
	 */
	public Automate createAutomate(NeighborMethod neighMethod, RulesMethod rule, Grid grid) {
		//Création de l'implementation du type de voisinage choisit par le joueur pour son automate
		Neighbourhood neighborType;
		switch (neighMethod) {
			case VanNeumann : 
				neighborType = new VanNeumann();
				break;
			default : 
				neighborType = new Moore1();
				break;
		}
		//Création de l'implementation des règles d'évolution choisies par le joueur pour son automate
		Rule ruleType;
		switch (rule) {
			case Fredkin : 
				ruleType = new Fredkin();
				break;
			default :
				ruleType = new JeuDeLaVie();
				break;
		}
		//Création de l'automate
		Automate automate = new Automate(neighborType,grid,nbPlayer,ruleType);
		
		//incrémentation du nombre de joueur pour le prochain identifant
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
	 * Fonction permettant d'accéder au nombre de joueurs 
	 * @return le nombre de joueurs
	 */
	public int getNbPlayer() {
		return nbPlayer;
	}
	
}
