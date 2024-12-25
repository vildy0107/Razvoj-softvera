package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String naziv;

        int brPoslovnica;
        System.out.println("Unesite broj poslovnica");
        brPoslovnica = scanner.nextInt();
        System.out.println("Unijeto je " + brPoslovnica);

        Firma firma = new Firma(brPoslovnica);

        while (true) {
            System.out.println("\nMeni:");
            System.out.println("1. Dodaj poslovnicu");
            System.out.println("2. Ispiši poslovnice");
            System.out.println("3. Obriši poslovnicu");
            System.out.println("4. Unesi profit za poslovnicu");
            System.out.println("5. Prikaz profita po poslovnici");
            System.out.println("6. Prikaz ukupnog profita po mjesecima");
            System.out.println("7. Prikaz godišnjeg profita po poslovnici");
            System.out.println("8. Prikaz ukupnog godišnjeg profita");
            System.out.println("0. Izlaz");

            System.out.print("Izaberite opciju: ");
            int izbor = scanner.nextInt();
            scanner.nextLine();

            switch (izbor) {
                case 1:

                    firma.dodajPoslovnicu();
                    break;
                    case 2:
                        firma.ispisiPoslovnice();
                        break;
                case 3:
                    System.out.print("Unesite ime poslovnice za brisanje: ");
                    naziv = scanner.nextLine();
                    firma.obrisiPoslovnicu(naziv);
                    break;
                case 4:
                    System.out.print("Unesite ime poslovnice: ");
                    naziv = scanner.nextLine();
                    System.out.print("Unesite broj mjeseca (1-12): ");
                    int mjesec = scanner.nextInt();
                    System.out.print("Unesite profit: ");
                    double profit = scanner.nextDouble();
                    firma.unesiProfit(naziv, mjesec, profit);
                    break;
                case 5:
                    System.out.print("Unesite ime poslovnice: ");
                    naziv = scanner.nextLine();
                    firma.prikaziProfitPoPoslovnici(naziv);
                    break;
                case 6:
                    firma.prikaziUkupniProfitPoMjesecima();
                    break;
                case 7:
                    firma.prikaziGodisnjiProfitZaSvePoslovnice();
                    break;
                case 8:
                    firma.prikaziUkupniGodisnjiProfit();
                    break;
                case 0:
                    System.out.println("Izlaz iz programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nepoznata opcija. Molimo pokušajte ponovo.");
            }
        }
    }
}
