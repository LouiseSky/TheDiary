import com.fasterxml.jackson.databind.ObjectMapper;
import model.DiaryEntry;
import model.MainMenu;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Scanner input = new Scanner(System.in);

        List<DiaryEntry> diaryEntries = new ArrayList<>();
        Date date = new Date();

        int choice;

            do {
                MainMenu.greeting();
                choice = input.nextInt();

                Path path = Paths.get("src/main/resources/diaryEntry.json");

                if (choice == 1) {

                   List<DiaryEntry> diaryEntryList1 =
                            List.of(mapper.readValue(path.toFile(), DiaryEntry[].class));

                    for (DiaryEntry entry : diaryEntryList1) {
                        System.out.println(entry.getTitle());
                        System.out.println(entry.getEntry());
                        System.out.println(entry.getDate());
                        System.out.println("--------------------------");
                    }
                } else if (choice == 2) {
                    input.nextLine();
                    System.out.println("Fyll i titeln på ditt inlägg: ");
                    String choiceOfTitle = input.nextLine();
                    System.out.println("Skriv ditt inlägg: ");
                    // TODO lägga till om det är någon som skriver ett långt inlägg
                    String choiceOfEntry = input.nextLine();
                    DiaryEntry newDiaryEntry = new DiaryEntry(choiceOfTitle, choiceOfEntry, date);
                    List <DiaryEntry> tempFromJSON =
                            (List.of(mapper.readValue(path.toFile(), DiaryEntry[].class)));
                    diaryEntries.addAll(tempFromJSON);
                    diaryEntries.add(newDiaryEntry);

                    System.out.println(newDiaryEntry.getTitle());
                    System.out.println(newDiaryEntry.getEntry());
                    System.out.println(newDiaryEntry.getDate());

                    mapper.writeValue(path.toFile(),diaryEntries);

                } else if (choice == 3) {
                    System.out.println("Hejdå, tack för idag! Programmet avslutas");
                }
            } while (choice != 3);
    }

    static void writeAndReadValue(List<DiaryEntry> diaryEntries) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diaryEntries);

    }
}
