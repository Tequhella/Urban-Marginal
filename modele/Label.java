package modele;

import java.io.Serializable;

import javax.swing.JLabel;

public class Label implements Serializable
{
	/*
	 * Liste propriétés
	 */
	private static int nbLabel ;
	private int numLabel ;
	private JLabel jLabel ;
	
	// constructeur
	public Label(int numLabel, JLabel jLabel)
	{
		this.numLabel = numLabel ;
		this.jLabel   = jLabel ;
	}
	
	
	/****************************************************
	 * Création setter pour nbLabel, numLabel et jLabel.*
	 ****************************************************/
	public static void setNbLabel(int nbLabel)
	{
		Label.nbLabel = nbLabel ;
	}
	
	public void setNumLabel(int numLabel)
	{
		this.numLabel = numLabel ;
	}
	
	public void setJLabel(JLabel jLabel)
	{
		this.jLabel = jLabel ;
	}
	
	/****************************************************
	 * Création getter pour nbLabel, numLabel et jLabel.*
	 ****************************************************/
	public int getNbLabel()
	{
		return this.nbLabel ;
	}
	
	public int getNumLabel()
	{
		return this.numLabel ;
	}
	
	public JLabel getJLabel()
	{
		return this.jLabel ;
	}
	
	
	
	
}
