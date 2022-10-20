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

        Path pathUsers = Paths.get("src/main/resources/users.json");

        List<User> listOfUsers;

        String userStartMenu = "Ingen";

        int startMenu = 1;
        // TODO Lägg till JAVADOC

        do {
            if (startMenu == 1) {
                try {
                    MainMenu.menuOne(userStartMenu);
                    int choiceMenuOne = input.nextInt();

                    if (choiceMenuOne == 1) {
                        System.out.println("Lista över användare: ");

                        listOfUsers = List.of(mapper.readValue(pathUsers.toFile(), User[].class));
                        for (User user : listOfUsers) {
                            System.out.println(user.getName());
                        }
                        input.nextLine();

                        System.out.println("Var god välj en av användarna i listan. Om du inte vill ha någon " +
                                "av användarna i listan, skriv " + "-" + " så kommer du tillbaka till huvudmenyn");
                        String choiceOfUserInList = input.nextLine();

                        boolean matchingUser = false;
                        for (User user : listOfUsers) {
                            if (choiceOfUserInList.equalsIgnoreCase(user.getName())) {
                                User.setCurrentUserName(user.getName());
                                matchingUser = true;
                                startMenu = 2;
                                break;
                            }
                        }
                        if (!matchingUser) {
                            System.out.println("Du valde ingen befintlig användare i listan.");
                        }
                    }
                    if (choiceMenuOne == 2) {
                        User.chooseNewUser(mapper, input);
                    }
                    if (choiceMenuOne == 3) {
                        System.out.println("Hejdå, tack för idag! Programmet avslutas.");
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Nu blev det fel, du behöver skriva in en siffra mellan 1-3.");
                    input.nextLine();
                }
            }
            try {
                if (startMenu == 2) {
                    MainMenu.menuTwo(User.getCurrentUserName());
                    int choiceMenuTwo = input.nextInt();

                    if (choiceMenuTwo == 1) {
                        DiaryEntry.readListOfDiaryEntries(mapper);
                    }
                    if (choiceMenuTwo == 2) {
                        DiaryEntry.writeNewDiaryEntry(mapper, input, User.getCurrentUserName());
                    }
                    if (choiceMenuTwo == 3) {
                        System.out.println("Tack för idag " + User.getCurrentUserName() + "! Programmet avslutas.");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Nu blev det fel, du behöver skriva in en siffra mellan 1-3.");
                input.nextLine();
            }
        } while (true);
    }
}
