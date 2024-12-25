package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Korpa {
    private List<Proizvod> proizvodi;

    public Korpa() {
        proizvodi = new ArrayList<>();
    }

    public void dodajProizvod(Proizvod proizvod) {
        if (proizvodi.contains(proizvod)) {
            System.out.println("Proizvod '" + proizvod.getNaziv() + "' je već u korpi.");
        } else {
            proizvodi.add(proizvod);
            System.out.println("Proizvod '" + proizvod.getNaziv() + "' je dodan u korpu.");
        }
    }

    public void ukloniProizvod(String naziv) {
        Proizvod proizvodZaUkloniti = proizvodi.stream()
                .filter(p -> p.getNaziv().equalsIgnoreCase(naziv))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Proizvod '" + naziv + "' nije pronađen u korpi."));
        proizvodi.remove(proizvodZaUkloniti);
        System.out.println("Proizvod '" + naziv + "' je uklonjen iz korpe.");
    }

    public double ukupnaCijena() {
        if (proizvodi.isEmpty()) {
            throw new IllegalStateException("Korpa je prazna.");
        }
        return proizvodi.stream().mapToDouble(Proizvod::getCijena).sum();
    }

    public void ispisiKorpu() {
        if (proizvodi.isEmpty()) {
            System.out.println("Korpa je prazna.");
        } else {
            proizvodi.forEach(System.out::println);
        }
    }

    public void ispisiSortiranePoCijeniSilazno() {
        proizvodi.stream()
                .sorted(Comparator.comparingDouble(Proizvod::getCijena).reversed())
                .forEach(System.out::println);
    }

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }
}
