import com.fasterxml.jackson.databind.ObjectMapper;
import model.Diary;
import model.DiaryEntry;
import model.MainMenu;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.registerModule(new JavaTimeModule());
        Scanner input = new Scanner(System.in);
        LocalDate localDate = LocalDate.now();
        Path pathOne = Paths.get("src/main/resources/users.json");

       // List <Diary> diaryEntries = new ArrayList<>();
        List<DiaryEntry> diaryEntries = new ArrayList<>();
        List<User> usersList = new ArrayList<>();
        Diary diary = new Diary();
        int choiceOfUser;

        do {
            MainMenu.menu1("Ingen");
            choiceOfUser = input.nextInt();

            if (choiceOfUser == 1) {
                System.out.println("Lista över användare: ");
                List<User> usersList1 = List.of(mapper.readValue(pathOne.toFile(), User[].class));
                for (User user : usersList1) {
                    System.out.println(user.getName());
                }
                input.nextLine();
                System.out.println("Var god välj en av användarna i listan, skriv namnet.");
                String choiceOfUserInList = input.nextLine();

                // TODO Lägga till så att den returnerar till en ny loop
                if (true) {
                    MainMenu.menu2(choiceOfUserInList);
                    int choice = input.nextInt();

                    Path pathTwo = Paths.get("src/main/resources/diaryEntry.json");

                    if (choice == 1) {
                        List<DiaryEntry> diaryEntryList1 =
                                List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class));
                        int i;

                        for (i = 0; i < diaryEntryList1.size(); i++) {
                            System.out.println(diaryEntryList1.get(i).getTitle());
                            //System.out.println(entry.getDiaryEntryList().get(entry).getEntry());
                            //System.out.println(entry.getDiaryEntryList().get(entry).getLocalDate());
                            //System.out.println(entry.getEntry());
                            //System.out.println(entry.getLocalDate());
                            System.out.println("--------------------------");
                        }
                    } else if (choice == 2) {

                        //Path path = Paths.get("src/main/resources/diaryEntry.json");
                        input.nextLine();
                        System.out.println("Fyll i titeln på ditt inlägg: ");
                        String choiceOfTitle = input.nextLine();
                        System.out.println("Skriv ditt inlägg: ");
                        // TODO lägga till om det är någon som skriver ett långt inlägg
                        String choiceOfEntry = input.nextLine();
                        DiaryEntry newDiaryEntry = new DiaryEntry(choiceOfUserInList, choiceOfTitle, choiceOfEntry, localDate);
                        List <DiaryEntry> tempFromJSON =
                                (List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class)));

                        diaryEntries.addAll(tempFromJSON);
                        diaryEntries.add(newDiaryEntry);
                        /*System.out.println(newDiaryEntry.getUser());
                        System.out.println(newDiaryEntry.getDiaryEntryList());
                        System.out.println(newDiaryEntry.getDiaryEntryList().get(0).getTitle());*/

                        /*System.out.println(newDiaryEntry.getTitle());
                        System.out.println(newDiaryEntry.getEntry());
                        System.out.println(newDiaryEntry.getLocalDate());*/

                        mapper.writeValue(pathTwo.toFile(),diaryEntries);

                    } else if (choice == 3) {
                        System.out.println("Hejdå, tack för idag! Programmet avslutas");
                    }
                } else {
                    System.out.println("Du valde ingen användare, välkommen tillbaka till huvudmenyn");
                }

            } else if (choiceOfUser == 2) {
                input.nextLine();
                System.out.println("Var god skriv i ditt nya användarnamn");
                String choiceOfNewUser = input.nextLine();
                User newUser = new User(choiceOfNewUser);
                List<User> listOfNewUser = List.of(mapper.readValue(pathOne.toFile(), User[].class));
                usersList.addAll(listOfNewUser);
                usersList.add(newUser);
                mapper.writeValue(pathOne.toFile(), usersList);
                System.out.println("Ditt användarnamn är nu sparat, programmet återgår till huvudmenyn igen, där kan du välja din nya användare.");
            } else {
                System.out.println("Hejdå, tack för idag! Programmet avslutas");
            }
        } while (choiceOfUser != 3);
        //mapper.writeValue(Paths.get("src/main/resources/users.json").toFile(), Users.class);



   /* if (true) {
        MainMenu.menu2(choiceOfUserInList);
        int choice = input.nextInt();

        Path pathTwo = Paths.get("src/main/resources/diaryEntry.json");

        if (choice == 1) {
            List<DiaryEntry> diaryEntryList1 =
                    List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class));

            for (DiaryEntry entry : diaryEntryList1) {
                System.out.println(entry.getTitle());
                System.out.println(entry.getEntry());
                System.out.println(entry.getLocalDate());
                System.out.println("--------------------------");
            }
        } else if (choice == 2) {

            //Path path = Paths.get("src/main/resources/diaryEntry.json");
            input.nextLine();
            System.out.println("Fyll i titeln på ditt inlägg: ");
            String choiceOfTitle = input.nextLine();
            System.out.println("Skriv ditt inlägg: ");
            // TODO lägga till om det är någon som skriver ett långt inlägg
            String choiceOfEntry = input.nextLine();
            DiaryEntry newDiaryEntry = new DiaryEntry(choiceOfTitle, choiceOfEntry, localDate);
            List <DiaryEntry> tempFromJSON =
                    (List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class)));
            diaryEntries.addAll(tempFromJSON);
            diaryEntries.add(newDiaryEntry);

            System.out.println(newDiaryEntry.getTitle());
            System.out.println(newDiaryEntry.getEntry());
            System.out.println(newDiaryEntry.getLocalDate());

            mapper.writeValue(pathTwo.toFile(),diaryEntries);

        } else if (choice == 3) {
            System.out.println("Hejdå, tack för idag! Programmet avslutas");
        }*/



/*static void writeNewDiaryEntry() throws IOException {
    Scanner input = new Scanner(System.in);
    LocalDate localDate = LocalDate.now();
    ObjectMapper mapper = new ObjectMapper();
    List<DiaryEntry> diaryEntries = new ArrayList<>();
    Path path = Paths.get("src/main/resources/diaryEntry.json");
    input.nextLine();
    System.out.println("Fyll i titeln på ditt inlägg: ");
    String choiceOfTitle = input.nextLine();
    System.out.println("Skriv ditt inlägg: ");
    // TODO lägga till om det är någon som skriver ett långt inlägg
    String choiceOfEntry = input.nextLine();
    DiaryEntry newDiaryEntry = new DiaryEntry(choiceOfTitle, choiceOfEntry, localDate);
    List <DiaryEntry> tempFromJSON =
            (List.of(mapper.readValue(path.toFile(), DiaryEntry[].class)));
    diaryEntries.addAll(tempFromJSON);
    diaryEntries.add(newDiaryEntry);

    System.out.println(newDiaryEntry.getTitle());
    System.out.println(newDiaryEntry.getEntry());
    System.out.println(newDiaryEntry.getLocalDate());

    mapper.writeValue(path.toFile(),diaryEntries);

   /* static void writeAndReadValue(List<DiaryEntry> diaryEntries) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diaryEntries);

    }*/
}}
