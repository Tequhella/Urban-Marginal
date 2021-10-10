package modele;

import outils.connexion.Connection;
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
		System.out.println(info);
	}

	public void deconnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
	}

}
