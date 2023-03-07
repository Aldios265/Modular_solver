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


    public static void communicator() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, k, t, t(1/2)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] outputString = input.split(",");
        if (outputString.length == 5) {

            try {
                n = Integer.parseInt(outputString[0]);
            } catch (Exception e){
                toFind = "n";
                if (boundary.contains(toFind)) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            }

            try {
                n0 = Integer.parseInt(outputString[1]);
            } catch (Exception e){
                toFind = "n0";
                if (boundary.contains(toFind)) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            }

            try {
                k = Integer.parseInt(outputString[2]);
            } catch (Exception e){
                toFind = "k";
                if (boundary.contains(toFind)) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            }
            try {
                t = Integer.parseInt(outputString[3]);
            } catch (Exception e){
                toFind = "t";
                if (boundary.contains(toFind)) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            }

            try {
                tHalf = Integer.parseInt(outputString[4]);
            } catch (Exception e){
                toFind = "tHalf";
                if (boundary.contains(toFind)) {
                    numberOfBoundedVariables++;
                } else {
                    numberOfVariables++;
                }
            }
            System.out.println(toFind);



        } else {
//            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
        if (true) {
            System.out.println(solver());
        } else {
//            throw new IllegalArgumentException("Неверное количество неизвестных");
        }


    }


    public static String solver() {
        switch (toFind) {
            case "n":
                n = n0 * Math.exp(-k * t);

            case "n0":
                n0 = n / Math.exp(-k * t);

            case "k":
                if (numberOfBoundedVariables == 1) {
                    k = Math.log(2) / tHalf;
                } else {
                    k = -(Math.log(n/n0) / t);
                    tHalf = Math.log(2) / k;
                }

            case "t":
                n = n0 * Math.exp(-k * t);

            case "tHalf":
                if (numberOfBoundedVariables == 1) {
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

