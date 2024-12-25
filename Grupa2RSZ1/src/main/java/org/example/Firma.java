package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Firma {
    private List<Poslovnica> poslovnice;
    private int brojP;

    public Firma(int brojP) {
        poslovnice = new ArrayList<>();
        this.brojP = brojP;
    }

    public void dodajPoslovnicu() {
        String naziv;
        Scanner sc = new Scanner(System.in);



        if (this.brojP > poslovnice.size()) {
            System.out.print("Unesite ime poslovnice: ");
            naziv = sc.nextLine();
            poslovnice.add(new Poslovnica(naziv));
            System.out.println("Poslovnica '" + naziv + "' je dodana.");
        }
        else {
            System.out.println("Max broj poslovnica je " + this.brojP + "." );
        }
    }

    public void ispisiPoslovnice(){
        poslovnice.forEach(p-> System.out.println(p.getNaziv()));
    }


    public void obrisiPoslovnicu(String naziv) {
        poslovnice.removeIf(p -> p.getNaziv().equalsIgnoreCase(naziv));
        System.out.println("Poslovnica '" + naziv + "' je obrisana.");
    }

    public Poslovnica pronadjiPoslovnicu(String naziv) {
        for (Poslovnica p : poslovnice) {
            if (p.getNaziv().equalsIgnoreCase(naziv)) {
                return p;
            }
        }
        return null;
    }

    public void unesiProfit(String naziv, int mjesec, double profit) {
        Poslovnica poslovnica = pronadjiPoslovnicu(naziv);
        if (poslovnica != null) {
            poslovnica.unesiProfit(mjesec, profit);
        } else {
            System.out.println("Poslovnica nije pronađena.");
        }
    }

    public void prikaziProfitPoPoslovnici(String naziv) {
        Poslovnica poslovnica = pronadjiPoslovnicu(naziv);
        if (poslovnica != null) {
            double[] profiti = poslovnica.dohvatiMjesecneProfite();
            System.out.println("Profiti za poslovnicu '" + naziv + "':");
            for (int i = 0; i < profiti.length; i++) {
                System.out.println("Mjesec " + (i + 1) + ": " + profiti[i]);
            }
        } else {
            System.out.println("Poslovnica nije pronađena.");
        }
    }

    public void prikaziUkupniProfitPoMjesecima() {
        System.out.println("Ukupni profit za sve poslovnice po mjesecima:");
        for (int i = 0; i < 12; i++) {
            double ukupno = 0;
            for (Poslovnica p : poslovnice) {
                ukupno += p.dohvatiProfitZaMjesec(i + 1);
            }
            System.out.println("Mjesec " + (i + 1) + ": " + ukupno);
        }
    }

    public void prikaziGodisnjiProfitZaSvePoslovnice() {
        System.out.println("Godišnji profit po poslovnici:");
        for (Poslovnica p : poslovnice) {
            System.out.println(p.getNaziv() + ": " + p.ukupniGodisnjiProfit());
        }
    }

    public void prikaziUkupniGodisnjiProfit() {
        double ukupno = 0;
        for (Poslovnica p : poslovnice) {
            ukupno += p.ukupniGodisnjiProfit();
        }
        System.out.println("Ukupni godišnji profit za sve poslovnice: " + ukupno);
    }
}
