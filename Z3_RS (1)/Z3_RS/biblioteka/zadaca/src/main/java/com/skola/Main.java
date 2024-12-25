package com.skola;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Biblioteka ---");
            System.out.println("1. Dodaj knjigu");
            System.out.println("2. Ažuriraj knjigu");
            System.out.println("3. Obriši knjigu");
            System.out.println("4. Pretraži knjige");
            System.out.println("5. Izlaz");
            System.out.print("Izaberite opciju: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Unesite validan broj opcije.");
                scanner.nextLine();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    updateBook(scanner);
                    break;
                case 3:
                    deleteBook(scanner);
                    break;
                case 4:
                    searchBooks(scanner);
                    break;
                case 5:
                    System.out.println("Izlaz iz programa. Pozdrav!");
                    break;
                default:
                    System.out.println("Nepoznata opcija. Pokušajte ponovo.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Unesite naziv knjige:");
        String naziv = scanner.nextLine().trim();

        if (naziv.isEmpty()) {
            System.out.println("Naziv knjige ne može biti prazan.");
            return;
        }

        System.out.println("Unesite autora knjige:");
        String autor = scanner.nextLine().trim();

        int godina = inputPositiveInt(scanner, "Unesite godinu izdavanja:");

        BookStatus status = inputBookStatus(scanner, "Unesite status knjige (Dostupna/Izdata):");

        BookOperations.addBook(naziv, autor, godina, status.getStatus());
        System.out.println("Knjiga uspješno dodana.");
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Unesite ID knjige za ažuriranje:");
        int updateId = inputPositiveInt(scanner, "Unesite validan ID knjige:");

        System.out.println("Unesite novi naziv knjige:");
        String newNaziv = scanner.nextLine().trim();

        System.out.println("Unesite novog autora:");
        String newAutor = scanner.nextLine().trim();

        int newGodina = inputPositiveInt(scanner, "Unesite novu godinu izdavanja:");

        BookStatus newStatus = inputBookStatus(scanner, "Unesite novi status knjige (Dostupna/Izdata):");

        BookOperations.updateBook(updateId, newNaziv, newAutor, newGodina, newStatus.getStatus());
        System.out.println("Knjiga uspješno ažurirana.");
    }

    private static void deleteBook(Scanner scanner) {
        System.out.println("Unesite ID knjige za brisanje:");
        int deleteId = inputPositiveInt(scanner, "Unesite validan ID knjige:");

        BookOperations.deleteBook(deleteId);
        System.out.println("Knjiga uspješno obrisana.");
    }

    private static void searchBooks(Scanner scanner) {
        System.out.println("Unesite naziv knjige za pretragu (ili ostavite prazno):");
        String searchNaziv = scanner.nextLine().trim();

        System.out.println("Unesite autora knjige za pretragu (ili ostavite prazno):");
        String searchAutor = scanner.nextLine().trim();

        Integer searchGodina = null;
        System.out.println("Unesite godinu izdavanja za pretragu (ili ostavite prazno):");
        String godinaInput = scanner.nextLine().trim();
        if (!godinaInput.isEmpty()) {
            try {
                searchGodina = Integer.parseInt(godinaInput);
            } catch (NumberFormatException e) {
                System.out.println("Godina nije validna. Pretraga nastavljena bez godine.");
            }
        }

        BookStatus searchStatus = null;
        System.out.println("Unesite status za pretragu (Dostupna/Izdata ili ostavite prazno):");
        String statusInput = scanner.nextLine().trim();
        if (!statusInput.isEmpty()) {
            try {
                searchStatus = BookStatus.fromString(statusInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Nevazeći status. Pretraga nastavljena bez statusa.");
            }
        }

        BookOperations.searchBooks(
                searchNaziv,
                searchAutor,
                searchGodina,
                searchStatus != null ? searchStatus.getStatus() : null
        );
    }

    private static int inputPositiveInt(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                number = Integer.parseInt(input);
                if (number > 0) return number;
                else System.out.println("Vrijednost mora biti pozitivan broj.");
            } catch (NumberFormatException e) {
                System.out.println("Molimo unesite validan broj.");
            }
        }
    }

    private static BookStatus inputBookStatus(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String statusInput = scanner.nextLine().trim();
            try {
                return BookStatus.fromString(statusInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Nevažeći status. Pokušajte ponovo (Dostupna/Izdata). ");
            }
        }
    }
}
