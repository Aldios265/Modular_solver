package menu;

import java.util.Arrays;
import java.util.Scanner;

/*Данный класс, а точнее метод menuExecutor активирует подмассив окна меню,
 помещенный в него в качестве параметра,
и выводит в консоль соотвесттвующие данному объекту сообщения и считывает ответ пользователя.
* Данный класс является универсальным и может автивировать любой подобный подмассив */
public class MenuExecutor {

    /*Данный метод выводит в консоль заголовок меню*/
    public static void printHeader(String name) {
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Modular_Menu");
        System.out.println("------------");
        System.out.println(name);
        System.out.println("------------");


    }
    /* Данный метод выводит в консоль все варианты выбора, соответствующие данному меню вместе с их индексами,
     * к которым прибавляется еденица(user friendly)*/
    public static void printChoices(String[] possibleOptions) {
        for (int i = 0; i < possibleOptions.length; i++) {
            System.out.println((i + 1) + ") " + possibleOptions[i]);
        }
        System.out.println("Для выбора введите номер опции:");
    }

    /* Данный метод считывает введенное пользователем в консоль значение а также
     * в случае неправильности ввода выводит некоторые ошибки*/
    public static int userInput(int length) {
        int input;
        Scanner scanner = new Scanner(System.in);
        try {
            input = Integer.parseInt(scanner.next());
            System.out.println("\n\n\n");
        } catch (Exception e) {
            throw new IllegalArgumentException("Введен неверный символ, ожидался номер опции");
        }
        if ((input > 0) && (input <= length)) {
            return input;
        } else {
            throw new IllegalArgumentException("Введен неверный номер опции");
        }
    }

    /* Данный метод проверяет, является ли данная часть массива меню функциональной опцией*/
    public static boolean checkIfOption(Object[] objectArray) {
        for (Object i: objectArray) {
            if (!i.getClass().getName().equals("java.lang.String")) {
                return true;
            }
        }
        return false;
    }

    public static String[] convertToStringArray(Object[] objectArray) {
        String[] stringArray = new String[objectArray.length];
        if (!checkIfOption(objectArray)) {
            for (int i = 0; i < objectArray.length; i++) {
                stringArray[i] = objectArray[i].toString();
            }
        }
        return stringArray;
    }


    /*Главный метод данного класса*/
    public static String mainExecutor(Object[] menuOptionArray) {

        /*Считывание полей окна меню с помощью get-методов*/
        String name = menuOptionArray[0].toString();
        String[] options = convertToStringArray(Arrays.copyOfRange(menuOptionArray, 2, menuOptionArray.length));



        /* Активация методов данного класса */
        printHeader(name);
        printChoices(options);
        return options[userInput(options.length) - 1];

    }

}




