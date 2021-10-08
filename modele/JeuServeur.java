package modele;

import connexion.Connection;
import controleur.Controle;

public class JeuServeur extends Jeu
{
	public JeuServeur(Controle controle)
	{
		super.controle = controle;
	}
	
	
	public void setConnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
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
