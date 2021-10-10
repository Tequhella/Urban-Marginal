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
		this.frmEntreeJeu = new EntreeJeu(this) ; //Cr�ation frame dans la m�moire.
		this.frmEntreeJeu.setVisible(true) ;	  //Rend visible la frame.
	}
	
	/*
	 * Cr�ation m�thode evenementVue
	 */
	
	public void evenementVue(JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			evenementEntreeJeu(info) ;
		}
		
		if (uneFrame instanceof ChoixJoueur)
		{
			System.out.println("R�ception info") ;
			evenementChoixJoueur(info) ;
		}
	}
	
	/*
	 * Cr�ation m�thode evenementEntreeJeu
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
	 * Cr�ation m�thode evenementChoixJoueur
	 */
	
	private void evenementChoixJoueur(Object info)
	{
		((modele.JeuClient)leJeu).envoi(info) ;
		frmChoixJoueur.dispose() ;
		frmArene = new Arene() ;
		frmArene.setVisible(true) ;
	}
	
	/*
	 * Cr�ation m�thode setConnection
	 */
	
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
	}
	
	/*
	 * Cr�ation m�thode evenementChoixJoueur
	 */
	
	public void receptionInfo(Connection connection, Object info)
	{
		leJeu.reception(connection, info) ;
	}
}
