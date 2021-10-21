package modele;

import outils.connexion.Connection;

import javax.swing.JPanel;

import controleur.Controle;

public class JeuClient extends Jeu
{
	/*
	 * Liste propriétés.
	 */
	private Connection connection ;
	
	// constructeur
	public JeuClient(Controle controle)
	{
		super.controle = controle ;
	}
	
	
	/*
	 * Méthode setConnection.
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
	}
	
	/*
	 * Méthode reception, réceptionne les infos de l'ordinateur distant.
	 */
	public void reception(Connection connection, Object info)
	{
		// Reception info JPanel.
		if (info instanceof JPanel) /*--->*/ controle.evenementModele
											 (
												 this,
												 "envoi panel murs",
												 info
											 ) ;
		// Reception info Label.
		else if (info instanceof Label) /*--->*/ controle.evenementModele
												 (
													 this,
													 "ajout joueur", 
													 info
												 ) ;
		// Reception info String.
		else if (info instanceof String) /*--->*/ controle.evenementModele
												  (
													  this,
													  "remplace chat",
													  info
												  );
		
	}

	public void deconnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Méthode reception, envoi les infos aux ordinateurs distants.
	 */
	public void envoi(Object info)
	{
		this.connection.envoi(info) ;
	}
	
}
