package modele;

import connexion.Connection;
import controleur.Controle;

public class JeuClient extends Jeu
{
	public JeuClient(Controle controle)
	{
		super.controle = controle;
	}
	
	
	public void setConnection(Connection connection)
	{
		
	}

	public void reception(Connection connection, Object info)
	{
		// TODO Auto-generated method stub
		
	}

	public void deconnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
	}
	
}
