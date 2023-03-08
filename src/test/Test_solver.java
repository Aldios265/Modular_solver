package test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Test_solver {

    //Главные переменные данного класа, используются в методе solver() для рассчета резульатата.
    static double n;
    static double n0;
    static double k;
    static double t;
    static double tHalf;

    //Переменная toFind используется в методе distribution() для выбора ветки рассчета для метода solver().
    static String toFind;

    //Массивы литералов главных переменных по уравнениям.
    //Используются в методе distribution() для выбора втки рассчета для метода solver().
    static String[] equation1 = {"n", "n0", "t", "k"};
    static String[] equation2 = {"k", "tHalf"};

    //Перевод массива в ArrayList<> для использования метода .contains() в методе distribution().
    static ArrayList<String> equation1List = new ArrayList<>(Arrays.asList(equation1));
    static ArrayList<String> equation2List = new ArrayList<>(Arrays.asList(equation2));


    static String[] argumentsArray = {"n", "n0", "t", "boundary1"};
    static String[] boundary11Array = {"k", "tHalf"};

    //Перевод массива в ArrayList<> дял использования метода .contains() в методе distribution().
    static ArrayList<String> boundary1 = new ArrayList<>(Arrays.asList(boundary11Array));

    //Переменные количества введенных пользователем аргументов(количество параметров - количество неизвестных).
    //Используются в методе validation для оценки правилоности введенных пользователем данных.
    static byte numberOfArguments;
    static byte numberOfArgumentsBoundary1;
    static byte numberOfParameters;


    public static void output() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, t, [k, t(1/2)]");
    }


    public static String[] input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] outputArray = input.split(",");
        return outputArray;
    }
    
    
    public static void distribution1(String[] outputArray) {
        if (outputArray.length == numberOfParameters) {
            try {

            }


        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
    }

    public static void distribution(String[] outputArray) {
        if (outputArray.length == 5) {
            try {
                n = Double.parseDouble(outputArray[0]);
                if (boundary1.contains("n")) {
                    numberOfArgumentsBoundary1++;
                } else {
                    numberOfArguments++;
                }
            } catch (Exception e) {
                toFind = "n";
            }

            try {
                n0 = Double.parseDouble(outputArray[1]);
                if (boundary1.contains("n0")) {
                    numberOfArgumentsBoundary1++;
                } else {
                    numberOfArguments++;
                }
            } catch (Exception e) {
                toFind = "n0";
            }

            try {
                t = Double.parseDouble(outputArray[2]);
                if (boundary1.contains("t")) {
                    numberOfArgumentsBoundary1++;
                } else {
                    numberOfArguments++;
                }
            } catch (Exception e) {
                toFind = "t";

            }
            try {
                k = Double.parseDouble(outputArray[3]);
                if (boundary1.contains("k")) {
                    numberOfArgumentsBoundary1++;
                } else {
                    numberOfArguments++;
                }
            } catch (Exception e) {
                toFind = "k";
            }

            try {
                tHalf = Double.parseDouble(outputArray[4]);
                if (boundary1.contains("tHalf")) {
                    numberOfArgumentsBoundary1++;
                } else {
                    numberOfArguments++;
                }
            } catch (Exception e) {
                toFind = "tHalf";
            }

        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
    }

    public static void validation() {
        if (numberOfArgumentsBoundary1 == 2) {
            throw new IllegalArgumentException("переменные k и t(1/2) не могут быть заданы одновременно");
        } else if (numberOfArguments + ((numberOfArgumentsBoundary1 == 1) ? 1 : 0 ) != 3) {
            throw new IllegalArgumentException("Неверное количество аргументов.(параметры k и t(1/2) не являются неизвестными " +
                    "в случае если хотябы один из них является аргументом)");
        } else {
            System.out.println(solver());
        }
    }


    public static String solver() {
        switch (toFind) {
            case "n":
                n = n0 * Math.exp(-k * t);

            case "n0":
                n0 = n / Math.exp(-k * t);

            case "t":
                n = n0 * Math.exp(-k * t);

            case "k":
                if (numberOfArgumentsBoundary1 == 1) {
                    k = Math.log(2) / tHalf;
                } else {
                    k = -(Math.log(n/n0) / t);
                    tHalf = Math.log(2) / k;
                }

            case "tHalf":
                if (numberOfArgumentsBoundary1 == 1) {
                    tHalf = Math.log(2) / k;
                } else {
                    k = -(Math.log(n/n0) / t);
                    tHalf = Math.log(2) / k;
                }
        }

        try {
            return(String.format("Ответ: n = %f, n0 = %f, t = %f, k = %f, t(1/2) = %f", n, n0, t, k, tHalf));
        } catch (Exception e) {
            return "Ответ не найден";
        }
    }

    public static void solverMain() {
        output();
        distribution(input());
        validation();
        solver();
    }
}

