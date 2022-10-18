package model;

import java.time.LocalDate;
import java.util.List;

public class Diary {
    private User user;
    private List<DiaryEntry> diaryEntryList;

    public Diary() {
    }

    public Diary(User user, List<DiaryEntry> diaryEntryList) {
        this.user = user;
        this.diaryEntryList = diaryEntryList;
    }

    public Diary(String name, String choiceOfTitle, String choiceOfEntry, LocalDate localDate) {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DiaryEntry> getDiaryEntryList() {
        return diaryEntryList;
    }

    public void setDiaryEntryList(List<DiaryEntry> diaryEntryList) {
        this.diaryEntryList = diaryEntryList;
    }

    public void showDiary() {
        System.out.println("Namn på användare: " + getUser());
    }
}
