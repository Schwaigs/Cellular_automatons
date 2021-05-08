package automate;

import automate.view.Settings;

public class Start {
	/** 
	 * Fonction permettant de lancer l'application en initialisant la vue
	 */
	public static void main(String[] args) {
		new Settings().setVisible(true);
	}
}
