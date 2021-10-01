package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntreeJeu extends JFrame
{
	/*
	 * Création des composants de la fenêtre. liste proprietes
	 */
	private JPanel contentPane ; // Fenêtre.
	private JButton btnConnect ; // Bouton Connect.
	private JButton btnExit ;	// Bouton Sortie.
	private JTextField txtIp ;	// Champ de text pour insérer l'IP.
	private JLabel lblNewLabel ;
	private JLabel lblNewLabel_1 ;
	private JLabel lblNewLabel_2 ;
	
	
	/*
	public static void main(String[] args)
	{		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					EntreeJeu frame = new EntreeJeu(); //Mise en place de la fenêtre.
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	* clic sur le bouton Start pour lancer le serveur
	*/
	
	private Controle controle (Controle controle)
	{
		
	}
	
	/**
	* clic sur le bouton Start pour lancer le serveur
	*/
	
	private void btnStart_clic()
	{
		System.out.println("Using start button") ;
		evenementVue(this);
	}
	
	/**
	* clic sur le bouton Exit pour sortir de l'application
	*/
	
	private void btnExit_clic() 
	{
		System.out.println("Using exit button") ;
		System.exit(0) ;
	}
	
	/**
	* Create the frame.
	* @param controle 
	*/
	
	public EntreeJeu(Controle controle) 
	{
		btnStart_clic() ;
		btnExit_clic() ;
		setTitle("Urban Marginal") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setBounds(100, 100, 450, 190) ;
		contentPane = new JPanel() ;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)) ;
		setContentPane(contentPane) ;
		contentPane.setLayout(null) ;
		
		JButton btnStart = new JButton("Start") ;
		btnStart.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.out.println("using start button") ;
			}
		});
		btnStart.setBounds(291, 28, 89, 23) ;
		contentPane.add(btnStart) ;
		
		btnConnect = new JButton("Connect") ;
		btnConnect.setBounds(291, 78, 89, 23) ;
		contentPane.add(btnConnect) ;
		
		btnExit = new JButton("Exit") ;
		btnExit.setBounds(291, 112, 89, 23) ;
		contentPane.add(btnExit) ;
		
		txtIp = new JTextField() ;
		txtIp.setText("127.0.0.1") ;
		txtIp.setBounds(119, 79, 96, 20) ;
		contentPane.add(txtIp) ;
		txtIp.setColumns(10) ;
		
		lblNewLabel = new JLabel("IP server :") ;
		lblNewLabel.setBounds(45, 82, 64, 14) ;
		contentPane.add(lblNewLabel) ;
		
		lblNewLabel_1 = new JLabel("Start a server :") ;
		lblNewLabel_1.setBounds(45, 32, 105, 14) ;
		contentPane.add(lblNewLabel_1) ;
		
		lblNewLabel_2 = new JLabel("Connect an existing server :") ;
		lblNewLabel_2.setBounds(45, 57, 170, 14) ;
		contentPane.add(lblNewLabel_2) ;
	}
}