import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<DiaryEntry> diaryEntries = new ArrayList<>();
        int choice;

        do {

            greeting();
            choice = input.nextInt();

            // Lägg till en while eller do

            if (choice == 1) {
                System.out.println("Lista för att se dina inlägg: ");
                System.out.println(diaryEntries);
                // Lista

            } else if (choice == 2) {
                System.out.println("Fyll i titeln på ditt inlägg.");

                System.out.println("Skriv ditt inlägg.");

            } else if (choice == 3) {
                System.out.println("Hejdå, tack för idag! Programmet avslutas");
            }
        } while (choice !=3);
    }
    static void greeting() {
        System.out.println("Välkommen till dagboken! Vill du " +
                "1. Läsa din dagbok " +
                "2. Skriva inlägg " +
                "3. Avsluta programmet");
    }
}
