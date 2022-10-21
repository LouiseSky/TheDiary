package model;

public class MainMenu {
    public static void menuOne(String name) {
        System.out.println("Aktiv användare: " + name);
        System.out.println("1. Välj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avsluta programmet");
        System.out.println("Var god välj: 1, 2 eller 3.");
    }
public static void exitProgram() {
    System.out.println("Hejdå, tack för idag! Programmet avslutas.");
}

    /**
     * Shows menu two when a user is chosen.
     * @param name is a variable for the user.
     */
    public static void menuTwo(String name) {
        System.out.println("Aktiv användare: " + name);
        System.out.println("1. Läsa din dagbok");
        System.out.println("2. Skriva inlägg");
        System.out.println("3. Avsluta programmet");
        System.out.println("Var god välj: 1, 2 eller 3.");
    }
    public static void exitProgramAddUsername() {
        System.out.println("Tack för idag " + User.getCurrentUserName() + "! Programmet avslutas.");
    }
}
