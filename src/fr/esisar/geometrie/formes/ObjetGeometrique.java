package fr.esisar.geometrie;

import java.awt.Graphics;

import fr.esisar.geometrie.formes.Point;

/**
 * Classe abstraite représentant un objet géométrique générique. Fournit des
 * méthodes abstraites que doivent implémenter les formes spécifiques.
 */
public abstract class ObjetGeometrique {
	private String name;
	
	private double vx;
	private double vy;

	/**
	 * Constructeur protégé pour initialiser le nom de l'objet géométrique.
	 * 
	 * @param name le nom de l'objet géométrique.
	 */
	protected ObjetGeometrique(String name,double vx, double vy) {
		this.name = name;
		this.vx = vx;
		this.vy = vy;
	}

	/**
	 * Retourne le nom de l'objet géométrique.
	 * 
	 * @return le nom de l'objet.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retourne la vitesse en x l'objet géométrique.
	 * 
	 * @return la vitesse en x de l'objet.
	 */
	public double getVX() {
		return vx;
	}
	
	/**
	 * Retourne la vitesse en y l'objet géométrique.
	 * 
	 * @return la vitesse en y de l'objet.
	 */
	public double getVY() {
		return vy;
	}
	
	/**
	 * Change la vitesse en x l'objet géométrique.
	 */
	public void setVX(double vx) {
		this.vx = vx;
	}
	
	/**
	 * Change la vitesse en y l'objet géométrique.
	 */
	public void setVY(double vy) {
		this.vy = vy;
	}

	/**
	 * Calcule et retourne l'aire de la forme géométrique.
	 * 
	 * @return l'aire de la forme.
	 */
	public abstract double aire();

	/**
	 * Calcule et retourne le périmètre de la forme géométrique.
	 * 
	 * @return le périmètre de la forme.
	 */
	public abstract double perimetre();

	/**
	 * Dessine la forme géométrique sur un contexte graphique donné.
	 * 
	 * @param g le contexte graphique où dessiner la forme.
	 */
	public abstract void dessiner(Graphics g);

	/**
	 * Détermine si un point donné est contenu dans la forme.
	 * 
	 * @param p le point à tester.
	 * @return true si le point est contenu dans la forme, false sinon.
	 */
	public abstract boolean contient(Point p);

	/**
	 * Déplace la forme selon les décalages spécifiés en x et y.
	 * 
	 * @param dx déplacement en x.
	 * @param dy déplacement en y.
	 */
	public abstract void deplacer(int dx, int dy);
	
	/**
	 * Renvoie le point principal de la forme ainsi que ses demi-tailles (moitié largeur, moitié hauteur).
	 * Ces informations sont utilisées pour les calculs de collision avec les bords du panneau.
	 * @return un tableau contenant [Point principal, demi-largeur, demi-hauteur]
	 */
	public abstract Object[] getBordure();
	
	/**
	 * Génère une représentation SVG de la forme.
	 * 
	 * @return une chaîne contenant la description SVG de la forme.
	 */
	public abstract String affichageSVG();

	/**
	 * Modifie la taille de la forme selon un facteur donné.
	 * 
	 * @param i facteur de changement de taille.
	 */
	public abstract void changerTaille(int i);

	/**
	 * Effectue une rotation de la forme d'un angle donné (en degrés).
	 * 
	 * @param theta angle de rotation en degrés.
	 */
	public abstract void rotate(int theta);
}
