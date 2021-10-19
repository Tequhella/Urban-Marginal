package modele;

public abstract class Objet
{
	protected int posX, posY ;
	protected Label label ;
	
	/**
	* contrôle si l'objet actuel touche l'objet passé en paramètre
	* @param objet
	* @return vrai si les 2 objets se touchent
	*/
	public boolean toucheObjet (Objet objet)
	{
		if (objet.label==null)
		{
			return false ;
		}
		else
		{
			if (objet.label.getJLabel()==null)
			{
				return false ;
			}
			else
			{
				int l_obj  = objet.label.getJLabel().getWidth() ;
				int h_obj  = objet.label.getJLabel().getHeight() ;
				int l_this = this.label.getJLabel().getWidth() ;
				int h_this = this.label.getJLabel().getHeight() ;
				
				return (!((this.posX + l_this < objet.posX ||
						   this.posX > objet.posX + l_obj) || 
						  (this.posY + h_this < objet.posY ||
						   this.posY > objet.posY + h_obj))) ;
			}
		}
	}
	
	/***************************************
	 * Les setter pour posX, posY et label.*
	 ***************************************/
	public void setPosX(int posX)
	{
		this.posX = posX ;
	}
	
	public void setPosY(int posY)
	{
		this.posY = posY ;
	}
	
	public void setLabel(Label label)
	{
		this.label = label;
	}
	
	
	/***************************************
	 * Les getter pour posX, posY et label.*
	 ***************************************/
	public int getPosX()
	{
		return this.posX ;
	}
	
	public int getPosY()
	{
		return this.posY ;
	}
	
	public Label getLabel()
	{
		return this.label ;
	}
}
