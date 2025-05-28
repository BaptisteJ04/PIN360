package fr.esisar.geometrie;

import java.util.ArrayList;

import fr.esisar.geometrie.formes.Point;

/**
 * Classe démontrant le tri de cercles comparables selon leur aire, à l’aide
 * d’un tri à bulles.
 */
public class CerclesTries {

	/**
	 * Point d'entrée du programme. Crée plusieurs cercles, affiche la liste avant
	 * et après tri.
	 * 
	 * @param args Arguments en ligne de commande (non utilisés).
	 */
	public static void main(String[] args) {
		// Création de quelques cercles
		ArrayList<CercleComparable> cercles = new ArrayList<>();

		cercles.add(new CercleComparable(new Point("A", 0, 0), 5.0, "Cercle"));
		cercles.add(new CercleComparable(new Point("B", 2, 1), 3.0, "Cercle"));
		cercles.add(new CercleComparable(new Point("C", 1, 3), 7.0, "Cercle"));
		cercles.add(new CercleComparable(new Point("D", -1, -1), 4.0, "Cercle"));

		// Affichage avant tri
		System.out.println("Avant tri :");
		for (CercleComparable c : cercles) {
			System.out.println(c.toString());
		}

		// Tri des cercles
		triBulle(cercles);

		// Affichage après tri
		System.out.println("\nAprès tri :");
		for (CercleComparable c : cercles) {
			System.out.println(c.toString());
		}
	}

	/**
	 * Trie une liste de CercleComparable par ordre croissant d’aire en utilisant
	 * l’algorithme du tri à bulles.
	 * 
	 * @param liste La liste de cercles à trier.
	 */
	public static void triBulle(ArrayList<CercleComparable> liste) {
		int n = liste.size();
		boolean permutation; // optimisation dans le meilleur des cas

		do {
			permutation = false;
			for (int i = 0; i < n - 1; i++) {
				// Si l'élément i est plus grand que l'élément i+1
				if (liste.get(i).compareTo(liste.get(i + 1)) > 0) {
					// échange des deux éléments
					CercleComparable temp = liste.get(i);
					liste.set(i, liste.get(i + 1));
					liste.set(i + 1, temp);
					permutation = true;
				}
			}
			// Après chaque passage, le plus grand est à la fin
			n--;
		} while (permutation);
	}

}
