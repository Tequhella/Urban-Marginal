package controleur;

import vue.EntreeJeu;
import javax.swing.JFrame;

import connexion.ClientSocket;
import connexion.ServeurSocket;

public class Controle {
	
	//proprietes
	private EntreeJeu frmEntreeJeu ;
	
	//demarrage appli
	public static void main (String[] args)
	{
		new Controle () ;
	}
	
	//constructeur
	private Controle ()
	{
		this.frmEntreeJeu = new EntreeJeu (this) ; //Création frame dans la mémoire.
		this.frmEntreeJeu.setVisible (true) ;	  //Rend visible la frame.
	}
	
	public void evenementVue (JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			evenementEntreeJeu (info) ;
		}
	}

	private void evenementEntreeJeu (Object info)
	{
		if ((String)info == "serveur")
		{
			new ServeurSocket(this, 6666) ;
		}
		else
		{
			if (new ClientSocket((String)info, 6666, this).isConnexionOk())
			{
				
			}
		}
		
	}
}
