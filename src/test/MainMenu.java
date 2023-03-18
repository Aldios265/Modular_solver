package test;

import java.util.Scanner;

public class MainMenu {

    public static void printHeader() {
        System.out.println("------------");
        System.out.println("Modular solver v 1.1");
        System.out.println("------------");

    }
    public static void printChoices(Integer[] possibleInputs, String[] possibleOptions) {
        for (int i = 0; i < possibleInputs.length; i++) {
            System.out.println(possibleInputs[i] + ") " + possibleOptions[i]);
        }
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



    public static int mainMenu() {
        Integer[] possibleInputs = {1, 2, 3};
        String[] possibleOptions = {"Выбрать программу", "Настройки", "Информация"};
        printHeader();
        printChoices(possibleInputs, possibleOptions);
        return userInput(possibleInputs.length);
    }


}


