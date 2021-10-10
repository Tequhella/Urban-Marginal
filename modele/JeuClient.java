package modele;

import outils.connexion.Connection;
import controleur.Controle;

public class JeuClient extends Jeu
{
	private Connection connection ;
	
	public JeuClient(Controle controle)
	{
		super.controle = controle ;
	}
	
	
	public void setConnection(Connection connection)
	{
		this.connection = connection ;
	}

	public void reception(Connection connection, Object info)
	{
		// TODO Auto-generated method stub
		
	}

	public void deconnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void envoi(Object info)
	{
		connection.envoi(info) ;
		super.envoi(connection, info) ;
	}
	
}
