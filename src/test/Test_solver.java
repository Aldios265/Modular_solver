package test;

import java.util.Scanner;

public class Test_solver {
    static double n;
    static double n0;
    static double k;
    static double t;
    static double tHalf;
    static String toFind;
    static int numberOfVariables;
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
                numberOfVariables++;
            }
            try {
                n0 = Integer.parseInt(outputString[1]);
            } catch (Exception e){
                toFind = "n0";
                numberOfVariables++;
            }
            try {
                k = Integer.parseInt(outputString[2]);
            } catch (Exception e){
                toFind = "k";
                numberOfVariables++;
            }
            try {
                t = Integer.parseInt(outputString[3]);
            } catch (Exception e){
                toFind = "t";
                numberOfVariables++;
            }
            try {
                tHalf = Integer.parseInt(outputString[4]);
            } catch (Exception e){
                toFind = "tHalf";
                numberOfVariables++;
            }

            System.out.println(toFind);

        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
        if (numberOfVariables != 1) {
            throw new IllegalArgumentException("Неверное количество неизвестных");
        }


    }

    public static void solver() {

    }

}

