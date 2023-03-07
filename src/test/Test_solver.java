package test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Test_solver {

    static double n;
    static double n0;
    static double k;
    static double t;

    static double tHalf;
    static String toFind;
    static int numberOfVariables;

    static String[] boundaryArray = {"k", "tHalf"};
    static ArrayList<String> boundary = new ArrayList<>(Arrays.asList(boundaryArray));
    static byte numberOfBoundedVariables;


    public static void parcer() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, t, k, t(1/2)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] outputString = input.split(",");

        if (outputString.length == 5) {
            try {
                n = Double.parseDouble(outputString[0]);
                if (boundary.contains("n")) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            } catch (Exception e) {
                toFind = "n";
            }

            try {
                n0 = Double.parseDouble(outputString[1]);
                if (boundary.contains("n0")) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            } catch (Exception e){
                toFind = "n0";
            }

            try {
                t = Double.parseDouble(outputString[2]);
                if (boundary.contains("t")) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            } catch (Exception e){
                toFind = "t";

            }
            try {
                k = Double.parseDouble(outputString[3]);
                if (boundary.contains("k")) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            } catch (Exception e){
                toFind = "k";
            }

            try {
                tHalf = Double.parseDouble(outputString[4]);
                if (boundary.contains("tHalf")) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            } catch (Exception e){
                toFind = "tHalf";
            }

        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }

        if (numberOfBoundedVariables == 2) {
            throw new IllegalArgumentException("переменные k и t(1/2) не могут быть заданы одновременно");
        } else if (numberOfVariables + ((numberOfBoundedVariables == 1) ? 1 : 0 ) != 3) {
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
                if (numberOfBoundedVariables == 1) {
                    k = Math.log(2) / tHalf;
                } else {
                    k = -(Math.log(n/n0) / t);
                    tHalf = Math.log(2) / k;
                }

            case "tHalf":
                if (numberOfBoundedVariables == 1) {
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
}

