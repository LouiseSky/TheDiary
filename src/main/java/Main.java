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

        do {
            if (startMenu == 1) {
                try {
                    MainMenu.menuOne(userStartMenu);
                    int choiceMenuOne = input.nextInt();

                    if (choiceMenuOne == 1) {
                        User.showUsersInList();
                        input.nextLine();
                        User.chooseUserInList();

                        String choiceOfUserInList = input.nextLine();

                        listOfUsers = List.of(mapper.readValue(pathUsers.toFile(), User[].class));

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
                            User.noMatchingUserInList();
                        }
                    }
                    if (choiceMenuOne == 2) {
                        User.chooseNewUser();
                    }
                    if (choiceMenuOne == 3) {
                        MainMenu.exitProgram();
                        break;
                    }
                } catch (InputMismatchException e) {
                    showMessageIfItIsAnException();
                    input.nextLine();
                }
            }
            try {
                if (startMenu == 2) {
                    MainMenu.menuTwo(User.getCurrentUserName());
                    int choiceMenuTwo = input.nextInt();

                    if (choiceMenuTwo == 1) {
                        DiaryEntry.readListOfDiaryEntries();
                    } else if (choiceMenuTwo == 2) {
                        DiaryEntry.writeNewDiaryEntry(User.getCurrentUserName());
                    } else {
                        MainMenu.exitProgramAddUsername();
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                showMessageIfItIsAnException();
                input.nextLine();
            }
        } while (true);
    }

    private static void showMessageIfItIsAnException() {
        System.out.println("Nu blev det fel, du behöver skriva in en siffra mellan 1-3.");
    }
}
