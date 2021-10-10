package modele;

import outils.connexion.Connection;
import controleur.Controle;

public abstract class Jeu 
{
	protected Controle controle;
	
	public abstract void setConnection(Connection connection) ;
	public abstract void reception(Connection connection, Object info) ;
	public abstract void deconnection(Connection connection) ;
	public void envoi(Connection connection, Object info)
	{
		connection.envoi(info) ;
	}
}
