package test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Test_solver1 {

    //Главные переменные данного класа, используются в методе solver() для рассчета резульатата.
    static double n;
    static double n0;
    static double k;
    static double t;
    static double tHalf;

    //Общее количчество переменных.
    static byte numberOfVariables = 5;

    //массив строчных литералов переменных.
    static String[] parametersStringArray = {"n", "n0", "k", "t", "tHalf"};
    //Количество массив численных литералов переменных.
    static Object[] parametersArray = new Object[numberOfVariables];

    //Переменная toFind1 используется в методе distribution() для выбора ветки рассчета для метода solver().
    static String toFind1;
    static String toFind2;

    //Переменная equationCode имеет стандартное значение 0, значения соответствуют описанным ниже случаям.
    static byte equation1Code;
    static byte equation2Code;

    //Массивы литералов главных переменных по уравнениям.
    static String[] equation1 = {"n", "n0", "t", "k"};
    static String[] equation2 = {"k", "tHalf"};

    /*Массивы литералов используются в методе distribution() для выбора втки рассчета для метода solver().
    Пользователь вводит значения параметров в методе input(), вместо значений, которые необходимо найти по условию задачи
    пользователь ставит знак "-" либо другой знак, который будет при действии на него метода Double.parceDouble()
    вызывать ошибку, и перенаправлять try - catch выражение на блок catch. Пример инпута пользователя: 2,3.4,-,2,-
    В зависимости от того, какие переменные пользователь введет можно выделить несколько случаев:
    equationCode = 0) Вс значения данного уравнения известны. Если они известны сразу после ввода значений пользователем
    то программа выдает ошибку: "Одно или несколько уравнений не имеют смысла"
    equationCode = 1) Известны все значения кроме одного.
    equationCode = 2) Известны все значение кроме двух

    equationCode = 3) ... кроме 3.
    ...
    equationCode = n) ... кроме n.
     */


    //Перевод массива в ArrayList<> для использования метода .contains() в методе distribution().
    static ArrayList<String> equation1List = new ArrayList<>(Arrays.asList(equation1));
    static ArrayList<String> equation2List = new ArrayList<>(Arrays.asList(equation2));


    static String[] argumentsArray = {"n", "n0", "t", "boundary1"};
    static String[] boundary11Array = {"k", "tHalf"};


    public static void output() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, t, [k, t(1/2)]");
    }

    public static String[] input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] inputArray = input.split(",");
        return inputArray;
    }

    public static void parse(String[] inputArray) {
        if (inputArray.length == numberOfVariables) {
            for (int i = 0; i < numberOfVariables; i++) {
                try {
                    parametersArray[i] = Double.parseDouble(inputArray[i]);
                } catch (Exception e) {
                    parametersArray[i] = inputArray[i];
                }
            }
        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
    }

    public static void solverMain() {
        output();
        parse(input());

    }

}