package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global ;

public class Mur extends Objet implements Global
{
	// constructeur
	public Mur ()
	{
		this.posX = (int)Math.round(Math.random() * L_ARENE - L_MUR) ;
		this.posY = (int)Math.round(Math.random() * H_ARENE - H_MUR) ;
		
		super.label = new Label(-1, new JLabel()) ;
		super.label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER) ;
		super.label.getJLabel().setVerticalAlignment(SwingConstants.CENTER) ;
		super.label.getJLabel().setBounds(posX, posY, L_MUR, H_MUR) ;
		super.label.getJLabel().setIcon(new ImageIcon(MUR)) ;
	}
}
