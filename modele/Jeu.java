package modele;

import connexion.Connection;
import controleur.Controle;

public abstract class Jeu 
{
	protected Controle controle;
	
	public abstract setConnection(Connection connection) ;
	public abstract reception(Connection connection, Object info) ;
	public abstract deconnection(Connection connection) ;
	public void envoi(Connection connection, Object info)
	{
		connection.envoi(info) ;
	}
}
