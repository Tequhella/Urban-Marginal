package controleur;

public interface Global 
{
	/**
	 * Création des constantes globales.
	 */
	
	/**************
	 * Partie int *
	 **************/
	public static final Integer PORT		= 6666 ;
	
	// partie personnage
	public static final int
		GAUCHE 			= 0,
		DROITE 			= 1,
		HAUT			= 2,
		BAS				= 3,
		TIRE			= 4,
		ACTION			= 2,
		NBPERSOS 		= 3,
		H_PERSO 		= 44,
		L_PERSO 		= 39,
		NBETATSMARCHE	= 4,
		LEPAS			= 10,
		PSEUDO			= 0 ;
	
	// partie mur
	public static final int
		NBMURS			= 20,  // nombre de mur
		H_MUR 			= 35,  // hauteur de l'image
		L_MUR 			= 34,  // largeur de l'image
		MARGE			= 5 ;  // écart entre les différents objets
	
	// partie arène
	public static final int
		H_ARENE			= 600,
		L_ARENE			= 800 ;
	
	// partie boule
	public static final int
		L_BOULE 		= 17,
		H_BOULE			= 17 ;
	
	// partie chat
	public static final int
		CHAT			= 1,
		H_CHAT			= 200,
		H_SAISIE		= 25,
		H_MESSAGE		= 8 ;
	
	
	/*****************
	 * Partie string *
	 *****************/
	public static final String
		SEPARATOR   	= "//",
		CHEMIN 	   		= "media" + SEPARATOR,					// "media//"
		CHEMINFONDS 	= CHEMIN + "fonds" + SEPARATOR,			// "media//fonds//"
		FONDCHOIX   	= CHEMINFONDS + "fondchoix.jpg",		// "media//fonds//fondchoix.jpg"
		CHEMINPERSOS 	= CHEMIN + "personnages" + SEPARATOR,	// "media//personnages//"
		FONDARENE		= CHEMINFONDS + "fondarene.jpg",		// "media//fonds//fondarene.jpg"
		CHEMINMURS		= CHEMIN + "murs" + SEPARATOR,			// "media//murs//"
		MUR				= CHEMINMURS + "mur.gif",				// "media//murs//mur.gif"
		CHEMINBOULES	= CHEMIN + "boules" + SEPARATOR,		// "media//boules//"
		BOULE			= CHEMINBOULES + "boule.gif",			// "media//boules//boule.gif"
		PERSO			= "perso",
		EXTIMAGE 		= ".gif",
		MARCHE 			= "marche",
		BLESEE 			= "touche",
		MORT 			= "mort",
		SEPARE			= "~" ;
	
	
}
