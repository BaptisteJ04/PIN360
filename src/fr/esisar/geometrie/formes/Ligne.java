package fr.esisar.geometrie.formes;

import java.awt.Graphics;
import java.awt.Color;

import fr.esisar.geometrie.ObjetGeometrique;

/**
 * Classe représentant une ligne définie par deux points (début et fin). Hérite
 * de {@link ObjetGeometrique}.
 */
public class Ligne extends ObjetGeometrique {
	/**
	 * Point de départ de la ligne.
	 */
	private Point debut;

	/**
	 * Point de fin de la ligne.
	 */
	private Point fin;

	/**
	 * Constructeur de la ligne.
	 *
	 * @param debut Le point de départ de la ligne.
	 * @param fin   Le point de fin de la ligne.
	 * @param nom   Le nom de la ligne.
	 */
	public Ligne(Point debut, Point fin, String nom) {
		super(nom,0.2,0.2);
		this.debut = debut;
		this.fin = fin;
	}

	/**
	 * Calcule le périmètre de la ligne, ici la distance entre les deux points.
	 *
	 * @return La longueur de la ligne.
	 */
	@Override
	public double perimetre() {
		return distance(debut, fin);
	}

	/**
	 * L'aire d'une ligne est toujours nulle.
	 *
	 * @return 0.
	 */
	@Override
	public double aire() {
		return 0;
	}

	@Override
	public Object[] getBordure() {
		Point milieu = new Point("NOIR", Color.BLUE, (debut.getAbscisse() + fin.getAbscisse()) / 2,
				(debut.getOrdonnee() + fin.getOrdonnee()) / 2);
		double dx = Math.abs(debut.getAbscisse() - fin.getAbscisse()) / 2.0;
		double dy = Math.abs(debut.getOrdonnee() - fin.getOrdonnee()) / 2.0;
		return new Object[] { milieu, dx, dy };
	}

	/**
	 * Retourne la représentation SVG de la ligne.
	 *
	 * @return Une chaîne formatée décrivant la ligne en SVG.
	 */
	@Override
	public String affichageSVG() {
		return String.format(
				"<line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"10\"/>",
				debut.getAbscisse(), debut.getOrdonnee(), fin.getAbscisse(), fin.getOrdonnee(), debut.getNom());
	}

	/**
	 * Représentation textuelle de la ligne.
	 *
	 * @return Une chaîne décrivant la ligne entre le point de début et le point de
	 *         fin.
	 */
	@Override
	public String toString() {
		return "Ligne de " + debut + " à " + fin;
	}

	/**
	 * Dessine la ligne sur un contexte graphique donné.
	 *
	 * @param g Le contexte graphique sur lequel dessiner.
	 */
	public void dessiner(Graphics g) {
		g.setColor(debut.getCouleur());
		g.drawLine((int) debut.getAbscisse(), (int) debut.getOrdonnee(), (int) fin.getAbscisse(),
				(int) fin.getOrdonnee());
	}

	/**
	 * Indique si un point est proche de la ligne (segment).
	 *
	 * @param p Le point à tester.
	 * @return true si le point est à moins de 5 unités du segment, false sinon.
	 */
	public boolean contient(Point p) {
		double distTotal = perimetre();
		double dist1 = distance(p, debut);
		double dist2 = distance(p, fin);
		// Tolérance : point proche du segment
		return Math.abs((dist1 + dist2) - distTotal) < 5;
	}

	/**
	 * Déplace la ligne en décalant ses points de dx en x et dy en y.
	 *
	 * @param dx Le déplacement horizontal.
	 * @param dy Le déplacement vertical.
	 */
	public void deplacer(int dx, int dy) {
		debut.deplacer(dx, dy);
		fin.deplacer(dx, dy);
	}

	/**
	 * Effectue une rotation de la ligne autour du point de départ.
	 *
	 * @param theta L'angle de rotation en degrés.
	 */
	@Override
	public void rotate(int theta) {
		// Conversion en radians
		double radians = theta * (Math.PI / 180);

		// Formules de rotation autour de debut
		fin.setAbscisse((fin.getAbscisse() - debut.getAbscisse()) * Math.cos(radians)
				- (fin.getOrdonnee() - debut.getOrdonnee()) * Math.sin(radians) + debut.getAbscisse());
		fin.setOrdonnee((fin.getAbscisse() - debut.getAbscisse()) * Math.sin(radians)
				+ (fin.getOrdonnee() - debut.getOrdonnee()) * Math.cos(radians) + debut.getOrdonnee());
	}

	/**
	 * Change la taille de la ligne en la redimensionnant depuis son centre.
	 *
	 * @param facteur Le facteur d'agrandissement si supérieur à 0 ou de réduction
	 *                si inférieur à 0.
	 */
	public void changerTaille(int facteur) {

		// On trouve le centre
		double cx = (debut.getAbscisse() + fin.getAbscisse()) / 2;
		double cy = (debut.getOrdonnee() + fin.getOrdonnee()) / 2;

		// On agrandit à droite et à gauche du centre
		double dx1 = debut.getAbscisse() - cx;
		double dy1 = debut.getOrdonnee() - cy;
		double dx2 = fin.getAbscisse() - cx;
		double dy2 = fin.getOrdonnee() - cy;

		double scale = 1 + facteur * 0.05; // Coef d'agrandissement

		debut.setAbscisse(cx + dx1 * scale);
		debut.setOrdonnee(cy + dy1 * scale);
		fin.setAbscisse(cx + dx2 * scale);
		fin.setOrdonnee(cy + dy2 * scale);
	}

	/**
	 * Calcule la distance euclidienne entre deux points.
	 *
	 * @param a Le premier point.
	 * @param b Le second point.
	 * @return La distance entre a et b.
	 */
	private double distance(Point a, Point b) {
		return Math
				.sqrt(Math.pow(a.getAbscisse() - b.getAbscisse(), 2) + Math.pow(a.getOrdonnee() - b.getOrdonnee(), 2));
	}

	/**
	 * Retourne le point de départ de la ligne.
	 *
	 * @return Le point de début.
	 */
	public Point getDebut() {
		return debut;
	}

	/**
	 * Retourne le point de fin de la ligne.
	 *
	 * @return Le point de fin.
	 */
	public Point getFin() {
		return fin;
	}
}
