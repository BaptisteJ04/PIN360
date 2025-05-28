package fr.esisar.tests;

import fr.esisar.geometrie.formes.CercleAvecException;
import fr.esisar.geometrie.formes.Point;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CercleAvecExceptionTest {

	@Test
    public void testRayonNegatif() {
        Point centre = new Point("O", 0.0, 0.0);
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CercleAvecException.newCercleException(centre, -5.0, "Cercle");
        });
        assertEquals("Rayon doit être positif", e.getMessage());
    }
}
