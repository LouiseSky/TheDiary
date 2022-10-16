package model;

public class MainMenu {
    public static void menu1(String name) {
        System.out.println("Aktiv användare: " + name);
        System.out.println("1. Välj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avsluta programmet");
    }
    public static void menu2(String name) {
            System.out.println("Aktiv användare: " + name);
            System.out.println("Du har tre olika val");
            System.out.println("1. Läsa din dagbok ");
            System.out.println("2. Skriva inlägg ");
            System.out.println("3. Avsluta programmet");
            System.out.println("Var god välj: 1, 2 eller 3.");
    }
public static int main() {
    System.out.println("Välkommen till menu 2");
    return 2;
}
}
