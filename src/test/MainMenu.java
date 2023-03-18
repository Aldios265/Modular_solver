package test;

import java.util.Scanner;

public class MainMenu {

    public static void printHeader(String name) {
        System.out.println("------------");
        System.out.println(name);
        System.out.println("------------");

    }
    public static void printChoices(String[] possibleOptions) {
        for (int i = 0; i < possibleOptions.length; i++) {
            System.out.println((i + 1) + ") " + possibleOptions[i]);
        }
        System.out.println("Для выбора введите номер опции:");
    }


    public static int userInput(int length) {
        int input;
        Scanner scanner = new Scanner(System.in);
        try {
            input = Integer.parseInt(scanner.next());
        } catch (Exception e) {
            throw new IllegalArgumentException("Введен неверный символ, ожидался номер опции");
        }
        if ((input > 0) && (input < length)) {
            return input;
        } else {
            throw new IllegalArgumentException("Введен неверный номер опции");
        }
    }



    public static int mainMenu(MenuCartridge menuCartridge) {

        //----------------------
        String treeName = "MainMenu";
        String[] tree = {"ChooseProgram", "Settings", "Info", "Back"};

        String name = "ModularSolver\nГлавное меню";
        String[] possibleOptions = {"Выбрать программу", "Настройки", "Информация", "Назад"};
        //------------------

        printHeader(name);
        printChoices(possibleOptions);
        return userInput(possibleOptions.length);
    }


}


