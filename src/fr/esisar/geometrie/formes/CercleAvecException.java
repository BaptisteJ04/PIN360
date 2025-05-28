package fr.esisar.geometrie.formes;

/**
 * Classe CercleAvecException étendant Cercle, avec vérification du rayon à la
 * création.
 */
public class CercleAvecException extends Cercle {

	/**
	 * Constructeur privé pour forcer l'usage de la méthode factory.
	 * 
	 * @param point le centre du cercle.
	 * @param rayon le rayon du cercle.
	 * @param nom   le nom du cercle.
	 */
	private CercleAvecException(Point point, double rayon, String nom) {
		super(point, rayon, nom);
	}

	/**
	 * Méthode factory statique pour créer un CercleAvecException en validant le
	 * rayon.
	 * 
	 * @param point le centre du cercle.
	 * @param rayon le rayon du cercle, doit être positif.
	 * @param nom   le nom du cercle.
	 * @return un nouvel objet CercleAvecException si le rayon est valide.
	 * @throws IllegalArgumentException si le rayon est négatif.
	 */
	public static CercleAvecException newCercleException(Point point, double rayon, String nom)
			throws IllegalArgumentException {
		if (rayon < 0) {
			throw new IllegalArgumentException("Rayon doit être positif");
		} else {
			return new CercleAvecException(point, rayon, nom);
		}
	}
}
