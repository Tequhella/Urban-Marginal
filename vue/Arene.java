package vue;

import java.awt.BorderLayout ;
import java.awt.EventQueue ;
import java.awt.event.MouseAdapter ;
import java.awt.event.MouseEvent ;
import java.awt.event.KeyAdapter ;
import java.awt.event.KeyEvent ;
import java.io.IOException ;

import javax.swing.ImageIcon ;
import javax.swing.JButton ;
import javax.swing.JFrame ;
import javax.swing.JPanel ;
import javax.swing.border.EmptyBorder ;

import controleur.Controle;
import controleur.Global ;

import javax.swing.JLabel ;
import javax.swing.JTextField ;
import javax.swing.JScrollPane ;
import javax.swing.JTextArea ;

public class Arene extends JFrame implements Global
{
	/*
	 * Liste propriétés.
	 */
	private Controle   controle ;
	private JPanel 	   contentPane ;
	private JPanel 	   jpnMurs ;
	private JPanel 	   jpnJeu ;
	private JTextField txtSaisie ;
	private JTextArea  txtChat ;
	private boolean    client ;
	
	
	/*
	 * Méthode ajoutMur : permet d'ajouter un mur dans
	 * le jPanel de mur de la fenêtre.
	 */
	public void ajoutMur(JLabel mur)
	{
		jpnMurs.add(mur) ;
		jpnMurs.repaint() ;
	}
	
	/*
	 * Méthode ajoutPanelMurs : permet de réceptionner côté
	 * client les murs ajoutés du serveur.
	 */
	public void ajoutPanelMurs(JPanel objet)
	{
		jpnMurs.add(objet) ;
		jpnMurs.repaint() ;
		contentPane.requestFocus() ;
		System.out.println(objet) ;
	}
	
	/*
	 * Méthode ajoutJoueur : permet d'ajouter un joueur dans
	 * le jPanel de joueur de la fenêtre.
	 */
	public void ajoutJoueur(JLabel jLabel)
	{
		jpnJeu.add(jLabel) ;
		jpnJeu.repaint() ;
	}
	
	/*
	 * Méthode ajoutModifJoueur :
	 */
	public void ajoutModifJoueur(int num, JLabel unLabel)
	{
		System.out.println(unLabel) ;
		try
		{
			jpnJeu.remove(num) ;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			
		}
		jpnJeu.add(unLabel, num) ;
		jpnJeu.repaint() ;
	}
	
	/*
	 * Getter getJpnMurs
	 */
	public JPanel getJpnMurs() 
	{
		return jpnMurs;
	}
	
	
	/*********************
	 * Méthode Evènement *
	 ********************/
	
		/*
		 * Méthode txtSaisie_keyPressed : permet d'envoyer le message tapé.
		 */
	 
	private void txtSaisie_keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (txtSaisie.getText().equals(null) == false)
			{
				controle.evenementVue(this, CHAT + SEPARE + txtSaisie.getText()) ;
				txtSaisie.setText(null) ;
				contentPane.requestFocus() ;
			}
		}
	}
	
		/*
		 * Méthode contentPane_keyPressed : s'occupe de tester le type de touche
		 * et l'envoi au serveur.
		 */
	
	private void contentPane_keyPressed(KeyEvent e)
	{
		int valeur = -1 ;
		
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_SPACE: valeur = TIRE	; break ;
			case KeyEvent.VK_LEFT : valeur = GAUCHE ; break ;
			case KeyEvent.VK_RIGHT: valeur = DROITE ; break ;
			case KeyEvent.VK_DOWN : valeur = BAS  	; break ;
			case KeyEvent.VK_UP	  : valeur = HAUT 	; break ;
		}
		
		if (valeur != -1)
		{
			controle.evenementVue(this, ACTION + SEPARE + valeur) ;
		}
	}
	
	/*
	 * Méthode ajoutChat : ajoute une phrase dans txtChat.
	 */
	public void ajoutChat(String unePhrase)
	{
		txtChat.setText(unePhrase + "\r\n" + txtChat.getText()) ;
	}
	
	/*
	 * Méthode remplaceChat.
	 */
	public void remplaceChat(String str)
	{
		txtChat.setText(str) ;
	}
	
	/*
	 * Getter txtChat.
	 */
	public String getTxtChat()
	{
		return txtChat.getText() ;
	}
	
	/**
	 * Create the frame.
	 */
	public Arene(String typeJeu, Controle controle)
	{
		this.controle = controle ;
		client = (typeJeu.equals("client")) ;
		
		setTitle("Arene") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setBounds(100, 100, L_ARENE + 3 * MARGE, H_ARENE + H_CHAT) ;
		contentPane = new JPanel() ;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		contentPane.setLayout(null) ;
		setContentPane(contentPane) ;
		
		if (client == true)
		{
			contentPane.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					contentPane_keyPressed(e);
				}
			});
		}
		
		
		jpnJeu = new JPanel();
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
		
		if (client == true)
		{
			txtSaisie = new JTextField() ;
			txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE) ;
			contentPane.add(txtSaisie) ;
			txtSaisie.setColumns(10) ;
			
			txtSaisie.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					txtSaisie_keyPressed(e);
				}
			}) ;
		}
		
		JScrollPane jspChat = new JScrollPane() ;
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, H_ARENE + H_SAISIE, L_ARENE, H_CHAT - H_SAISIE - 7 * MARGE) ;
		contentPane.add(jspChat) ;
		
		txtChat = new JTextArea() ;
		jspChat.setViewportView(txtChat) ;
	}

	
}
