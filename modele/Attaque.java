package modele;

import controleur.Global;

public class Attaque extends Thread implements Global
{
	/*
	 * Liste propriétés.
	 */
	private Joueur 	   attaquant ;
	private JeuServeur jeuServeur ;
	
	// constructeur
	public Attaque(Joueur attaquant, JeuServeur jeuServeur)
	{
		this.attaquant  = attaquant ;
		this.jeuServeur = jeuServeur ;
		
		super.start() ;
	}
	
	/* 
	 * Méthode run : se charge de lancer une procédure en parallèle.
	 */
	public void run()
	{
		Boule laboule   = attaquant.getBoule() ;
		int orientation = attaquant.getOrientation() ;
		laboule.getLabel().getJLabel().setVisible(true) ;
		
		do
		{
			if (orientation == GAUCHE) /*--->*/ laboule.setPosX(laboule.getPosX() - LEPAS) ;
			else /*------------------------->*/ laboule.setPosX(laboule.getPosX() + LEPAS) ;
			
			laboule.getLabel().getJLabel().setBounds
										   (
												   laboule.getPosX(),
												   laboule.getPosY(),
												   L_BOULE,
												   H_BOULE
										   ) ;
			pause(10, 0) ;
			jeuServeur.envoi(laboule.getLabel()) ;
		}
		while (laboule.getPosX() > 0 && laboule.getPosX() < L_ARENE) ;
		
		laboule.getLabel().getJLabel().setVisible(false) ;
		jeuServeur.envoi(laboule.getLabel()) ;
	}
	
	/*
	 * Méthode pause :
	 */
	private void pause(long milli, int nano)
	{
		try
		{
			Thread.sleep(milli, nano) ;
		}
		catch (InterruptedException e)
		{
			e.printStackTrace() ;
		}
	}
	
}
