package connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import controleur.Controle;

public class Connection extends Thread
{
	/*
	 * Création des proprietes
	 */
	private Object 			   leRecepteur ; // recepeteur
	private ObjectInputStream  in ; 		 // canal entrant
	private ObjectOutputStream out; 		 // canal sortant
	
	/*
	 * Création des constructeurs
	 */
	public Connection(Socket socket, Object leRecepteur)
	{
		this.leRecepteur = leRecepteur;
		
		try 
		{
			this.out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(IOException e) 
		{
			System.out.println("erreur création canal sortie "+e) ;
			System.exit(0) ;
		}
		
		try
		{
			this.in = new ObjectInputStream(socket.getInputStream());
		} 
		catch(IOException e)
		{
			System.out.println("erreur création canal entree "+e) ;
			System.exit(0) ;
		}
		
		this.start();
	}
	
	public void run()
	{
		Object  reception ;
		boolean inOk = true ;
		
		while(inOk)
		{
			
			try 
			{
				reception = in.readObject() ;
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("erreur classe non trouvée : "+e) ;
				System.exit (0) ;
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, "L'ordinateur distant s'est déconnecté.") ;
				inOk = false;
				try
				{
					in.close();
				}
				catch (IOException e1)
				{
					System.out.println("la fermeture du canal d'entrée a échoué : "+e);
				}
			}
		}
	}
	
	public void envoi(Object unObjet)
	{
		try
		{
			out.writeObject(unObjet);
			out.flush();
		}
		catch (IOException e)
		{
			System.out.println("erreur avec l'objet d'envoi : "+e);
		}
		
	}
}
