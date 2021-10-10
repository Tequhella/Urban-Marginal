package controleur;

import javax.swing.JFrame;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;

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
	 * Création méthode evenementVue
	 */
	
	public void evenementVue(JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			evenementEntreeJeu(info) ;
		}
		
		if (uneFrame instanceof ChoixJoueur)
		{
			System.out.println("Réception info") ;
			evenementChoixJoueur(info) ;
		}
	}
	
	/*
	 * Création méthode evenementEntreeJeu
	 */

	private void evenementEntreeJeu(Object info)
	{
		if ((String)info == "serveur")
		{
			new ServeurSocket(this, PORT) ;
			leJeu = new JeuServeur(this) ;
			frmEntreeJeu.dispose() ;
			frmArene = new Arene() ;
			frmArene.setVisible(true) ;
		}
		else
		{
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
	 * Création méthode evenementChoixJoueur
	 */
	
	private void evenementChoixJoueur(Object info)
	{
		((modele.JeuClient)leJeu).envoi(info) ;
		frmChoixJoueur.dispose() ;
		frmArene = new Arene() ;
		frmArene.setVisible(true) ;
	}
	
	/*
	 * Création méthode setConnection
	 */
	
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
	}
	
	/*
	 * Création méthode evenementChoixJoueur
	 */
	
	public void receptionInfo(Connection connection, Object info)
	{
		leJeu.reception(connection, info) ;
	}
}
