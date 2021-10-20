package controleur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Label;

public class Controle implements Global
{
	//proprietes
	private EntreeJeu 	frmEntreeJeu ;
	private Arene	  	frmArene ;
	private ChoixJoueur frmChoixJoueur ;
	private Connection  connection ;
	private Jeu 	  	leJeu ;
	
	//demarrage appli
	public static void main(String[] args)
	{
		new Controle() ;
	}
	
	//constructeur
	private Controle()
	{
		this.frmEntreeJeu = new EntreeJeu(this) ; //Création frame dans la mémoire.
		this.frmEntreeJeu.setVisible(true) ;	  //Rend visible la frame.
	}
	
	/*
	 * Méthode evenementVue
	 */
	public void evenementVue(JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			evenementEntreeJeu(info) ;
		}
		else
		{
			if (uneFrame instanceof ChoixJoueur)
			{
				System.out.println("Réception info") ;
				evenementChoixJoueur(info) ;
			}
		}
		
			
	}
	
	/*
	 * Méthode evenementEntreeJeu
	 */
	private void evenementEntreeJeu(Object info)
	{
		// côté Serveur
		if ((String)info == "serveur")
		{
			new ServeurSocket(this, PORT) ;
			leJeu = new JeuServeur(this) ;
			frmEntreeJeu.dispose() ;
			frmArene = new Arene() ;
			((JeuServeur)leJeu).constructionMurs() ;
			frmArene.setVisible(true) ;
		}
		else
		{
			// côté Client
			if (new ClientSocket((String)info, PORT, this).isConnexionOk())
			{
				leJeu = new JeuClient(this) ;
				leJeu.setConnection(connection);
				frmEntreeJeu.dispose() ;
				frmChoixJoueur = new ChoixJoueur(this) ;
				frmChoixJoueur.setVisible(true) ;
			}
		}
		
		
	}
	
	/*
	 * Méthode evenementChoixJoueur
	 */
	private void evenementChoixJoueur(Object info)
	{
		((modele.JeuClient)leJeu).envoi(info) ;
		frmChoixJoueur.dispose() ;
		frmArene = new Arene() ;
		frmArene.setVisible(true) ;
	}
	
	/*
	 * Méthode evenementModele, s'occupe de gérer si un évènement
	 * est côté Serveur ou Client.
	 */
	public void evenementModele(Object unJeu, String ordre, Object info)
	{
		// côté Serveur
		if (unJeu instanceof JeuServeur)
		{
			evenementJeuServeur(ordre, info) ;
		}
		else
		{
			// côté Client
			if (unJeu instanceof JeuClient)
			{
				evenementJeuClient(ordre, info) ;
			}
		}
	}
	
	
	/*
	 * Méthode evenementJeuServer, s'occupe des évènements côté Client.
	 */
	public void evenementJeuServeur(String ordre, Object info)
	{
		// Ajout d'un mur dans la frame Arene.
		if (ordre == "ajout mur")
		{
			frmArene.ajoutMur((JLabel)info) ;
		}
		else
		{
			// Envoi du panel murs entier côté Client.
			if (ordre == "envoi panel murs")
			{
				((JeuServeur)leJeu).envoi((Connection)info, frmArene.getJpnMurs()) ;
			}
			else
			{
				if (ordre == "ajout joueur")
				{
					frmArene.ajoutJoueur((JLabel)info);
				}
			}
		}
	}
	
	/*
	 * Méthode evenementJeuClient, s'occupe des évènements côté Client.
	 */
	public void evenementJeuClient(String ordre, Object info)
	{
		if (ordre == "envoi panel murs")
		{
			frmArene.ajoutPanelMurs((JPanel) info) ;
		}
		else
		{
			if (ordre == "ajout joueur")
			{
				frmArene.ajoutModifJoueur(((Label)info).getNumLabel(), ((Label)info).getJLabel()) ;
			}
		}
	}
	
	/*
	 * Méthode setConnection
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
		
		if (leJeu instanceof JeuServeur)
		{
			leJeu.setConnection(connection) ;
		}
	}
	
	
	/*
	 * Méthode receptionInfo
	 */
	public void receptionInfo(Connection connection, Object info)
	{
		System.out.println(info) ;
		leJeu.reception(connection, info) ;
	}
	
	
}
