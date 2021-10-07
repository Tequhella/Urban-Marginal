package connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServeurSocket extends Thread
{
	/*
	 * Création des proprietes
	 */
	private Object 		 leRecepteur ;
	private ServerSocket serverSocket ;
	
	public ServeurSocket(Object leRecepteur, int port)
	{
		this.leRecepteur = leRecepteur ;
		
		try
		{
			this.serverSocket = new ServerSocket (port) ;
		}
		catch (IOException e)
		{
			System.out.println ("erreur grave création socket serveur : "+e) ;
			System.exit(0) ;
		}
		
		this.start() ;
	}
	
	public void run ()
	{
		Socket  socket ;		
		
		while (true)
		{
			try
			{
				System.out.println ("Le serveur attend.") ;
				socket = serverSocket.accept () ;
				System.out.println ("Un client s'est connecté.") ;
				new Connection(socket, leRecepteur) ;
			}
			catch (IOException e)
			{
				System.out.println ("erreur grave création socket serveur : "+e) ;
				System.exit (0) ;
			}
		}
		
		
	}
}
