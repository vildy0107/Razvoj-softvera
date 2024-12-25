package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class KorpaTest {

    @Test
    void testDodajProizvod() {
        Korpa korpa = new Korpa();
        Proizvod p = new Proizvod("Laptop", 1200.50);
        korpa.dodajProizvod(p);

        List<Proizvod> proizvodi = korpa.getProizvodi();
        assertTrue(proizvodi.contains(p));
    }

    @Test
    void testDodajProizvodKojiVecPostoji() {
        Korpa korpa = new Korpa();
        Proizvod p = new Proizvod("Laptop", 1200.50);
        korpa.dodajProizvod(p);
        korpa.dodajProizvod(p);

        assertEquals(1, korpa.getProizvodi().size());
    }

    @Test
    void testUkloniProizvod() {
        Korpa korpa = new Korpa();
        Proizvod p = new Proizvod("Laptop", 1200.50);
        korpa.dodajProizvod(p);
        korpa.ukloniProizvod("Laptop");

        assertFalse(korpa.getProizvodi().contains(p));
    }

    @Test
    void testUkloniProizvodKojiNePostoji() {
        Korpa korpa = new Korpa();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> korpa.ukloniProizvod("Telefon"));
        assertEquals("Proizvod 'Telefon' nije pronađen u korpi.", exception.getMessage());
    }

    @Test
    void testUkupnaCijena() {
        Korpa korpa = new Korpa();
        korpa.dodajProizvod(new Proizvod("Laptop", 1200.50));
        korpa.dodajProizvod(new Proizvod("Telefon", 800.00));

        assertEquals(2000.50, korpa.ukupnaCijena());
    }

    @Test
    void testUkupnaCijenaPraznaKorpa() {
        Korpa korpa = new Korpa();
        Exception exception = assertThrows(IllegalStateException.class, korpa::ukupnaCijena);
        assertEquals("Korpa je prazna.", exception.getMessage());
    }

    @Test
    void testIspisiSortiranePoCijeniSilazno() {
        Korpa korpa = new Korpa();
        korpa.dodajProizvod(new Proizvod("Telefon", 800.00));
        korpa.dodajProizvod(new Proizvod("Laptop", 1200.50));

        List<Proizvod> proizvodi = korpa.getProizvodi();
        proizvodi.sort((p1, p2) -> Double.compare(p2.getCijena(), p1.getCijena()));

        assertEquals("Laptop", proizvodi.get(0).getNaziv());
        assertEquals("Telefon", proizvodi.get(1).getNaziv());
    }
}
