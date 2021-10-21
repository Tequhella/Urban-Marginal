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
		this.frmEntreeJeu = new EntreeJeu(this) ; //Cr�ation frame dans la m�moire.
		this.frmEntreeJeu.setVisible(true) ;	  //Rend visible la frame.
	}
	
	/*
	 * M�thode evenementVue
	 */
	public void evenementVue(JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			System.out.println("R�ception info : EntreeJeu") ;
			evenementEntreeJeu(info) ;
		}
		else if (uneFrame instanceof ChoixJoueur)
		{
			System.out.println("R�ception info : ChoixJoueur") ;
			evenementChoixJoueur(info) ;
		}
		else if (uneFrame instanceof Arene)
		{
			System.out.println("R�ception info : Arene") ;
			evenementArene(info) ;
		}
	}
	
	/*
	 * M�thode evenementEntreeJeu
	 */
	private void evenementEntreeJeu(Object info)
	{
		// c�t� Serveur
		if ((String)info == "serveur")
		{
			new ServeurSocket(this, PORT) ;
			this.leJeu = new JeuServeur(this) ;
			this.frmEntreeJeu.dispose() ;
			this.frmArene = new Arene("serveur", this) ;
			((JeuServeur)leJeu).constructionMurs() ;
			this.frmArene.setVisible(true) ;
		}
		// c�t� Client
		else
		{
			if (new ClientSocket((String)info, PORT, this).isConnexionOk())
			{
				this.leJeu = new JeuClient(this) ;
				this.leJeu.setConnection(connection);
				this.frmEntreeJeu.dispose() ;
				this.frmArene = new Arene("client", this) ;
				this.frmChoixJoueur = new ChoixJoueur(this) ;
				this.frmChoixJoueur.setVisible(true) ;
			}
		}
		
	}
	
	/*
	 * M�thode evenementChoixJoueur
	 */
	private void evenementChoixJoueur(Object info)
	{
		((JeuClient)leJeu).envoi(info) ;
		frmChoixJoueur.dispose() ;
		frmArene.setVisible(true) ;
	}
	
	/*
	 * M�thode evenementModele : s'occupe de g�rer si un �v�nement
	 * est c�t� Serveur ou Client.
	 */
	public void evenementModele(Object unJeu, String ordre, Object info)
	{
		// c�t� Serveur
		if (unJeu instanceof JeuServeur) /*--->*/ evenementJeuServeur(ordre, info) ;
		
		// c�t� client
		else if (unJeu instanceof JeuClient) /*--->*/ evenementJeuClient(ordre, info) ;
	}
	
	
	/*
	 * M�thode evenementJeuServer : s'occupe des �v�nements c�t� Client.
	 */
	public void evenementJeuServeur(String ordre, Object info)
	{
		System.out.println("Ordre : " + ordre) ;
		
		// Ajout d'un mur dans la frame Arene.
		if (ordre.equals("ajout mur")) /*--->*/	frmArene.ajoutMur((JLabel)info) ;
		
		// Envoi du panel murs entier c�t� Client.
		else if (ordre.equals("envoi panel murs")) /*--->*/ ((JeuServeur)leJeu).envoi
															(
																(Connection)info,
																frmArene.getJpnMurs()
															) ;
		
		// Ajout d'un joueur dans la frame Arene.
		else if (ordre.equals("ajout joueur")) /*--->*/ frmArene.ajoutJoueur((JLabel)info);
		
		// Ajout d'une phrase dans le chat.
		else if (ordre.equals("ajout phrase"))
		{
			frmArene.ajoutChat((String)info) ;
			((JeuServeur)leJeu).envoi(frmArene.getTxtChat()) ;
		}
			
	}
	
	/*
	 * M�thode evenementJeuClient : s'occupe des �v�nements c�t� Client.
	 */
	public void evenementJeuClient(String ordre, Object info)
	{
		// Envoi du panel murs dans la frame Arene.
		if (ordre.equals("envoi panel murs")) /*--->*/ frmArene.ajoutPanelMurs((JPanel) info) ;
		
		// Ajout d'un joueur dans la frame Arene.
		else if (ordre.equals("ajout joueur")) /*--->*/ frmArene.ajoutModifJoueur
														(
															((Label)info).getNumLabel(),
															((Label)info).getJLabel()
														) ;
		// Remplacement chat.
		else if (ordre.equals("remplace chat")) /*--->*/ frmArene.remplaceChat((String)info) ;
	}
	
	/*
	 * M�thode evenementArene : s'occupe des �v�nements de l'Arene.
	 */
	private void evenementArene(Object info)
	{
		((JeuClient)leJeu).envoi(info) ;
	}
	
	/*
	 * M�thode setConnection
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
		
		if (leJeu instanceof JeuServeur) /*--->*/ leJeu.setConnection(connection) ;
	}
	
	
	/*
	 * M�thode receptionInfo
	 */
	public void receptionInfo(Connection connection, Object info)
	{
		leJeu.reception(connection, info) ;
	}
	
	
}
