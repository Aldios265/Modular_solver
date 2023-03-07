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
    static byte numberOfBoundedUnknowns;


    public static void communicator() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, k, t, t(1/2)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] outputString = input.split(",");

        if (outputString.length == 5) {

            try {
                n = Double.parseDouble(outputString[0]);
            } catch (Exception e){
                toFind = "n";
                if (boundary.contains(toFind)) {
                    numberOfBoundedUnknowns++;
                } else {
                    numberOfVariables++;
                }
            }

            try {
                n0 = Double.parseDouble(outputString[1]);
            } catch (Exception e){
                toFind = "n0";
                if (boundary.contains(toFind)) {
                    numberOfBoundedUnknowns++;
                } else {
                    numberOfVariables++;
                }
            }

            try {
                k = Double.parseDouble(outputString[2]);
            } catch (Exception e){
                toFind = "k";
                if (boundary.contains(toFind)) {
                    numberOfBoundedUnknowns++;
                } else {
                    numberOfVariables++;
                }
            }
            try {
                t = Double.parseDouble(outputString[3]);
            } catch (Exception e){
                toFind = "t";
                if (boundary.contains(toFind)) {
                    numberOfBoundedUnknowns++;
                } else {
                    numberOfVariables++;
                }
            }

            try {
                tHalf = Double.parseDouble(outputString[4]);
            } catch (Exception e){
                toFind = "tHalf";
                if (boundary.contains(toFind)) {
                    numberOfBoundedUnknowns++;
                } else {
                    numberOfVariables++;
                }
            }

        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
        System.out.println(numberOfBoundedUnknowns);
        System.out.println(numberOfVariables);
        if (numberOfBoundedUnknowns == 0) {
            throw new IllegalArgumentException("переменные k и t(1/2) не могут быть заданы одновременно");
        } else if (numberOfVariables + ((numberOfBoundedUnknowns == 1) ? 1 : 0 ) != 4) {
            throw new IllegalArgumentException("Неверное количество неизвестных");
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

            case "k":
                if (numberOfBoundedUnknowns == 1) {
                    k = Math.log(2) / tHalf;
                } else {
                    k = -(Math.log(n/n0) / t);
                    tHalf = Math.log(2) / k;
                }

            case "t":
                n = n0 * Math.exp(-k * t);

            case "tHalf":
                if (numberOfBoundedUnknowns == 1) {
                    tHalf = Math.log(2) / k;
                } else {
                    k = -(Math.log(n/n0) / t);
                    tHalf = Math.log(2) / k;
                }
        }

        try {
            return(String.format("Ответ: n = %f, n0 = %f, k = %f, t = %f, t(1/2) = %f", n, n0, k, t, tHalf));
        } catch (Exception e) {
            return "Ответ не найден";
        }

    }

}

