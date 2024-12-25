package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProizvodTest {

    @Test
    void testKreiranjeProizvoda() {
        Proizvod p = new Proizvod("Laptop", 1200.50);
        assertEquals("Laptop", p.getNaziv());
        assertEquals(1200.50, p.getCijena());
    }

    @Test
    void testKreiranjeProizvodaSaPraznimNazivom() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Proizvod("", 500));
        assertEquals("Naziv proizvoda ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    void testKreiranjeProizvodaSaNegativnomCijenom() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Proizvod("Telefon", -10));
        assertEquals("Cijena proizvoda ne može biti negativna.", exception.getMessage());
    }

    @Test
    void testEquals() {
        Proizvod p1 = new Proizvod("Laptop", 1200.50);
        Proizvod p2 = new Proizvod("laptop", 1500.00);
        assertEquals(p1, p2);
    }

    @Test
    void testToString() {
        Proizvod p = new Proizvod("Laptop", 1200.50);
        assertEquals("Laptop (1200.5 KM)", p.toString());
    }
}
