package org.example;

import java.util.Objects;

public class Proizvod {
    private String naziv;
    private double cijena;

    public Proizvod(String naziv, double cijena) {
        if (naziv == null || naziv.trim().isEmpty()) {
            throw new IllegalArgumentException("Naziv proizvoda ne može biti prazan ili null.");
        }
        if (cijena < 0) {
            throw new IllegalArgumentException("Cijena proizvoda ne može biti negativna.");
        }
        this.naziv = naziv;
        this.cijena = cijena;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCijena() {
        return cijena;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Proizvod proizvod = (Proizvod) obj;
        return naziv.equalsIgnoreCase(proizvod.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv.toLowerCase());
    }

    @Override
    public String toString() {
        return naziv + " (" + cijena + " KM)";
    }
}
