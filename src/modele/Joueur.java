package modele;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion des joueurs
 * @author emds
 *
 */
public class Joueur extends Objet implements Global {

	// constantes
	private static final int // vie de d�part pour tous les joueurs selon les classes
		MAXVIE_ROGUE = 200,
		MAXVIE_ARCHER = 150,
		MAXVIE_WARRIOR = 400,
		MAXVIE_NECROMANCER = 120,
		MAXVIE_SORCERER = 100;
	
	// propri�t�s
	private String pseudo ;
	private int numPerso ;
	private Label message ;
	private JeuServeur jeuServeur ;
	private int vie ; // vie restante du joueur
	private int orientation ; // tourn� vers la gauche (0) ou vers la droite (1)
	private int etape ; // num�ro d'�tape dans l'animation
	private Boule boule ; // la boule du joueur
	
	private int portee ; // port�e de tire
	private int degats ; // d�g�ts de l'attaque
	private int vitesse ; // vitesse de d�placement
	private int vitalitee ; // gain de r�g�naration � chaque attaque
	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur ;
		etape = 1 ;
		orientation = DROITE ;
	}
	
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo ;
	}

	/**
	 * @return the boule
	 */
	public Boule getBoule() {
		return boule ;
	}

	/**
	 * @return the orientation
	 */
	public int getOrientation() {
		return orientation ;
	}
	
	/**
	 * @return the numPerso
	 */
	public int getNumPerso() {
		return numPerso ;
	}
	
	/**
	 * @return the degats
	 */
	public int getDegats() {
		return degats ;
	}
	
	/**
	 * @return the portee
	 */
	public int getPortee() {
		return portee ;
	}

	/**
	 * Affiche le personnage et son message
	 * @param etat
	 * @param etape
	 */
	public void affiche(String etat, int etape) {
		label.getjLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
		if (etat.equals(MORT)) label.getjLabel().setIcon(new ImageIcon(CHEMINPERSOS+etat+".png"));
		else label.getjLabel().setIcon(new ImageIcon(PERSO+numPerso+etat+etape+"d"+orientation+".png"));
		message.getjLabel().setBounds(posX-10, posY+H_PERSO, L_PERSO+10, H_MESSAGE);
		message.getjLabel().setText(pseudo+" : "+vie);
		// envoi du personnage � tous les autres joueurs
		jeuServeur.envoi(label);
		jeuServeur.envoi(message);
	}
	
	/**
	 * Initialisation d'un joueur (pseudo et num�ro)
	 * @param pseudo
	 * @param numPerso
	 */
	public void initPerso(String pseudo, int numPerso, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo ;
		this.numPerso = numPerso ;
		switch(numPerso)
		{
			case ROGUE:
				vie       = MAXVIE_ROGUE ;
				degats	  = 30 ;
				portee    = 5 ;
				vitesse   = 10 ;
				vitalitee = 5 ;
				break ;
			case ARCHER: 
				vie   	  = MAXVIE_ARCHER ;
				degats	  = 25 ;
				portee	  = 7 ;
				vitesse	  = 2 ;
				vitalitee = 5 ;
				break ;
			case WARRIOR:
				vie		  = MAXVIE_WARRIOR ;
				degats	  = 70 ;
				portee	  = 3 ;
				vitesse   = 0 ;
				vitalitee = 10 ;
				break ;
			case NECROMANCER:
				vie		  = MAXVIE_NECROMANCER ;
				degats	  = 20 ;
				portee	  = 10 ;
				vitesse	  = 3 ;
				vitalitee = 5 ;
				break ;
			case SORCERER:
				vie		  = MAXVIE_SORCERER ;
				degats	  = 15 ;
				portee	  = 15 ;
				vitesse	  = 2 ;
				vitalitee = 20 ;
				break ;
				
		}
		// cr�ation de l'affichage du personnage
		label = new Label(Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel()+1);
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		jeuServeur.nouveauLabelJeu(label);
		// cr�ation de l'affichage du message sous le personnage
		message = new Label(Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel()+1);
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message);
		// calcul de la premi�re position al�atoire
		premierePosition(lesJoueurs, lesMurs) ;
		// affichage du personnage
		affiche(MARCHE, etape) ;
		// cr�ation de la boule
		boule = new Boule(jeuServeur) ;
		jeuServeur.envoi(boule.getLabel());
	}

	/**
	 * G�re le d�placement du personnage
	 * @param action
	 * @param position
	 * @param orientation
	 * @param lepas
	 * @param max
	 * @param lesJoueurs
	 * @param lesMurs
	 * @return
	 */
	private int deplace(int action, // action sollicit�e : aller vers la gauche, la droite
						int position, // position de d�part
						int orientation, // orientation de d�part
						int lepas, // valeur du d�placement (positif ou n�gatif)
						int max, // valeur � ne pas d�passer
						Hashtable<Connection, Joueur> lesJoueurs, // les autres joueurs (pour �viter les collisions)
						ArrayList<Mur> lesMurs) { // les murs (pour �viter les collisions)
		this.orientation = orientation ;
		int ancpos = position ;
		position = (lepas > 0) ? position + lepas + vitesse : position + lepas - vitesse ;
		position = Math.max(position, 0) ;
		position = Math.min(position,  max) ;
		if (action==GAUCHE || action==DROITE) {
			posX = position ;
		}else{
			posY = position ;
		}
		// controle s'il y a collision
		if (toucheJoueur(lesJoueurs) || toucheMur(lesMurs)) {
			position = ancpos ;
		}
		// passe � l'�tape suivante de l'animation de la marche
		etape = (etape % NBETATSMARCHE) + 1 ;
		return position ;
	}
	
	/**
	 * G�re une action re�ue du controleur (d�placement, tire de boule...)
	 * @param action
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	public void action(int action, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		// traite l'action
		switch (action) {
			case GAUCHE : posX = deplace(action, posX, GAUCHE, -LEPAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs) ;break ;
			case DROITE : posX = deplace(action, posX, DROITE,  LEPAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs) ;break ;
			case HAUT :   posY = deplace(action, posY, HAUT,   -LEPAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs) ;break ;
			case BAS :    posY = deplace(action, posY, BAS,     LEPAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs) ;break ;
			case TIRE :   
				if (!boule.getLabel().getjLabel().isVisible()) {
					switch (numPerso)
					{
						case 2: jeuServeur.envoi(FIGHT2); break ;
						case 1: 
						case 3: jeuServeur.envoi(FIGHT1); break ;
						case 4:
						case 5: jeuServeur.envoi(FIGHT3); break ;
					}
					boule.tireBoule(this, lesMurs, lesJoueurs); 
				}
				break;
		}
		// affiche le personnage � sa nouvelle position
		affiche(MARCHE, etape) ;
	}
	
	/**
	 * @return the message
	 */
	public Label getMessage() {
		return message;
	}
	
	/**
	 * Contr�le si le joueur chevauche un des autres joueurs
	 * @param lesJoueurs
	 * @return
	 */
	private boolean toucheJoueur(Hashtable<Connection, Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (!unJoueur.equals(this)) {
				if (toucheObjet(unJoueur)) {
					return true ;
				}
			}
		}
		return false ;
	}
	
	/**
	 * Contr�le si le joueur chevauche un des murs
	 * @param lesMurs
	 * @return
	 */
	private boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Mur unMur : lesMurs) {
			if (toucheObjet(unMur)) {
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	private void premierePosition(Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		label.getjLabel().setBounds(0, 0, L_PERSO, H_PERSO);
		do {
			posX = (int) Math.round(Math.random() * (L_ARENE - L_PERSO)) ;
			posY = (int) Math.round(Math.random() * (H_ARENE - H_PERSO - H_MESSAGE)) ;
		}while(toucheJoueur(lesJoueurs)||toucheMur(lesMurs)) ;
	}
	
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
		switch(numPerso)
		{
			case ROGUE:
				vie += vitalitee;
				if (vie > MAXVIE_ROGUE) vie -= vie - MAXVIE_ROGUE ;
				break ;
			case ARCHER: 
				vie += vitalitee;
				if (vie > MAXVIE_ARCHER) vie -= vie - MAXVIE_ARCHER ;
				break ;
			case WARRIOR:
				vie += vitalitee;
				if (vie > MAXVIE_WARRIOR) vie -= vie - MAXVIE_WARRIOR ;
				break ;
			case NECROMANCER:
				vie += vitalitee;
				if (vie > MAXVIE_NECROMANCER) vie -= vie - MAXVIE_NECROMANCER ;
				break ;
			case SORCERER:
				vie += vitalitee;
				if (vie > MAXVIE_SORCERER) vie -= vie - MAXVIE_SORCERER ;
				break ;
				
		}
	}
	
	/**
	 * Perte de points de vie apr�s avoir �t� touch� 
	 */
	public void perteVie(int degats) {
		vie -= degats ;
		if (vie < 0) vie -= vie ;
	}
	
	/**
	 * vrai si la vie est � 0
	 * @return
	 */
	public boolean estMort() {
		return (vie<=0);
	}
	
	/**
	 * Le joueur est mort et disparait
	 */
	public void departJoueur() {
		if (!(label==null)) {
			label.getjLabel().setVisible(false);
			message.getjLabel().setVisible(false);
			boule.getLabel().getjLabel().setVisible(false);		
			jeuServeur.envoi(label);
			jeuServeur.envoi(message);
			jeuServeur.envoi(boule.getLabel());
		}
	}
	
}
