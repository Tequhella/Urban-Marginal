package modele;

import outils.connexion.Connection;

import javax.swing.JPanel;

import controleur.Controle;

public class JeuClient extends Jeu
{
	/*
	 * Liste propri�t�s.
	 */
	private Connection connection ;
	
	// constructeur
	public JeuClient(Controle controle)
	{
		super.controle = controle ;
	}
	
	
	/*
	 * M�thode setConnection.
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
	}
	
	/*
	 * M�thode reception, r�ceptionne les infos de l'ordinateur distant.
	 */
	public void reception(Connection connection, Object info)
	{
		if (info instanceof JPanel)
		{
			controle.evenementModele(this, "envoi panel murs", info) ;
		}
		else
		{
			if (info instanceof Label)
			{
				controle.evenementModele(this, "ajout joueur", info) ;
			}
		}
	}

	public void deconnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * M�thode reception, envoi les infos aux ordinateurs distants.
	 */
	public void envoi(Object info)
	{
		this.connection.envoi(info) ;
	}
	
}
