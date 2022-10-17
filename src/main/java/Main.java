import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.DiaryEntry;
import model.MainMenu;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Scanner input = new Scanner(System.in);

        Path pathOne = Paths.get("src/main/resources/users.json");
        Path pathTwo = Paths.get("src/main/resources/diaryEntry.json");

        List<DiaryEntry> listOfDiaryEntry;
        List<User> listOfUsers;

        String userStartMenu = "Ingen";
        String choiceOfUserInList;

        int startMenu = 1;

        do {
            if(startMenu == 1) {
                try {
                MainMenu.menuOne(userStartMenu);
                int choiceMenuOne = input.nextInt();

                    if (choiceMenuOne == 1) {
                        System.out.println("Lista över användare: ");

                        listOfUsers = List.of(mapper.readValue(pathOne.toFile(), User[].class));
                        for (User user : listOfUsers) {
                            System.out.println(user.getName());
                        }
                        input.nextLine();
                        System.out.println("Var god välj en av användarna i listan. Om du inte vill ha någon " +
                                "av användarna i listan, skriv X");
                        choiceOfUserInList = input.nextLine();

                        for (User user : listOfUsers) {
                            if (choiceOfUserInList.equalsIgnoreCase(user.getName())) {
                                User.setCurrentUserName(user.getName());
                                startMenu = 2;
                                break;
                            }
                        }
                    }if (choiceMenuOne == 2) {
                        User.chooseNewUser(mapper, input);
                    }if (choiceMenuOne == 3) {
                        System.out.println("Hejdå, tack för idag! Programmet avslutas");
                        break;
                    }
                } catch(InputMismatchException e) {
                    System.out.println("Nu blev det fel.");
                    input.nextLine();
                }
            }
            try {
                if (startMenu == 2) {
                    MainMenu.menuTwo(User.getCurrentUserName());
                    int choiceMenuTwo = input.nextInt();

                    if (choiceMenuTwo == 1) {
                        listOfDiaryEntry = List.of(mapper.readValue(pathTwo.toFile(), DiaryEntry[].class));
                        for (DiaryEntry diaryEntry : listOfDiaryEntry) {
                            if (User.getCurrentUserName().equalsIgnoreCase(String.valueOf(diaryEntry.getName()))) {
                                DiaryEntry.showDiaryEntry(diaryEntry.getTitle(), diaryEntry.getEntry(), diaryEntry.getLocalDate());
                            }
                        }
                    }
                    if (choiceMenuTwo == 2) {
                        DiaryEntry.writeNewDiaryEntry(mapper, input, User.getCurrentUserName());
                    }
                    if (choiceMenuTwo == 3) {
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Du skrev in något fel.");
                input.nextLine();
            }
        } while (true);
    }
}
