package automate.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automate.controller.GridExtend;
import automate.controller.NeighborMethod;
import automate.controller.RulesMethod;

/** 
 * Description de la classe Settings
 */
public class Settings  extends JFrame{
	
	//------------------------------ Attributs -------------------------------------
	/** 
	 * @param serialVersionUID : Nombre servant à la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * @param principalPanel : JPanel principal de la fenêtre
	 */
	private JPanel principalPanel = new JPanel();
	
	/** 
	 * @param comboNeighMethod1 : JComboBox<NeighborMethod> proposant au joueur 1 le choix du voisinage de son automate
	 */
	private JComboBox<NeighborMethod> comboNeighMethod1;
	
	/** 
	 * @param comboNeighMethod2 : JComboBox<NeighborMethod> proposant au joueur 2 le choix du voisinage de son automate
	 */
	private JComboBox<NeighborMethod> comboNeighMethod2;
	
	/** 
	 * @param comboRules1 : JComboBox<RulesMethod> proposant au joueur 1 le choix des règles d'évolution de son automate
	 */
	private JComboBox<RulesMethod> comboRules1;
	
	/** 
	 * @param comboRules2 : JComboBox<RulesMethod> proposant au joueur 2 le choix des règles d'évolution de son automate
	 */
	private JComboBox<RulesMethod> comboRules2;
	
	/** 
	 * @param start : JButton boutton validant les choix et faisant passer à la phase 2 du jeu
	 */
	private JButton start = new JButton("Lancer");

	//------------------------------ Méthodes -------------------------------------
	/** 
	 * Constructeur de Settings
	 */
	public Settings() {
		this.setTitle("Settings");
	    this.setSize(1000, 600);
	    this.setLocationRelativeTo(null);    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Mise en forme du panel principal
	    principalPanel.setLayout(new BoxLayout(principalPanel, BoxLayout.PAGE_AXIS));	    
	    
	    JPanel b1 = createPanel("Bienvenue dans Automates Games");			    //Titre
	    principalPanel.add(b1);

	    JPanel b2 = createPanel("Taille de la grille : ");        			    //Taille de la grille
	    JTextField gridSize = new JTextField("10");
	    b2.add(gridSize);
	    principalPanel.add(b2);

	    JPanel panelMaxIter = createPanel("Nombre d'itération maximum : ");    //Nombre max d'itération
	    JTextField maxIteration = new JTextField("10");
	    panelMaxIter.add(maxIteration);
	    principalPanel.add(panelMaxIter); 
	    
	    JPanel panelExtend = createPanel("Méthode d'extension de la grille : ");//Méthode extension grille
	    JComboBox<GridExtend> comboExtend = new JComboBox<GridExtend>();
	    for (GridExtend gridMet : GridExtend.values()) {
	    	comboExtend.addItem(gridMet); 
	    }
	    panelExtend.add(comboExtend);
	    principalPanel.add(panelExtend);

	    JPanel panelNbCell = createPanel("Nombre de cellules par automates : ");//Nombre de cellules par automates
	    JTextField numberCells = new JTextField("5");
	    panelNbCell.add(numberCells);
	    principalPanel.add(panelNbCell);
	    
	    //Ajouter les panel contenant les informations à remplir :
	    addNeighboorMethod(); //Méthode du choix de voisinnage
	    addRulesMethod();     //Règle de l'automate
	    
	    
	    start.setEnabled(false);
    	start.addActionListener( new ActionListener()  {
   		 @Override
   		 public void actionPerformed(ActionEvent e) {
   			 new Game(Integer.valueOf(gridSize.getText()), Integer.valueOf(numberCells.getText()),
   					 Integer.valueOf(maxIteration.getText()), (GridExtend)comboExtend.getSelectedItem(),
   					 (NeighborMethod)comboNeighMethod1.getSelectedItem(),(RulesMethod)comboRules1.getSelectedItem(),
   					 (NeighborMethod)comboNeighMethod2.getSelectedItem(), (RulesMethod)comboRules2.getSelectedItem()).setVisible(true);
   			 exit();
   		 }
	 	});

	    principalPanel.add(start);
		
	    this.getContentPane().add(principalPanel);	    
	}
	
	/** 
	 * Fonction ajoutant les combobox liées aux choix du voisinage des automates
	 */
	private void addNeighboorMethod() {
		//Type de recherche des voisins automate du Joueur 1
	    JPanel panelNeigh1 = createPanel("Type de recherche des voisins automate Joueur 1 : ");
	    comboNeighMethod1 = new JComboBox<NeighborMethod>();
	    
	    //Type de recherche des voisins automate du Joueur 2
	    JPanel panelNeigh2 = createPanel("Type de recherche des voisins automate Joueur 2 : ");
	    comboNeighMethod2 = new JComboBox<NeighborMethod>();

	    //Remplissage des méthodes de recherhce
	    for (NeighborMethod neighMet : NeighborMethod.values()) {
	    	comboNeighMethod1.addItem(neighMet);
	    	comboNeighMethod2.addItem(neighMet);
	    }
	    
	  //Ajout les combo box aux panel correspondant et au principal
	    addComboBoxPanel(comboNeighMethod1, panelNeigh1);
	    addComboBoxPanel(comboNeighMethod2, panelNeigh2);
	}
	
	/** 
	 * Fonction ajoutant les combobox aux choix des règles d'évolution des automates
	 */
	private void addRulesMethod() {
		//Règle de l'automate du Joueur 1
	    JPanel panelRules1 = createPanel("Règle automate Joueur 1 : ");
	    comboRules1 = new JComboBox<RulesMethod>();
	    
	    //Règle de l'automate du Joueur 2
	    JPanel panelRules2 = createPanel("Règle automate Joueur 2 : "); 
	    comboRules2 = new JComboBox<RulesMethod>();
	    
	    //Remplissage des règles
	    for (RulesMethod rules : RulesMethod.values()) {
	    	comboRules1.addItem(rules);
	    	comboRules2.addItem(rules);
	    }
	    
	    //Ajout les combo box aux panel correspondant et au principal
	    addComboBoxPanel(comboRules1, panelRules1);
	    addComboBoxPanel(comboRules2, panelRules2);
	}
	
	/** 
	 * Fonction ajoutant les combobox à un certain panel
	 */
	private void addComboBoxPanel(JComboBox<?> cboxAny, JPanel panelCbox) {
	   	 //Ajout la combo box au panel correspondant
	   	 panelCbox.add(cboxAny);
	   	 
	   	 cboxAny.addActionListener (new ActionListener () {
	   	 	public void actionPerformed(ActionEvent e) {
	   			 if (!(((NeighborMethod)comboNeighMethod1.getSelectedItem() == (NeighborMethod)comboNeighMethod2.getSelectedItem()) &&
	   					 ((RulesMethod)comboRules1.getSelectedItem() == (RulesMethod)comboRules2.getSelectedItem()))) {
	   					 start.setEnabled(true);
	   			 }
	   			 else {
	   				 start.setEnabled(false);
	   			 }
	   	 	}
	   	 });
	   	 
	   	 //Ajout au panel principal
	    principalPanel.add(panelCbox);
	}

	
	/** 
	 * Fonction créant les differents panels de notre fenêtre
	 */
	private JPanel createPanel(String nameLabel) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(new JLabel(nameLabel));
	    return panel;
	}
	
	/** 
	 * Fonction fermant la fenêtre
	 */
	private void exit() {
		this.dispose();
	}
}