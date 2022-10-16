import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
        mapper.registerModule(new JavaTimeModule());
        Scanner input = new Scanner(System.in);
        LocalDate localDate = LocalDate.now();
        Path pathOne = Paths.get("src/main/resources/users.json");
        Path pathTwo = Paths.get("src/main/resources/diaryEntry.json");


        List<DiaryEntry> diaryEntries = new ArrayList<>();
        List<DiaryEntry> diaryEntryList1;
        List<DiaryEntry> tempFromJSON;
        List<User> usersList1;
        List<User> usersList = new ArrayList<>();
        List<User> listOfNewUser;
        String choiceOfUserInList;
        String currentUser;

        int startMenu = 1;

        do {
            if(startMenu == 1) {
                MainMenu.menu1("Ingen");
                int choiceOfUserMenuOne = input.nextInt();

                if (choiceOfUserMenuOne == 1) {
                    System.out.println("Lista över användare: ");

                    usersList1 = List.of(mapper.readValue(pathOne.toFile(), User[].class));
                    for (User user : usersList1) {
                        System.out.println(user.getName());
                    }
                    input.nextLine();
                    System.out.println("Var god välj en av användarna i listan, skriv namnet.");
                    choiceOfUserInList = input.nextLine();


                    for (User user : usersList1) {
                        System.out.println(user.getName());
                        if(choiceOfUserInList.equalsIgnoreCase(user.getName())) {
                            User.setCurrentUserName(user.getName());
                            startMenu = 2;
                            break;
                        }
                        else {
                            System.out.println(user.getName());
                            System.out.println("Du valde ingen befintlig användare i listan. " +
                                    "Välkommen tillbaka till huvudmenyn");
                            break;
                        }

                        /*if (user.getName().equalsIgnoreCase(choiceOfUserInList)) {
                            System.out.println(user.getName());
                            User.setCurrentUserName(user.getName());
                            startMenu = 2;
                            break;
                        } else {
                            System.out.println(user.getName());
                            System.out.println("Du valde ingen befintlig användare i listan. " +
                                    "Välkommen tillbaka till huvudmenyn");
                            break;
                        }*/

                    }
                }

                if (choiceOfUserMenuOne == 2) {
                   chooseNewUser(mapper, input);
                }
                if(choiceOfUserMenuOne == 3){
                    System.out.println("Hejdå, tack för idag! Programmet avslutas");
                    break;
                }
            }
            if(startMenu == 2) {

                    MainMenu.menu2(User.getCurrentUserName());
                    int choiceMenuTwo = input.nextInt();

                    if (choiceMenuTwo == 1) {
                        diaryEntryList1 = List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class));
                        for (DiaryEntry diaryEntry : diaryEntryList1) {
                            if (User.getCurrentUserName().equalsIgnoreCase(String.valueOf(diaryEntry.getName()))) {
                                //DiaryEntry.showDiaryEntry(diaryEntry.getTitle(), diaryEntry.getEntry(), diaryEntry.getLocalDate());
                                /*System.out.println(diaryEntry.getName());
                                System.out.println(diaryEntry.getTitle());
                                System.out.println(diaryEntry.getEntry());
                                System.out.println(diaryEntry.getLocalDate());*/
                            } else {
                                System.out.println("Du har  inte skrivit några inlägg, välj 2 i huvudmenyn för att " +
                                        "skriva ett inlägg!");
                            }
                        }
                    } else if (choiceMenuTwo == 2) {
                        writeNewDiaryEntry(mapper,input, User.getCurrentUserName());

                    } else if (choiceMenuTwo == 3) {
                        break;
                    }
                }
        } while (startMenu != 3);
    }

    static void chooseNewUser(ObjectMapper mapper, Scanner input) throws IOException {
        List<User> listOfNewUser;
        Path pathOne = Paths.get("src/main/resources/users.json");

        System.out.println("Var god skriv i ditt nya användarnamn");
        input.nextLine();
        String choiceOfNewUser = input.nextLine();
        User newUser = new User(choiceOfNewUser);
        listOfNewUser = List.of(mapper.readValue(pathOne.toFile(), User[].class));
        List<User> usersList = new ArrayList<>(listOfNewUser);
        usersList.add(newUser);
        mapper.writeValue(pathOne.toFile(), usersList);
        System.out.println("Ditt användarnamn är nu sparat. " +
                "Programmet återgår till huvudmenyn igen där du kan välja din användare.");
    }

    static void writeNewDiaryEntry(ObjectMapper mapper, Scanner input, String name) throws IOException {
        mapper.registerModule(new JavaTimeModule());
        LocalDate localDate = LocalDate.now();
        Path pathTwo = Paths.get("src/main/resources/diaryEntry.json");

        List<DiaryEntry> tempFromJSON;

        System.out.println("Fyll i titeln på ditt inlägg: ");
        String choiceOfTitle = input.nextLine();
        System.out.println("Skriv ditt inlägg: ");
        // TODO lägga till om det är någon som skriver ett långt inlägg
        String choiceOfEntry = input.nextLine();
        DiaryEntry newDiaryEntry =
                new DiaryEntry(name, choiceOfTitle, choiceOfEntry, localDate);
        tempFromJSON = List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class));

        List<DiaryEntry> diaryEntries = new ArrayList<>(tempFromJSON);
        diaryEntries.add(newDiaryEntry);
        DiaryEntry.showDiaryEntry(choiceOfTitle, choiceOfEntry, localDate);
        mapper.writeValue(pathTwo.toFile(), diaryEntries);

    }
}
