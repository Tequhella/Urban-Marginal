package modele;

import outils.connexion.Connection;
import controleur.Controle;
import controleur.Global;

import java.util.ArrayList;
import java.util.Hashtable;



public class JeuServeur extends Jeu implements Global
{
	/*
	 * Liste propri�t�es
	 */
	private ArrayList<Mur> lesMurs 					 = new ArrayList<Mur>() ;
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	// constructeur
	public JeuServeur(Controle controle)
	{
		super.controle = controle;
		Label.setNbLabel(0) ;
	}
	
	/*
	 * M�thode constructionMurs : cr�e les murs et les places
	 * dans une collection pour �tre r�utilis�s.
	 */
	public void constructionMurs()
	{
		for (int i = 0; i < NBMURS; i++)
		{
			lesMurs.add(new Mur()) ;
			super.controle.evenementModele(this, "ajout mur", lesMurs.get(i).getLabel().getJLabel()) ;
		}
	}
	
	/*
	 * M�thode setConnection : enregistre les joueurs et donne
	 * en instructions l'envoi du panel de murs.
	 */
	public void setConnection(Connection connection)
	{
		lesJoueurs.put(connection, new Joueur()) ;
		controle.evenementModele(this, "envoi panel murs", connection);
		
	}

	/*
	 * M�thode r�ception : re�oit toutes les infos envoy�es
	 * par le Client.
	 */
	public void reception(Connection connection, Object info)
	{
		String[] infos = ((String) info).split(SEPARE) ;
		
		switch (Integer.parseInt(infos[0]))
		{
			case PSEUDO:
				System.out.println("Pseudo : " + infos[1] + "Perso : " + infos[2]);
				lesJoueurs.get(connection).initPerso(infos[1],
													 Integer.parseInt(infos[2]),
													 lesJoueurs,
													 lesMurs);
				break;
		}
	}
	
	/*
	 * M�thode deconnection.
	 */
	public void deconnection(Connection connection)
	{
		// TODO Auto-generated method stub
		
	}

}
