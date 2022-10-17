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

    public User() {
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
    public static void chooseNewUser(ObjectMapper mapper, Scanner input) throws IOException {
        Path pathOne = Paths.get("src/main/resources/users.json");

        System.out.println("Var god skriv i ditt nya användarnamn");
        input.nextLine();
        String choiceOfNewUser = input.nextLine();

        User newUser = new User(choiceOfNewUser);
        List<User> usersList = new ArrayList<>(List.of(mapper.readValue(pathOne.toFile(), User[].class)));

        usersList.add(newUser);

        mapper.writeValue(pathOne.toFile(), usersList);
        System.out.println("Ditt användarnamn är nu sparat. " +
                "Programmet återgår till huvudmenyn igen där du kan välja din användare.");
    }
}
