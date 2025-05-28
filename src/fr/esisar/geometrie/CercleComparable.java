package fr.esisar.geometrie;

import fr.esisar.geometrie.formes.Cercle;
import fr.esisar.geometrie.formes.Point;

/**
 * Représente un cercle comparable basé sur sa surface. Cette classe hérite de
 * {@link Cercle} et implémente {@link Comparable} pour permettre la comparaison
 * entre cercles selon leur aire.
 */
public class CercleComparable extends Cercle implements Comparable<CercleComparable> {

	/**
	 * Constructeur de CercleComparable.
	 * 
	 * @param point Centre du cercle.
	 * @param rayon Rayon du cercle.
	 * @param nom   Nom du cercle.
	 */
	public CercleComparable(Point point, double rayon, String nom) {
		super(point, rayon, nom);
	}

	/**
	 * Compare ce cercle à un autre CercleComparable selon leur aire.
	 * 
	 * @param o L'autre cercle à comparer.
	 * @return Un entier négatif si ce cercle a une aire plus petite, zéro si les
	 *         aires sont égales, un entier positif si ce cercle a une aire plus
	 *         grande.
	 */
	@Override
	public int compareTo(CercleComparable o) {
		if (aire() == o.aire())
			return 0;
		else
			return aire() > o.aire() ? 1 : -1;
	}

	/**
	 * Représentation textuelle du cercle, indiquant son nom, rayon, aire et
	 * position du centre.
	 * 
	 * @return Une chaîne de caractères descriptive du cercle.
	 */
	@Override
	public String toString() {
		return String.format("%s de rayon : %.2f\nAire : %.2f\nMilieu : %s", getName(), getRayon(), aire(),
				getCentre().toString());
	}

}
