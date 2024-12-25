package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Korpa korpa = new Korpa();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Meni ===");
            System.out.println("1. Dodaj proizvod u korpu");
            System.out.println("2. Ukloni proizvod iz korpe");
            System.out.println("3. Prikaži ukupnu cijenu proizvoda u korpi");
            System.out.println("4. Prikaži sve proizvode u korpi");
            System.out.println("5. Prikaži nazive proizvoda sortirane po cijeni (silazno)");
            System.out.println("6. Izlaz");
            System.out.print("Izaberite opciju: ");

            int opcija = scanner.nextInt();
            scanner.nextLine(); // Konsumira višak linije

            switch (opcija) {
                case 1:
                    try {
                        System.out.print("Unesite naziv proizvoda: ");
                        String naziv = scanner.nextLine();
                        System.out.print("Unesite cijenu proizvoda: ");
                        double cijena = scanner.nextDouble();
                        Proizvod proizvod = new Proizvod(naziv, cijena);
                        korpa.dodajProizvod(proizvod);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Greška: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Unesite naziv proizvoda za uklanjanje: ");
                        String naziv = scanner.nextLine();
                        korpa.ukloniProizvod(naziv);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Greška: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ukupna cijena proizvoda u korpi: " + korpa.ukupnaCijena() + " KM");
                    } catch (IllegalStateException e) {
                        System.out.println("Greška: " + e.getMessage());
                    }
                    break;
                case 4:
                    korpa.ispisiKorpu();
                    break;
                case 5:
                    korpa.ispisiSortiranePoCijeniSilazno();
                    break;
                case 6:
                    running = false;
                    System.out.println("Izlazak iz programa...");
                    break;
                default:
                    System.out.println("Nepoznata opcija. Molimo pokušajte ponovo.");
            }
        }
        scanner.close();
    }
}
