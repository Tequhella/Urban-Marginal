package controleur;

import vue.EntreeJeu;
import javax.swing.JFrame;

public class Controle {
	
	//proprietes
	private EntreeJeu frmEntreeJeu ;
	
	//demarrage appli
	public static void main(String[] args)
	{
		new Controle() ;
		
	}
	
	//constructeur
	private Controle()
	{
		this.frmEntreeJeu = new EntreeJeu() ; //Création frame dans la mémoire.
		this.frmEntreeJeu.setVisible(true) ;  //Rend visible la frame.
		this.frmEntreeJeu = new EntreeJeu(this) ;
	}
	
	public void evenementVue (JFrame uneFrame, Object info)
	{
		if (uneFrame instanceof EntreeJeu)
		{
			evenementEntreeJeu(info);
		}
	}

	private void evenementEntreeJeu(Object info)
	{
		// TODO Auto-generated method stub
		
	}
}
