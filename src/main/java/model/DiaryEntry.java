package model;

import java.time.LocalDate;

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
    public static void showDiaryEntry(String title, String entry, LocalDate localDate) {
        System.out.println("Titel: " + title);
        System.out.println("Inlägg: " + entry);
        System.out.println("Datum: " + localDate);
        System.out.println("-------------------");
    }
}
