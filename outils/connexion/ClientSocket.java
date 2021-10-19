package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientSocket
{
	boolean connexionOk ; // V�rifie si la connection se fait.
	
	// constructeur
	public ClientSocket(String ip, int port, Object leRecepteur)
	{
		connexionOk = false ;
		
		try
		{
			Socket socket = new Socket(ip, port) ;
			System.out.println("connexion serveur r�ussi") ;
			connexionOk = true;
			new Connection (socket, leRecepteur);
		}
		catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(null, "serveur non disponible") ;
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "ip incorrect") ;
		}
	}
	
	// Getter de connexionOk.
	public boolean isConnexionOk()
	{
		return connexionOk;
	}
}
