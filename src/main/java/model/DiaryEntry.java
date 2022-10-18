package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiaryEntry {
    private String name;
    private String title;
    private String entry;
    private LocalDate localDate;

    public DiaryEntry() {
    }

    public DiaryEntry(String name, String title, String entry, LocalDate localDate) {
        this.name = name;
        this.title = title;
        this.entry = entry;
        this.localDate = localDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public static String lineLength(String str, int width) {
        StringBuilder sb = new StringBuilder();

        while (true) {
            if (str.length() <= width) {
                sb.append(str);
                break;
            }

            int cutOff = 0;
            int temp = 0;
            while (((temp = str.indexOf(" ", ++temp)) < width) && (temp >= 0)) {
                cutOff = temp;
            }
            String newString = str.substring(0, cutOff);

            sb.append(newString);
            sb.append("\n");
            str = str.substring(cutOff);
        }
        return sb.toString();
    }

    public static void showDiaryEntry(String title, String entry, LocalDate localDate) {
        System.out.println("Titel: " + title);
        System.out.println("Inlägg: " + lineLength(entry, 30));
        System.out.println("Datum: " + localDate);
        System.out.println("-----------------------------");
    }

    public static void readListOfDiaryEntries(ObjectMapper mapper) throws IOException {
        Path pathDiaryEntries = Paths.get("src/main/resources/diaryEntries.json");
        List<DiaryEntry> listOfDiaryEntries;
        listOfDiaryEntries = List.of(mapper.readValue(pathDiaryEntries.toFile(), DiaryEntry[].class));

        boolean userHasWrittenADiaryEntry = false;
        for (DiaryEntry diaryEntry : listOfDiaryEntries) {
            if (User.getCurrentUserName().equalsIgnoreCase(String.valueOf(diaryEntry.getName()))) {
                DiaryEntry.showDiaryEntry
                        (diaryEntry.getTitle(), diaryEntry.getEntry(), diaryEntry.getLocalDate());
                userHasWrittenADiaryEntry = true;
            }
        }
        if (!userHasWrittenADiaryEntry) {
            System.out.println("Du har inte skrivit några inlägg, skriv gärna ett inlägg!");
        }
    }

    public static void writeNewDiaryEntry(ObjectMapper mapper, Scanner input, String name) throws IOException {
        mapper.registerModule(new JavaTimeModule());
        LocalDate localDate = LocalDate.now();
        Path pathDiaryEntries = Paths.get("src/main/resources/diaryEntries.json");

        input.nextLine();
        System.out.println("Fyll i titeln på ditt inlägg: ");
        String choiceOfTitle = input.nextLine();
        System.out.println("Skriv ditt inlägg: ");
        String choiceOfEntry = input.nextLine();

        DiaryEntry newDiaryEntry = new DiaryEntry(name, choiceOfTitle, choiceOfEntry, localDate);
        List<DiaryEntry> diaryEntries =
                new ArrayList<>(List.of(mapper.readValue(pathDiaryEntries.toFile(), DiaryEntry[].class)));
        diaryEntries.add(newDiaryEntry);

        DiaryEntry.showDiaryEntry(choiceOfTitle, choiceOfEntry, localDate);
        mapper.writeValue(pathDiaryEntries.toFile(), diaryEntries);
    }
}
