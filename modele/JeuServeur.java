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
	private ArrayList<Joueur> lesJoueursDansLordre 	 = new ArrayList<Joueur>() ;
	
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
			this.lesMurs.add(new Mur()) ;
			super.controle.evenementModele(this, "ajout mur", lesMurs.get(i).getLabel().getJLabel()) ;
		}
	}
	
	/*
	 * M�thode setConnection : enregistre les joueurs et donne
	 * en instructions l'envoi du panel de murs.
	 */
	public void setConnection(Connection connection)
	{
		this.lesJoueurs.put(connection, new Joueur(this)) ;
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
			// partie cr�ation personnage + envoi
			case PSEUDO:
				this.controle.evenementModele(this, "envoi panel murs", connection) ;
				for (Joueur joueur : lesJoueursDansLordre)
				{
					super.envoi(connection, joueur.getLabel()) ;
					super.envoi(connection, joueur.getMessage()) ;
				}
				lesJoueurs.get(connection).initPerso(infos[1],
													 Integer.parseInt(infos[2]),
													 lesJoueurs,
													 lesMurs) ;
				lesJoueursDansLordre.add(lesJoueurs.get(connection)) ;
				
				String laPhrase = "*** " + infos[1] + " vient de se connecter ***" ;
				controle.evenementModele(this, "ajout phrase", laPhrase) ;
				
				break;
			case CHAT:
				laPhrase = lesJoueurs.get(connection).getPseudo() + ">" + infos[1] ;
				controle.evenementModele(this, "ajout phrase", laPhrase) ;
				
				break;
			case ACTION:
				lesJoueurs.get(connection).action(Integer.parseInt(infos[1]), lesJoueurs, lesMurs) ;
				
				break;
		}
	}
	
	/*
	 * M�thode nouveauLabelJeu :
	 */
	public void nouveauLabelJeu(Label label)
	{
		this.controle.evenementModele(this, "ajout joueur", label.getJLabel()) ;
	}
	
	/*
	 * M�thode envoi.
	 */
	public void envoi(Object info)
	{
		for (Connection connection : lesJoueurs.keySet())
		{
			super.envoi(connection, info) ;
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
