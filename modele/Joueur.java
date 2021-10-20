package modele;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

public class Joueur extends Objet implements Global
{
	/*
	 * Liste des propriétés.
	 */
	private String     pseudo ;   	 // pseudo du joueur
	private int        numPerso ; 	 // numéro du personnage
	private Label  	   message ;
	private JeuServeur jeuServeur;
	private int 	   vie ;		 // vie restante du joueur
	private int 	   orientation ; // tourné vers la gauche (0) ou vers la droite (1)
	private int		   etape ;		 // numéro d'étape dans l'animation
	
	//constructeur
	public Joueur (JeuServeur jeuServeur)
	{
		this.jeuServeur = jeuServeur ;
		
		vie 		= 10 ;
		etape 		= 1 ;
		orientation = DROITE ;
	}
	
	/*
	 * Méthode initPerso : s'occupe d'initialiser les paramètres d'un personnage.
	 */
	public void initPerso(String pseudo,
						  int numPerso,
						  Hashtable<Connection, Joueur> lesJoueurs,
						  ArrayList<Mur> lesMurs)
	{
		this.pseudo   = pseudo ;
		this.numPerso = numPerso ;
		
		this.label = new Label(Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel() + 1);
		jeuServeur.nouveauLabelJeu(this.label) ;
		
		label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER) ;
		label.getJLabel().setVerticalAlignment(SwingConstants.CENTER) ;
		
		message = new Label(Label.getNbLabel(), new JLabel()) ;
		message.getJLabel().setHorizontalAlignment(SwingConstants.CENTER) ;
		message.getJLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message) ;
		
		premierePosition(lesJoueurs, lesMurs) ;
		affiche(MARCHE, etape) ;
	}
	
	/*
	 * Méthode toucheJoueur : vérifie le joueur s'il y a une colision
	 */
	private boolean toucheJoueur(Hashtable<Connection, Joueur> lesJoueurs)
	{
		for (Joueur joueurs : lesJoueurs.values())
		{
			if (joueurs.equals(this) == false)
			{
				if (super.toucheObjet(joueurs) == true)
					return true ;
			}
		}
		return false ;
	}
	
	/*
	 * Méthode toucheMur : vérifie le mur s'il y a une collision
	 * avec un autre.
	 */
	private boolean toucheMur(ArrayList<Mur> lesMurs)
	{
		for (Mur mur : lesMurs)
		{
			if (super.toucheObjet(mur) == true)
				return true ;
		}
		return false ;
	}
	
	/*
	 * Méthode premierePosition : place le joueur aléatoirement,
	 * s'il y a une collision, le joueur est déplacé.
	 */
	private void premierePosition(Hashtable<Connection, Joueur> lesJoueurs,
								  ArrayList<Mur> lesMurs)
	{
		this.label.getJLabel().setBounds(0, 0, L_PERSO, H_PERSO) ;
		
		do
		{
			this.posX = (int)Math.round(Math.random() * L_ARENE - L_PERSO) ;
			this.posY = (int)Math.round(Math.random() * H_ARENE - H_PERSO - H_MESSAGE) ;
		}
		while (toucheJoueur(lesJoueurs)) ;
	}
	
	/*
	 * 
	 */
	private void affiche(String etat, int etape)
	{
		this.label.getJLabel().setBounds(this.posX, this.posY, L_PERSO, H_PERSO) ;
		this.label.getJLabel().setIcon(new ImageIcon(CHEMINPERSOS +
													 PERSO 		  +
													 numPerso 	  +
													 etat 		  +
													 etape		  +
													 'd' 		  +
													 orientation  +
													 EXTIMAGE)) ;
		
		message.getJLabel().setBounds(this.posX - 10,
									  this.posY + H_PERSO,
									  L_PERSO + 10,
									  H_MESSAGE) ;
		message.getJLabel().setText(pseudo + " : " + vie) ;
		jeuServeur.envoi(this.label) ;
		jeuServeur.envoi(message) ;
	}
	
	/*
	 *  Getter de messages.
	 */
	public Label getMessage()
	{
		return message;
	}
}
