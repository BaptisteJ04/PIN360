package fr.esisar.geometrie.formes;

import java.awt.Color;

/**
 * Classe représentant un point dans un plan avec un nom, une position
 * (abscisse, ordonnée) et une couleur.
 */
public class Point {
	private String nom;
	private double abscisse;
	private double ordonnee;
	private Color couleur;

	/**
	 * Constructeur complet du point avec nom, couleur, et coordonnées.
	 * 
	 * @param nom      Nom du point.
	 * @param couleur  Couleur du point.
	 * @param abscisse Coordonnée x du point.
	 * @param ordonnee Coordonnée y du point.
	 */
	public Point(String nom, Color couleur, double abscisse, double ordonnee) {
		super();
		this.nom = nom;
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
		this.couleur = couleur;
	}

	/**
	 * Constructeur du point avec nom et coordonnées. La couleur par défaut est
	 * noire.
	 * 
	 * @param nom      Nom du point.
	 * @param abscisse Coordonnée x du point.
	 * @param ordonnee Coordonnée y du point.
	 */
	public Point(String nom, double abscisse, double ordonnee) {
		super();
		this.nom = nom;
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
		this.couleur = Color.BLACK;
	}

	/**
	 * Affiche le point sous la forme "nom(x, y)".
	 */
	public void afficherPoint() {
		String affichage = String.format("%s(%.2f, %.2f)", nom, abscisse, ordonnee);
		System.out.println(affichage);
	}

	/**
	 * Représentation textuelle du point.
	 * 
	 * @return Une chaîne décrivant le point.
	 */
	@Override
	public String toString() {
		return String.format("%s(%.2f, %.2f)\n", nom, abscisse, ordonnee);
	}

	/**
	 * Déplace le point de dx sur l'axe x et dy sur l'axe y.
	 * 
	 * @param dx déplacement sur l'axe x.
	 * @param dy déplacement sur l'axe y.
	 */
	public void deplacer(double dx, double dy) {
		abscisse += dx;
		ordonnee += dy;
	}

	/**
	 * Fait tourner le point autour de l'origine (0,0) d'un angle theta en degrés.
	 * Le sens positif est antihoraire.
	 * 
	 * @param theta Angle de rotation en degrés.
	 */
	public void rotate(double theta) {
		// Conversion en radians
		double radians = theta * (Math.PI / 180);

		// Calcul temporaire pour ne pas écraser abscisse avant ordonnee
		double xTemp = abscisse * Math.cos(radians) - ordonnee * Math.sin(radians);
		double yTemp = abscisse * Math.sin(radians) + ordonnee * Math.cos(radians);

		abscisse = xTemp;
		ordonnee = yTemp;
	}

	// Getters et setters

	public String getNom() {
		return nom;
	}

	public double getAbscisse() {
		return abscisse;
	}

	public double getOrdonnee() {
		return ordonnee;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAbscisse(double abscisse) {
		this.abscisse = abscisse;
	}

	public void setOrdonnee(double ordonnee) {
		this.ordonnee = ordonnee;
	}
}
