package fr.esisar.geometrie.gui;

import fr.esisar.geometrie.ObjetGeometrique;
import fr.esisar.geometrie.formes.Cercle;
import fr.esisar.geometrie.formes.Ellipse;
import fr.esisar.geometrie.formes.Ligne;
import fr.esisar.geometrie.formes.Point;
import fr.esisar.geometrie.formes.Rectangle;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;  
import java.io.IOException; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Interface graphique interactive permettant de dessiner et manipuler des
 * formes géométriques (cercles, rectangles, ellipses, lignes). Elle gère les
 * interactions souris et affiche les formes.
 */
public class GUIInteractifs extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Gestionnaire des événements souris.
	 */
	private GestionSourisGUI gestionSouris;

	/**
	 * Liste des objets géométriques dessinés.
	 */
	private final ArrayList<ObjetGeometrique> objets = new ArrayList<>();

	/**
	 * Méthode principale qui crée et affiche la fenêtre principale avec les boutons
	 * pour interagir avec les formes.
	 * 
	 * @param args Arguments de la ligne de commande (non utilisés).
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Formes Interactives");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 700);
		frame.setLayout(new BorderLayout());

		GUIInteractifs panelDessin = new GUIInteractifs();

		// Panneaux pour organiser les boutons
		JPanel panneauBoutons = new JPanel();
		JPanel panneauDel = new JPanel();
		JPanel panneauOption = new JPanel();

		// Boutons pour ajouter des formes, tourner, et supprimer
		JButton btnCercle = new JButton("Ajouter Cercle");
		JButton btnRectangle = new JButton("Ajouter Rectangle");
		JButton btnEllipse = new JButton("Ajouter Ellipse");
		JButton btnLigne = new JButton("Ajouter Ligne");
		JButton btnRot = new JButton("Rotate Fig");
		JButton btnSupprimer = new JButton("Supprimer Forme");
		JButton btnTout = new JButton("Supprimer Tout");
		JButton btnExporterSVG = new JButton("Exporter en SVG");


		// Gravite
		JButton btnGravite = new JButton("Activer Gravité");

		// Ajout des actions sur les boutons
		btnCercle.addActionListener((ActionEvent e) -> panelDessin.ajouterCercle());
		btnRectangle.addActionListener((ActionEvent e) -> panelDessin.ajouterRectangle());
		btnEllipse.addActionListener((ActionEvent e) -> panelDessin.ajouterEllipse());
		btnLigne.addActionListener((ActionEvent e) -> panelDessin.ajouterLigne());
		btnSupprimer.addActionListener((ActionEvent e) -> panelDessin.supprimerFormeSelectionnee());
		btnTout.addActionListener((ActionEvent e) -> panelDessin.supprimerTout());
		btnRot.addActionListener((ActionEvent e) -> panelDessin.rotateFig());
		btnGravite.addActionListener((ActionEvent e) -> panelDessin.appliquerGravite());
		btnExporterSVG.addActionListener((ActionEvent e) -> panelDessin.exporterSVG());

		// Ajout des boutons aux panneaux
		panneauBoutons.add(btnCercle);
		panneauBoutons.add(btnRectangle);
		panneauBoutons.add(btnEllipse);
		panneauBoutons.add(btnLigne);
		panneauOption.setLayout(new BoxLayout(panneauOption, BoxLayout.Y_AXIS));
		panneauOption.add(btnRot);
		panneauOption.add(btnExporterSVG);
		panneauOption.add(btnGravite);
		panneauDel.add(btnSupprimer);
		panneauDel.add(btnTout);

		// Placement des panneaux dans la fenêtre
		frame.add(panneauBoutons, BorderLayout.NORTH);
		frame.add(panneauOption, BorderLayout.EAST);
		frame.add(panneauDel, BorderLayout.SOUTH);
		frame.add(panelDessin, BorderLayout.CENTER);

		// Affichage de la fenêtre
		frame.setVisible(true);
	}

	/**
	 * Constructeur qui initialise le panneau et configure la gestion de la souris.
	 */
	public GUIInteractifs() {
		setLayout(null); // on utilisera paintComponent pour dessiner

		// Gestion souris
		gestionSouris = new GestionSourisGUI(objets, this);
		addMouseListener(gestionSouris);
		addMouseMotionListener(gestionSouris);
		addMouseWheelListener(gestionSouris);
	}

	/**
	 * Méthode appelée automatiquement pour redessiner le panneau. Elle dessine
	 * toutes les formes contenues dans la liste.
	 * 
	 * @param g Le contexte graphique sur lequel dessiner.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (ObjetGeometrique c : objets) {
			c.dessiner(g);
		}
	}

	/**
	 * Effectue une rotation de 15 degrés sur la forme sélectionnée puis redessine.
	 */
	private void rotateFig() {
		ObjetGeometrique forme = gestionSouris.getFormeSelectionnee();
		if (forme != null) {
			forme.rotate(15);
			repaint();
		}
	}

	/**
	 * Applique la gravité avec rebond amorti à tous les objets géométriques.
	 * Les objets "rebondissent" lorsqu'ils atteignent le bas du panneau.
	 */
	private void appliquerGravite() {
	    Timer timer = new Timer(40, null); // 40 ms = ~25 FPS

	    timer.addActionListener(new AbstractAction() {
	        final double GRAVITE = 0.98;
	        final double AMORTISSEMENT = 0.7;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int hauteurSol = getHeight();
	            boolean encoreEnMouvement = false;

	            for (ObjetGeometrique obj : objets) {
	                // Appliquer la gravité
	                obj.setVY(obj.getVY() + GRAVITE);

	                // Obtenir la bordure [centre, demi-largeur, demi-hauteur]
	                Object[] bordure = obj.getBordure();
	                Point centre = (Point) bordure[0];
	                double Hauteur = (double) bordure[2];

	                // Déplacer selon la vitesse actuelle
	                obj.deplacer((int) obj.getVX(), (int) obj.getVY());

	                // Calcul de la position du bas de l'objet
	                int yBas = (int) (centre.getOrdonnee() + Hauteur);

	                if (yBas >= hauteurSol) {
	                    // Rebond amorti
	                    obj.setVY(-obj.getVY() * AMORTISSEMENT);

	                    // Corrige la position pour rester sur le sol
	                    obj.deplacer(0, hauteurSol - yBas);
	                }

	                // Vérifie si cet objet est encore en mouvement
	                if (Math.abs(obj.getVY()) > 0.5 || yBas < hauteurSol) {
	                    encoreEnMouvement = true;
	                }
	            }

	            repaint();

	            // Arrêter le timer si plus aucun objet ne bouge
	            if (!encoreEnMouvement) {
	                ((Timer) e.getSource()).stop();
	                System.out.println("Gravité arrêtée : tous les objets sont au repos.");
	            }
	        }
	    });

	    timer.start();
	}

	/**
	 * Ajoute un cercle magenta à la liste des objets puis redessine.
	 */
	private void ajouterCercle() {
		ObjetGeometrique cercle = new Cercle(new Point("MAGENTA", Color.MAGENTA, 100, 100), 40, "C");
		objets.add(cercle);
		repaint();
	}

	/**
	 * Ajoute un rectangle orange à la liste des objets puis redessine.
	 */
	private void ajouterRectangle() {
		ObjetGeometrique rect = new Rectangle(new Point("ORANGE", Color.ORANGE, 150, 150), 80, 40, "R");
		objets.add(rect);
		repaint();
	}

	/**
	 * Ajoute une ellipse bleue à la liste des objets puis redessine.
	 */
	private void ajouterEllipse() {
		ObjetGeometrique ellipse = new Ellipse(new Point("BLUE", Color.BLUE, 150, 150), 80, 40, "E");
		objets.add(ellipse);
		repaint();
	}

	/**
	 * Ajoute une ligne bleue à la liste des objets puis redessine.
	 */
	private void ajouterLigne() {
		ObjetGeometrique ligne = new Ligne(new Point("BLUE", Color.BLUE, 150, 150),
				new Point("BLUE", Color.BLUE, 250, 250), "L");
		objets.add(ligne);
		repaint();
	}

	/**
	 * Supprime la forme sélectionnée de la liste et redessine.
	 */
	private void supprimerFormeSelectionnee() {
		ObjetGeometrique forme = gestionSouris.getFormeSelectionnee();
		if (forme != null) {
			objets.remove(forme);
			gestionSouris.deselectionner();
			repaint();
		}
	}

	/**
	 * Supprime toutes les formes de la liste puis redessine.
	 */
	private void supprimerTout() {
		objets.clear();
		repaint();
	}
	
	
	/**
	 * Exporte tous les objets géométriques au format SVG dans un fichier export.svg
	 */
	private void exporterSVG() {
	    try {
	        // Chemin absolu ou relatif depuis la racine du projet
	        Path path = Paths.get("../output/export.svg");

	        // S'assurer que le dossier existe
	        Files.createDirectories(path.getParent());

	        // Générer le contenu SVG
	        StringBuilder svg = new StringBuilder();
	        svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"")
	           .append(getWidth())
	           .append("\" height=\"")
	           .append(getHeight())
	           .append("\">\n");

	        for (ObjetGeometrique obj : objets) {
	            svg.append("  ").append(obj.affichageSVG()).append("\n");
	        }

	        svg.append("</svg>");

	        // Écrire le fichier
	        FileWriter myWriter = new FileWriter(path.toString());
	        myWriter.write(svg.toString());
	        myWriter.close();
	        JOptionPane.showMessageDialog(this, "SVG exporté dans : " + path.toAbsolutePath());

	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Erreur d'export : " + e.getMessage());
	    }
	}

}
