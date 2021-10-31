package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

public class Boule extends Objet implements Global
{
	/*
	 * Liste propriétés.
	 */
	private JeuServeur jeuServeur ;
	
	// constructeur
	public Boule(JeuServeur jeuServeur)
	{
		this.jeuServeur = jeuServeur ;
		
		super.label = new Label(Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel() + 1) ;
		
		super.label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER) ;
		super.label.getJLabel().setVerticalAlignment(SwingConstants.CENTER) ;
		super.label.getJLabel().setBounds(0, 0, L_BOULE, H_BOULE) ;
		super.label.getJLabel().setIcon(new ImageIcon(BOULE)) ;
		super.label.getJLabel().setVisible(false) ;
		
		jeuServeur.nouveauLabelJeu(super.label) ;
	}
	
	public void tireBoule(Joueur attaquant)
	{
		if (attaquant.getOrientation() == GAUCHE) /*--->*/ this.posX = attaquant.getPosX() - L_BOULE - 1 ;
		else /*---------------------------------------->*/ this.posX = attaquant.getPosX() + L_BOULE + 1 ;
		
		this.posY = attaquant.getPosY() + L_PERSO / 2 ;
		
		new Attaque(attaquant, jeuServeur) ;
	}
}
