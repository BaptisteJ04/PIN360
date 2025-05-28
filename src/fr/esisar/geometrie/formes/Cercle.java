package fr.esisar.geometrie.formes;

import java.awt.Graphics;
import fr.esisar.geometrie.ObjetGeometrique;

/**
 * Classe représentant un cercle, héritant d'ObjetGeometrique.
 */
public class Cercle extends ObjetGeometrique {

	private double rayon;
	private Point centre;

	/**
	 * Constructeur d'un cercle.
	 * 
	 * @param point le centre du cercle.
	 * @param rayon le rayon du cercle.
	 * @param nom   le nom du cercle.
	 */
	public Cercle(Point point, double rayon, String nom) {
		super(nom,0.2,0.2);
		this.centre = point;
		this.rayon = rayon;
	}
	
	/**
	 * Constructeur d'un cercle.
	 * 
	 * @param point le centre du cercle.
	 * @param rayon le rayon du cercle.
	 * @param nom   le nom du cercle.
	 * @param vx   vitesse en x 
	 * @param vy   vitesse en y
	 */
	public Cercle(Point point, double rayon, String nom,double vx, double vy) {
		super(nom,vx,vy);
		this.centre = point;
		this.rayon = rayon;
	}

	/**
	 * Dessine le cercle sur un composant graphique.
	 * 
	 * @param g le contexte graphique.
	 */
	public void dessiner(Graphics g) {
		g.setColor(centre.getCouleur());
		g.drawOval((int) (centre.getAbscisse() - rayon), (int) (centre.getOrdonnee() - rayon), (int) (2 * rayon),
				(int) (2 * rayon));
	}

	/**
	 * Vérifie si un point donné est à l'intérieur du cercle.
	 * 
	 * @param p le point à tester.
	 * @return true si le point est dans le cercle, false sinon.
	 */
	public boolean contient(Point p) {
		int dx = (int) (p.getAbscisse() - centre.getAbscisse());
		int dy = (int) (p.getOrdonnee() - centre.getOrdonnee());
		return dx * dx + dy * dy <= rayon * rayon;
	}

	/**
	 * Déplace le cercle de dx en abscisse et dy en ordonnée.
	 * 
	 * @param dx déplacement horizontal.
	 * @param dy déplacement vertical.
	 */
	public void deplacer(int dx, int dy) {
		centre.deplacer(dx, dy);
	}

	/**
	 * Rotation du cercle (non applicable, méthode vide).
	 * 
	 * @param theta angle de rotation en degrés.
	 */
	@Override
	public void rotate(int theta) {
		return; // un cercle ne change pas à la rotation
	}

	/**
	 * Change la taille du cercle en modifiant son rayon. Le rayon minimal est fixé
	 * à 5 pour éviter les valeurs négatives.
	 * 
	 * @param delta valeur à ajouter au rayon.
	 */
	public void changerTaille(int delta) {
		rayon = Math.max(5, rayon + delta);
	}

	/**
	 * Calcule le périmètre (circonférence) du cercle.
	 * 
	 * @return périmètre du cercle.
	 */
	@Override
	public double perimetre() {
		return 2 * Math.PI * rayon;
	}

	/**
	 * Calcule l'aire du cercle.
	 * 
	 * @return aire du cercle.
	 */
	@Override
	public double aire() {
		return Math.PI * rayon * rayon;
	}
	
	@Override
	public Object[] getBordure() {
	    return new Object[] { centre, rayon, rayon };
	}

	/**
	 * Génère la représentation SVG du cercle.
	 * 
	 * @return chaîne de caractères contenant la balise SVG.
	 */
	public String affichageSVG() {
		return String.format(
				"<circle class=\"anim\" cx=\"%.2f\" cy=\"%.2f\" r=\"%.2f\" stroke=\"%s\" stroke-width=\"10\" />",
				centre.getAbscisse(), centre.getOrdonnee(), rayon, centre.getNom());
	}

	/**
	 * Représentation textuelle du cercle.
	 * 
	 * @return chaîne descriptive du cercle.
	 */
	@Override
	public String toString() {
		return "Cercle de rayon : " + rayon + "\nMilieu : " + centre.toString();
	}

	/**
	 * Getter du centre du cercle.
	 * 
	 * @return le point centre.
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Getter du rayon du cercle.
	 * 
	 * @return le rayon.
	 */
	public double getRayon() {
		return rayon;
	}
}
