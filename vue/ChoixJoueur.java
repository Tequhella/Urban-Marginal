package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class ChoixJoueur extends JFrame implements Global
{

	private JPanel 	   contentPane ;
	private JTextField txtPseudo ;
	private JLabel     lblPersonnage ;
	private Controle   controle ;
	
	int numPerso = 1;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixJoueur frame = new ChoixJoueur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	* clic sur le bouton precedent
	*/
	
	private void lblPrecedent_clic()
	{
		if (numPerso == 1) /*--->*/ numPerso += 2;
		else /*--->*/ numPerso--;
		
		affichePerso() ;
	}
	
	/**
	* clic sur le bouton suivant
	*/
	
	private void lblSuivant_clic()
	{
		if (numPerso == NBPERSOS) /*--->*/ numPerso -= 2;
		else /*--->*/ numPerso++;
		
		affichePerso() ;
	}
	
	/**
	* clic sur le bouton go pour lancer
	*/
	
	private void lblGo_clic()
	{
		System.out.println("Clic sur le bouton Go") ;
		if (txtPseudo.getText().equals(""))
		{
			System.out.println("Champ de texte vide") ;
			JOptionPane.showMessageDialog(null, "Le pseudo est obligatoire") ;
			txtPseudo.requestFocus() ;
		}
		else
		{
			System.out.println("Envoi des infos au controleur") ;
			controle.evenementVue(this, PSEUDO + SEPARE + txtPseudo.getText() + SEPARE + numPerso) ;
		}
	}
	
	/**
	* sortie de la souris d'une zone
	* changement de forme du curseur : normal
	*/
	
	private void souris_normale()
	{
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)) ;
	}
	
	/**
	* survol de la souris sur une zone
	* changement de forme du curseur : main
	*/
	
	private void souris_doigt()
	{
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
	}
	
	private void affichePerso()
	{
		lblPersonnage.setIcon (new ImageIcon(CHEMINPERSOS + PERSO + numPerso + MARCHE + 1 + 'd' + DROITE + EXTIMAGE)) ;
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle)
	{
		setTitle("Choice") ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setBounds(100, 100, 416, 313) ;
		contentPane = new JPanel() ;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		setContentPane(contentPane) ;
		contentPane.setLayout(null) ;
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.setBounds(60, 145, 45, 45) ;
		lblPrecedent.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				lblPrecedent_clic() ;
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				souris_doigt() ;
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				souris_normale() ;
			}
		}) ;
		contentPane.add(lblPrecedent) ;
		
		JLabel lblSuivant = new JLabel("") ;
		lblSuivant.setBounds(290, 145, 45, 45) ;
		lblSuivant.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				lblSuivant_clic() ;
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				souris_doigt() ;
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				souris_normale() ;
			}
		}) ;
		contentPane.add(lblSuivant);
		
		JLabel lblGo = new JLabel("");
		lblGo.setBounds(310, 197, 65, 65) ;
		lblGo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				lblGo_clic() ;
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				souris_doigt() ;
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				souris_normale() ;
			}
		}) ;
		contentPane.add(lblGo);
		
		txtPseudo = new JTextField() ;
		txtPseudo.setSize(new Dimension(118, 18)) ;
		txtPseudo.setLocation(new Point(144, 247)) ;
		txtPseudo.setColumns(10) ;
		contentPane.add(txtPseudo) ;
		
		lblPersonnage = new JLabel("") ;
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER) ;
		lblPersonnage.setSize(new Dimension(120, 120)) ;
		lblPersonnage.setLocation(new Point(141, 115)) ;
		contentPane.add(lblPersonnage) ;
		
		JLabel lblFond = new JLabel("") ;
		lblFond.setBounds(0, 0, 400, 275) ;
		lblFond.setIcon(new ImageIcon(FONDCHOIX)) ;
		contentPane.add(lblFond) ;
		
		txtPseudo.requestFocus() ;
		
		
		affichePerso() ;
		
		this.controle = controle;
	}
}
