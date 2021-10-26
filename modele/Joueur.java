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
	 * Liste des propri�t�s.
	 */
	private String     pseudo ;   	 // pseudo du joueur
	private int        numPerso ; 	 // num�ro du personnage
	private Label  	   message ;
	private JeuServeur jeuServeur;
	private int 	   vie ;		 // vie restante du joueur
	private int 	   orientation ; // tourn� vers la gauche (0) ou vers la droite (1)
	private int		   etape ;		 // num�ro d'�tape dans l'animation
	
	//constructeur
	public Joueur (JeuServeur jeuServeur)
	{
		this.jeuServeur = jeuServeur ;
		
		vie 		= 10 ;
		etape 		= 1 ;
		orientation = DROITE ;
	}
	
	/*
	 * M�thode initPerso : s'occupe d'initialiser les param�tres d'un personnage.
	 */
	public void initPerso
	(
			String pseudo,
			int numPerso,
			Hashtable<Connection, Joueur> lesJoueurs,
			ArrayList<Mur> lesMurs
	)
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
	private void premierePosition
	(
			Hashtable<Connection, Joueur> lesJoueurs,
			ArrayList<Mur> lesMurs
	)
	{
		this.label.getJLabel().setBounds(0, 0, L_PERSO, H_PERSO) ;
		
		do
		{
			this.posX = (int)Math.round(Math.random() * L_ARENE - L_PERSO) ;
			this.posY = (int)Math.round(Math.random() * H_ARENE - H_PERSO - H_MESSAGE) ;
		}
		while (toucheJoueur(lesJoueurs) && toucheMur(lesMurs)) ;
	}
	
	/*
	 * 
	 */
	private void affiche(String etat, int etape)
	{
		this.label.getJLabel().setBounds(this.posX, this.posY, L_PERSO, H_PERSO) ;
		this.label.getJLabel().setIcon(new ImageIcon
									  (
											  CHEMINPERSOS +
											  PERSO 		  +
											  numPerso 	  +
											  etat 		  +
											  etape		  +
											  'd' 		  +
											  orientation  +
											  EXTIMAGE
									  )) ;
		
		message.getJLabel().setBounds
		(
				this.posX - 10,
				this.posY + H_PERSO,
				L_PERSO + 10,
				H_MESSAGE
		) ;
		message.getJLabel().setText(pseudo + " : " + vie) ;
		jeuServeur.envoi(this.label) ;
		jeuServeur.envoi(message) ;
	}
	
	/*
	 * M�thode deplace : s'occupe des d�placements.
	 */
	
	private int deplace
	(
			int action, 	 // pour conna�tre l'action sollicit�e (gauche, droite�)
			int position,	 // pour la position de d�part
			int orientation, // pour l'orientation de d�part
			int lepas,		 // pour la valeur du d�placement (en positif ou n�gatif)
			int max,		 // pour la valeur � ne pas d�passer
			Hashtable<Connection, Joueur> lesJoueurs, // le dictionnaire de joueurs
			ArrayList<Mur> lesMurs 					  // la collection de murs
	)
	{
		this.orientation = orientation ;
		int ancpos 		 = position ;
		position += lepas ;
		
		if (position < 0) /*----->*/ position = 0 ;
		if (position > max) /*--->*/ position = max ;
		
		if (action == GAUCHE || action == DROITE) /*--->*/ this.posX = position ;
		else /*---------------------------------------->*/ this.posY = position ;
		
		if (toucheJoueur(lesJoueurs) && toucheMur(lesMurs)) /*--->*/ position = ancpos ;
		
		// etape = (etape == NBETATSMARCHE) etape -= 3 : etape++ ;
		if (etape == NBETATSMARCHE) /*--->*/ etape -= 3 ;
		else /*-------------------------->*/ etape++ ;

		return position ;
	}
	
	/*
	 * M�thode action :
	 */
	
	public void action
	(
			int action,
			Hashtable<Connection, Joueur> lesJoueurs,
			ArrayList<Mur> lesMurs
	)
	{
		switch (action)
		{
			case GAUCHE: 
				 this.posX = deplace 
				 		(
				 				action,
				 				this.posX,
				 				GAUCHE,
				 				-LEPAS,
				 				L_ARENE - L_PERSO,
				 				lesJoueurs,
				 				lesMurs
		 				) ;
				break ;
			case DROITE: 
				this.posX = deplace 
		 		(
		 				action,
		 				this.posX,
		 				DROITE,
		 				LEPAS,
		 				L_ARENE - L_PERSO,
		 				lesJoueurs,
		 				lesMurs
 				) ;
				break ;
			case HAUT: 
				this.posY = deplace 
		 		(
		 				action,
		 				this.posY,
		 				orientation,
		 				-LEPAS,
		 				H_ARENE - H_PERSO - H_MESSAGE,
		 				lesJoueurs,
		 				lesMurs
 				) ;
				break ;
			case BAS : 
				this.posY = deplace 
		 		(
		 				action,
		 				this.posY,
		 				orientation,
		 				LEPAS,
		 				H_ARENE - H_PERSO - H_MESSAGE,
		 				lesJoueurs,
		 				lesMurs
 				) ;
				break ;
		}
		
		affiche(MARCHE, etape) ;
	}
	
	/*
	 *  Getter de messages et pseudo.
	 */
	public Label getMessage()
	{
		return message ;
	}
	
	public String getPseudo()
	{
		return pseudo ;
	}
}
