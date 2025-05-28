package fr.esisar.geometrie;

import fr.esisar.geometrie.formes.Cercle;
import fr.esisar.geometrie.formes.Point;

/**
 * Classe JO qui génère le SVG des anneaux olympiques avec leurs positions et
 * couleurs.
 */
public class JO {

	/**
	 * Point d'entrée du programme. Crée les cercles représentant les anneaux
	 * olympiques et affiche leur représentation SVG dans la console.
	 * 
	 * @param args arguments de la ligne de commande (non utilisés).
	 */
	public static void main(String[] args) {

		/*
		 * Anneaux Olympiques
		 */
		Point pointB = new Point("blue", 54, 54);
		Cercle cercleB = new Cercle(pointB, 49.25, "Cercle");

		Point pointJ = new Point("yellow", 112.5, 104);
		Cercle cercleJ = new Cercle(pointJ, 49.25, "Cercle");

		Point pointN = new Point("black", 171, 54);
		Cercle cercleN = new Cercle(pointN, 49.25, "Cercle");

		Point pointR = new Point("red", 288, 54);
		Cercle cercleR = new Cercle(pointR, 49.25, "Cercle");

		Point pointV = new Point("green", 229.5, 104);
		Cercle cercleV = new Cercle(pointV, 49.25, "Cercle");

		System.out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"600\" width=\"800\">\n"
				+ "<style> @keyframes carre {0%{transform: translate(0, 0);}25% {transform: translate(400px, 0);}50% {transform: translate(400px, 400px);}75% {transform: translate(0, 400px);}100% {transform: translate(0, 0);}}.anim {animation: carre 8s infinite;}</style>"
				+ "<g stroke-width=\"9.5\" stroke=\"#000\" fill=\"none\">");
		System.out.println(cercleB.affichageSVG());
		System.out.println(cercleN.affichageSVG());
		System.out.println(cercleJ.affichageSVG());
		System.out.println(cercleR.affichageSVG());
		System.out.println(cercleV.affichageSVG());
		System.out.println("<path class=\"anim\" d=\"M93.4,24.45A49.25,49.25 0 0 1 93.4,83.55\" stroke=\"blue\"/>\n"
				+ "<path class=\"anim\" d=\"M210.4,24.45A49.25,49.25 0 0 1 210.4,83.55M171,103.25A49.25,49.25 0 0 1 141.45,93.4\"/>\n"
				+ "<path class=\"anim\" d=\"M288,103.25A49.25,49.25 0 0 1 258.45,93.4\" stroke=\"red\"/></g></svg>\n");

	}
}
