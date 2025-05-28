package fr.esisar.geometrie.formes;

import java.awt.Graphics;

import fr.esisar.geometrie.ObjetGeometrique;

/**
 * Classe représentant une ellipse, définie par un centre et deux rayons (X et
 * Y).
 */
public class Ellipse extends ObjetGeometrique {
	private Point centre;
	private double rayonX;
	private double rayonY;

	/**
	 * Constructeur d'une ellipse.
	 * 
	 * @param centre Le centre de l'ellipse.
	 * @param rayonX Le rayon horizontal (axe X).
	 * @param rayonY Le rayon vertical (axe Y).
	 * @param nom    Le nom de l'ellipse.
	 */
	public Ellipse(Point centre, double rayonX, double rayonY, String nom) {
		super(nom,0.2,0.2);
		this.centre = centre;
		this.rayonX = rayonX;
		this.rayonY = rayonY;
	}

	/**
	 * Calcule une approximation du périmètre de l'ellipse via la formule de
	 * Ramanujan.
	 * 
	 * @return Le périmètre approximatif.
	 */
	@Override
	public double perimetre() {
		// Approximation de Ramanujan
		return 2 * Math.PI * Math.sqrt((rayonX * rayonX + rayonY * rayonY) / 2.0);
	}

	/**
	 * Calcule l'aire de l'ellipse.
	 * 
	 * @return L'aire.
	 */
	@Override
	public double aire() {
		return Math.PI * rayonX * rayonY;
	}
	
	@Override
	public Object[] getBordure() {
	    return new Object[] { centre, rayonX / 2.0, rayonY / 2.0 };
	}

	/**
	 * Génère la représentation SVG de l'ellipse.
	 * 
	 * @return Chaîne de caractères SVG.
	 */
	@Override
	public String affichageSVG() {
		return String.format(
				"<ellipse cx=\"%.2f\" cy=\"%.2f\" rx=\"%.2f\" ry=\"%.2f\" stroke=\"%s\" stroke-width=\"10\" fill=\"none\"/>",
				centre.getAbscisse(), centre.getOrdonnee(), rayonX, rayonY, centre.getNom());
	}

	@Override
	public String toString() {
		return "Ellipse centre: " + centre + ", rayons: (" + rayonX + ", " + rayonY + ")";
	}

	/**
	 * Dessine l'ellipse sur le contexte graphique donné.
	 * 
	 * @param g Contexte graphique.
	 */
	public void dessiner(Graphics g) {
		g.setColor(centre.getCouleur());
		g.drawOval((int) (centre.getAbscisse() - rayonX), (int) (centre.getOrdonnee() - rayonY), (int) (2 * rayonX),
				(int) (2 * rayonY));
	}

	/**
	 * Vérifie si un point est à l'intérieur de l'ellipse.
	 * 
	 * @param p Point à tester.
	 * @return true si le point est dans l'ellipse, false sinon.
	 */
	public boolean contient(Point p) {
		double dx = (p.getAbscisse() - centre.getAbscisse()) / rayonX;
		double dy = (p.getOrdonnee() - centre.getOrdonnee()) / rayonY;
		return dx * dx + dy * dy <= 1;
	}

	/**
	 * Déplace l'ellipse en décalant son centre.
	 * 
	 * @param dx déplacement en X.
	 * @param dy déplacement en Y.
	 */
	public void deplacer(int dx, int dy) {
		centre.deplacer(dx, dy);
	}

	/**
	 * Effectue une rotation de l'ellipse de 90° en permutant ses rayons.
	 * 
	 * @param theta angle de rotation (en degrés, ici ignoré car rotation
	 *              simplifiée).
	 */
	@Override
	public void rotate(int theta) {
		double aux = rayonX;
		rayonX = rayonY;
		rayonY = aux;
	}

	/**
	 * Modifie la taille de l'ellipse en appliquant un facteur d'échelle.
	 * 
	 * @param facteur facteur d'agrandissement ou de réduction (entier).
	 */
	public void changerTaille(int facteur) {
		double scale = 1 + facteur * 0.05;
		rayonX = Math.max(10, rayonX * scale);
		rayonY = Math.max(5, rayonY * scale);
	}

	// Getters
	public Point getCentre() {
		return centre;
	}

	public double getRayonX() {
		return rayonX;
	}

	public double getRayonY() {
		return rayonY;
	}
}
