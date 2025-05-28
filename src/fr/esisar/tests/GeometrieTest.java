package fr.esisar.tests;

import fr.esisar.geometrie.ObjetGeometrique;
import fr.esisar.geometrie.formes.Cercle;
import fr.esisar.geometrie.formes.Point;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GeometrieTest {

    public static boolean surfaceIdentique(ObjetGeometrique objet1, ObjetGeometrique objet2) {
        return objet1.aire() == objet2.aire();
    }

    public static void afficheObjetGeometrique(ObjetGeometrique objet) {
        System.out.println(objet.toString());
        System.out.printf("Names %s\nPerimetre : %.2f\nAire : %.2f\n",
                objet.getName(), objet.perimetre(), objet.aire());
    }

    @Test
    public void testSurfaceIdentique() {
        Point centre1 = new Point("A", 0.0, 0.0);
        Point centre2 = new Point("B", 1.0, 1.0);

        ObjetGeometrique cercle1 = new Cercle(centre1, 5.0, "Cercle");
        ObjetGeometrique cercle2 = new Cercle(centre2, 5.0, "Cercle");
        ObjetGeometrique cercle3 = new Cercle(centre2, 3.0, "Cercle");

        assertTrue(surfaceIdentique(cercle1, cercle2), "Les cercles avec le même rayon doivent avoir la même surface.");
        assertFalse(surfaceIdentique(cercle1, cercle3), "Les cercles avec des rayons différents doivent avoir des surfaces différentes.");
    }

    @Test
    public void testAffichageObjetGeometrique() {
        Point centre = new Point("C", 2.0, 3.0);
        ObjetGeometrique cercle = new Cercle(centre, 4.0, "TestCercle");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            afficheObjetGeometrique(cercle);
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("TestCercle"), "Le nom du cercle doit apparaître dans l'affichage.");
        assertTrue(output.contains("Perimetre"), "Le périmètre doit apparaître dans l'affichage.");
        assertTrue(output.contains("Aire"), "L'aire doit apparaître dans l'affichage.");
    }
}
