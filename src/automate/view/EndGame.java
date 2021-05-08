package automate.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * Description de la classe EndGame
 */
public class EndGame extends JFrame {
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param serialVersionUID : Nombre servant à la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur de EndGame
	 * @param numberEndReason : entier correspondant au joueur ayant gagné la partie
	 */
	public EndGame(int numberEndReason) {
		this.setTitle("The end");
	    this.setSize(350, 100);
	    this.setLocationRelativeTo(null);    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setLayout(new BorderLayout());
	    
	    JLabel textEnd;
	    if ( numberEndReason > 0) 
	    {
	    	textEnd = new JLabel("La partie est terminé ! Le joueur " + numberEndReason + " a remporté la partie.");
	    }
	    else {
	    	textEnd = new JLabel("La partie est terminé ! C'est une égalité parfaite.");
	    }
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.add(textEnd);
	    this.add(panelPrincipal,BorderLayout.CENTER);
	}

}
