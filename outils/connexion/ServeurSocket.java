package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServeurSocket extends Thread
{
	/*
	 * Cr�ation des proprietes
	 */
	private Object 		 leRecepteur ;
	private ServerSocket serverSocket ;
	
	// constructeur
	public ServeurSocket(Object leRecepteur, int port)
	{
		this.leRecepteur = leRecepteur ;
		
		try
		{
			this.serverSocket = new ServerSocket (port) ;
		}
		catch (IOException e)
		{
			System.out.println("erreur grave cr�ation socket serveur : "+e) ;
			System.exit(0) ;
		}
		
		this.start() ;
	}
	
	/*
	 * M�thode run permettant d'�tablir une attente de connection
	 * d'un ordinateur distant.
	 */
	public void run()
	{
		Socket  socket ;		
		
		while (true)
		{
			try
			{
				System.out.println("Le serveur attend.") ;
				socket = serverSocket.accept() ;
				System.out.println("Un client s'est connect�.") ;
				new Connection(socket, leRecepteur) ;
			}
			catch (IOException e)
			{
				System.out.println("erreur grave cr�ation socket serveur : "+e) ;
				System.exit (0) ;
			}
		}
		
		
	}
}
