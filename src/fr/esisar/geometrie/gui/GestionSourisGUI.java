package fr.esisar.geometrie.gui;

import fr.esisar.geometrie.ObjetGeometrique;
import fr.esisar.geometrie.formes.Point;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;

/**
 * Gestionnaire d'événements souris pour l'interface graphique. Permet la
 * sélection, le déplacement et la redimension des objets géométriques.
 */
public class GestionSourisGUI extends MouseAdapter {

	private final List<ObjetGeometrique> objets;
	private final JComponent composantGraphique;
	private ObjetGeometrique objSelectionne = null;
	private Point dernierPointSouris = null;

	/**
	 * Constructeur initialisant la liste des objets et le composant graphique.
	 * 
	 * @param objets             la liste des objets géométriques manipulés.
	 * @param composantGraphique le composant Swing où les objets sont dessinés.
	 */
	public GestionSourisGUI(List<ObjetGeometrique> objets, JComponent composantGraphique) {
		this.objets = objets;
		this.composantGraphique = composantGraphique;
	}

	/**
	 * Gère l'événement de clic souris : sélectionne un objet si le clic est à
	 * l'intérieur.
	 * 
	 * @param e l'événement de souris.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		deselectionner();
		Point p = new Point("BLACK", Color.BLACK, e.getX(), e.getY());
		for (ObjetGeometrique c : objets) {
			if (c.contient(p)) {
				objSelectionne = c;
				dernierPointSouris = p;
				break;
			}
		}
	}

	/**
	 * Gère le glissement de la souris : déplace l'objet sélectionné selon le
	 * mouvement.
	 * 
	 * @param e l'événement de souris.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (objSelectionne != null && dernierPointSouris != null) {
			int dx = e.getX() - (int) dernierPointSouris.getAbscisse();
			int dy = e.getY() - (int) dernierPointSouris.getOrdonnee();
			objSelectionne.deplacer(dx, dy);
			dernierPointSouris = new Point("BLACK", Color.BLACK, e.getX(), e.getY());
			composantGraphique.repaint();
		}
	}

	/**
	 * Gère la rotation de la molette de la souris : change la taille de l'objet
	 * sélectionné.
	 * 
	 * @param e l'événement de molette de souris.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (objSelectionne != null) {
			int rotation = e.getWheelRotation();
			objSelectionne.changerTaille((int) -rotation * 5);
			composantGraphique.repaint();
		}
	}

	/**
	 * Retourne l'objet géométrique actuellement sélectionné.
	 * 
	 * @return l'objet sélectionné ou null si aucun.
	 */
	public ObjetGeometrique getFormeSelectionnee() {
		return objSelectionne;
	}

	/**
	 * Désélectionne l'objet sélectionné et réinitialise le point souris.
	 */
	public void deselectionner() {
		objSelectionne = null;
		dernierPointSouris = null;
	}

}
