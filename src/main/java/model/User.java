package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private static String currentUserName;
    private static final Path pathUsers = Paths.get("src/main/resources/users.json");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner input = new Scanner(System.in);

    public User() {
    }

    /**
     *
     * @throws IOException
     */
    public static void showUsersInList() throws IOException {

        System.out.println("Lista över användare: ");

        List<User> listOfUsers = List.of(mapper.readValue(pathUsers.toFile(), User[].class));

        for (User user : listOfUsers) {
            System.out.println(user.getName());
        }
    }

    public static void chooseUserInList() {
        System.out.println("Var god välj en av användarna i listan. Om du inte vill ha någon " +
                "av användarna i listan, skriv " + "-" + " så kommer du tillbaka till huvudmenyn");
    }

    /**
     * Method for choosing a new user.
     * @throws IOException because list is transfer to JSON-file
     */
    public static void chooseNewUser() throws IOException {
        List<User> usersList = new ArrayList<>(List.of(mapper.readValue(pathUsers.toFile(), User[].class)));
        System.out.println("Var god skriv i ditt nya användarnamn");
        input.nextLine();
        String choiceOfNewUser = input.nextLine();

        boolean userNameNotAvailable = false;
        for (User user : usersList) {
            if (choiceOfNewUser.equalsIgnoreCase(user.getName())) {
                System.out.println("Användaren finns redan, du måste välja ett nytt namn.");
                userNameNotAvailable = true;
                break;
            }
        }
        if (!userNameNotAvailable) {
            User newUser = new User(choiceOfNewUser);
            usersList.add(newUser);

            mapper.writeValue(pathUsers.toFile(), usersList);
            System.out.println("Ditt användarnamn " + choiceOfNewUser + " är nu sparat. " +
                    "Programmet återgår till huvudmenyn igen där du kan välja din användare.");
        }
    }

    public static void noMatchingUserInList() {
        System.out.println("Du valde ingen befintlig användare i listan.");
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        User.currentUserName = currentUserName;
    }

}
