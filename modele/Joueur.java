package modele;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

public class Joueur extends Objet implements Global
{
	/*
	 * Liste des propri�t�s.
	 */
	private String pseudo ;
	private int    numPerso ;
	private Label  message ;
	
	//constructeur
	public Joueur ()
	{
		
	}
	
	/*
	 * M�thode initPerso : s'occupe d'initialiser les param�tres d'un personnage.
	 */
	public void initPerso(String pseudo,
						  int numPerso,
						  Hashtable<Connection, Joueur> lesJoueurs,
						  ArrayList<Mur> lesMurs)
	{
		this.pseudo   = pseudo ;
		this.numPerso = numPerso ;
		
		Label label = new Label(super.label.getNbLabel(), new JLabel()) ;
		label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER) ;
		label.getJLabel().setVerticalAlignment(SwingConstants.CENTER) ;
		
		message = new Label(super.label.getNbLabel(), new JLabel()) ;
		message.getJLabel().setHorizontalAlignment(SwingConstants.CENTER) ;
		message.getJLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		
		premierePosition(lesJoueurs, lesMurs) ;
	}
	
	/*
	 * M�thode toucheJoueur : v�rifie le joueur s'il y a une colision
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
	 * M�thode toucheMur : v�rifie le mur s'il y a une collision
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
	 * M�thode premierePosition : place le joueur al�atoirement,
	 * s'il y a une collision, le joueur est d�plac�.
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
	 *  Getter de messages.
	 */
	public Label getMessage()
	{
		return message;
	}
}
