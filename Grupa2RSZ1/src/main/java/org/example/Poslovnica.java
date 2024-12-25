package org.example;
import java.util.Arrays;

public class Poslovnica {
    private String naziv;
    private double[] mjesecniProfiti;

    public Poslovnica(String naziv) {
        this.naziv = naziv;
        this.mjesecniProfiti = new double[12];
    }

    public String getNaziv() {
        return naziv;
    }

    public void unesiProfit(int mjesec, double profit) {
        if (mjesec < 1 || mjesec > 12) {
            System.out.println("Nevažeći mjesec! Unesite mjesec između 1 i 12.");
        } else {
            mjesecniProfiti[mjesec - 1] += profit;
            System.out.println("Profit unesen za mjesec " + mjesec + ": " + profit);
        }
    }

    public double dohvatiProfitZaMjesec(int mjesec) {
        if (mjesec < 1 || mjesec > 12) return 0;
        return mjesecniProfiti[mjesec - 1];
    }

    public double ukupniGodisnjiProfit() {
        return Arrays.stream(mjesecniProfiti).sum();
    }

    public double[] dohvatiMjesecneProfite() {
        return mjesecniProfiti;
    }
}
