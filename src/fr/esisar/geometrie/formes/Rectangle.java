package fr.esisar.geometrie.formes;

import java.awt.Graphics;
import fr.esisar.geometrie.ObjetGeometrique;

/**
 * Classe représentant un rectangle défini par un point (coin supérieur gauche),
 * une largeur et une hauteur. Hérite de {@link ObjetGeometrique}.
 */
public class Rectangle extends ObjetGeometrique {
	private Point coin;
	private double largeur;
	private double hauteur;

	/**
	 * Constructeur du rectangle.
	 * 
	 * @param coin    Point représentant le coin supérieur gauche.
	 * @param largeur Largeur du rectangle.
	 * @param hauteur Hauteur du rectangle.
	 * @param nom     Nom du rectangle.
	 */
	public Rectangle(Point coin, double largeur, double hauteur, String nom) {
		super(nom,0.2,0.2);
		this.coin = coin;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	/**
	 * Calcule le périmètre du rectangle.
	 * 
	 * @return Le périmètre.
	 */
	@Override
	public double perimetre() {
		return 2 * (largeur + hauteur);
	}

	/**
	 * Calcule l'aire du rectangle.
	 * 
	 * @return L'aire.
	 */
	@Override
	public double aire() {
		return largeur * hauteur;
	}

	@Override
	public Object[] getBordure() {
	    return new Object[] { coin, largeur, hauteur };
	}
	
	/**
	 * Retourne la représentation SVG du rectangle.
	 * 
	 * @return Chaîne SVG.
	 */
	@Override
	public String affichageSVG() {
		return String.format(
				"<rect x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" height=\"%.2f\" stroke=\"%s\" stroke-width=\"10\" fill=\"none\"/>",
				coin.getAbscisse(), coin.getOrdonnee(), largeur, hauteur, coin.getNom());
	}

	/**
	 * Représentation textuelle du rectangle.
	 * 
	 * @return Chaîne descriptive.
	 */
	@Override
	public String toString() {
		return "Rectangle largeur: " + largeur + "\nhauteur: " + hauteur + "\ncoin supérieur gauche: " + coin;
	}

	/**
	 * Dessine le rectangle sur un contexte graphique.
	 * 
	 * @param g Contexte graphique.
	 */
	public void dessiner(Graphics g) {
		g.setColor(coin.getCouleur());
		g.drawRect((int) coin.getAbscisse(), (int) coin.getOrdonnee(), (int) largeur, (int) hauteur);
	}

	/**
	 * Teste si un point est contenu dans le rectangle.
	 * 
	 * @param p Point testé.
	 * @return true si contenu, false sinon.
	 */
	public boolean contient(Point p) {
		double x = p.getAbscisse();
		double y = p.getOrdonnee();
		return x >= coin.getAbscisse() && x <= (coin.getAbscisse() + largeur) && y >= coin.getOrdonnee()
				&& y <= (coin.getOrdonnee() + hauteur);
	}

	/**
	 * Déplace le rectangle en décalant son coin.
	 * 
	 * @param dx Déplacement horizontal.
	 * @param dy Déplacement vertical.
	 */
	public void deplacer(int dx, int dy) {
		coin.deplacer(dx, dy);
	}

	/**
	 * Effectue une rotation 90° en permutant largeur et hauteur.
	 * 
	 * @param theta Angle de rotation (en degrés), non utilisé ici.
	 */
	@Override
	public void rotate(int theta) {
		double aux = largeur;
		largeur = hauteur;
		hauteur = aux;
	}

	/**
	 * Change la taille du rectangle selon un delta.
	 * 
	 * @param delta Variation de taille.
	 */
	public void changerTaille(int delta) {
		largeur = Math.max(5, largeur + 2 * delta);
		hauteur = Math.max(5, hauteur + delta);
	}

	// Getters

	public Point getCoin() {
		return coin;
	}

	public double getLargeur() {
		return largeur;
	}

	public double getHauteur() {
		return hauteur;
	}
}
