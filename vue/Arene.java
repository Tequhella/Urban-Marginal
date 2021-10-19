package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Global;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Arene extends JFrame implements Global
{
	/*
	 * Liste propri�t�s.
	 */
	private JPanel contentPane;
	private JPanel jpnMurs ;
	private JTextField txtSaisie;
	
	
	/*
	 * M�thode ajoutMur : permet d'ajouter un mur dans
	 * le jPanel de mur de la fen�tre.
	 */
	public void ajoutMur(JLabel mur)
	{
		jpnMurs.add(mur) ;
		jpnMurs.repaint() ;
	}
	
	/*
	 * M�thode ajoutPanelMurs : permet de r�ceptionner c�t�
	 * client les murs ajout�s du serveur.
	 */
	public void ajoutPanelMurs(JPanel objet)
	{
		jpnMurs.add(objet) ;
		jpnMurs.repaint() ;
		contentPane.requestFocus() ;
		System.out.println(objet) ;
	}
	
	/*
	 * Getter getJpnMurs
	 */
	public JPanel getJpnMurs() 
	{
		return jpnMurs;
	}

	/**
	 * Create the frame.
	 */
	public Arene()
	{
		setTitle("Arene") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setBounds(100, 100, L_ARENE + 3 * MARGE, H_ARENE + H_CHAT) ;
		contentPane = new JPanel() ;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		contentPane.setLayout(null) ;
		setContentPane(contentPane) ;
		
		JPanel jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE, H_ARENE) ;
		jpnJeu.setLayout(null) ;
		jpnJeu.setOpaque(false) ;
		contentPane.add(jpnJeu) ;
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE, H_ARENE) ;
		jpnMurs.setLayout(null) ;
		jpnMurs.setOpaque(false) ;
		contentPane.add(jpnMurs) ;
		
		JLabel lblFond = new JLabel("") ;
		lblFond.setBounds(0, 0, L_ARENE, H_ARENE) ;
		lblFond.setIcon(new ImageIcon(FONDARENE)) ;
		contentPane.add(lblFond) ;
		
		txtSaisie = new JTextField() ;
		txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE) ;
		contentPane.add(txtSaisie) ;
		txtSaisie.setColumns(10) ;
		
		JScrollPane jspChat = new JScrollPane() ;
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, H_ARENE + H_SAISIE, L_ARENE, H_CHAT - H_SAISIE - 7 * MARGE) ;
		contentPane.add(jspChat) ;
		
		JTextArea txtChat = new JTextArea() ;
		jspChat.setViewportView(txtChat) ;
	}

	
}
