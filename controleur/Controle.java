package controleur;

import javax.swing.JFrame;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
import connexion.ClientSocket;
import connexion.ServeurSocket;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;

public class Controle {
	
	//proprietes
	private EntreeJeu 	frmEntreeJeu ;
	private Arene	  	frmArene ;
	private ChoixJoueur frmChoixJoueur ;
	private Jeu 	  	leJeu ;
	
	//demarrage appli
	public static void main(String[] args)
	{
		new Controle() ;
	}
	
	//constructeur
	private Controle()
	{
		this.frmEntreeJeu = new EntreeJeu(this) ; //Création frame dans la mémoire.
		this.frmEntreeJeu.setVisible(true) ;	  //Rend visible la frame.
	}
	
	public void evenementVue(JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			evenementEntreeJeu(info) ;
		}
	}

	private void evenementEntreeJeu(Object info)
	{
		if ((String)info == "serveur")
		{
			new ServeurSocket(this, 6666) ;
			leJeu = new JeuServeur(this) ;
			frmEntreeJeu.dispose() ;
			frmArene = new Arene() ;
			frmArene.setVisible(true) ;
		}
		else
		{
			if (new ClientSocket((String)info, 6666, this).isConnexionOk())
			{
				leJeu = new JeuClient(this) ;
				frmEntreeJeu.dispose() ;
				frmArene = new Arene() ;
				frmChoixJoueur = new ChoixJoueur() ;
				frmChoixJoueur.setVisible(true) ;
				
				// frmArene.setVisible(true) ;
			}
		}
	}
}
