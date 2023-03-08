package test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Calculator_test1 {

    //Главные переменные данного класа, используются в методе solver() для рассчета резульатата.
    static double n;
    static double n0;
    static double k;
    static double t;
    static double tHalf;

    //Общее количчество переменных.
    static byte numberOfVariables = 5;

    //массив строчных литералов переменных.
    static String[] parametersStringArray = {"n", "n0", "t", "k", "tHalf"};
    //Количество массив численных литералов переменных.
    static Object[] parametersArray = new Object[numberOfVariables];

    //Переменная toFind1 используется в методе distribution() для выбора ветки рассчета для метода solver().
    static String toFind1;
    static String toFind2;

    //Переменная equationCode имеет стандартное значение 0, значения соответствуют описанным ниже случаям.
    static byte equationCode1 = 0;
    static byte equationCode2 = 0;

    //Массивы литералов главных переменных по уравнениям.
    static String[] equation1 = {"n", "n0", "t", "k"};
    static String[] equation2 = {"k", "tHalf"};

    /*Массивы литералов используются для выбора втки рассчета для метода solver().
    Пользователь вводит значения параметров в методе input(), вместо значений, которые необходимо найти по условию задачи
    пользователь ставит знак "-" либо другой знак, который будет при действии на него метода Double.toDoubleDouble()
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


    public static void output() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, t, [k, t(1/2)]");
    }

    /*метод input() преобразует строку, введенную пользователем в массив строк, разделенных запятой
    а также проводит валидацию строки, длинна строки должна соответствовать аргументу функции*/
    public static String[] input(byte numberOfVariables) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] inputArray = input.split(",");
        if (inputArray.length == numberOfVariables) {
            return inputArray;
        } else {
            throw new IllegalArgumentException("Введено неверное количество параметров");
        }
    }


    /* Метод toDouble() преобразует массив строк в массив объектов. Объекты,
    преобразующиеся в double с момощью Double.parceDouble() метод записывает в массив как double datatype, остальные оставляет такими какие они есть */

    public static Object[] toDouble(String[] initialArray) {
        Object[] objectDoubleArray = new Object[initialArray.length];
            for (int i = 0; i < numberOfVariables; i++) {
                try {
                    objectDoubleArray[i] = Double.parseDouble(initialArray[i]);
                } catch (Exception e) {
                    objectDoubleArray[i] = initialArray[i];
                }
            }
        return objectDoubleArray;
    }

/* метод equationCodeDetermine() рассчитывает и возвращает equationCode данного уравнения, в качестве параметров он берет
 основной массив строк основных переменных, */
    public static byte equationCodeDetermine(String[] equationArray, String[] generalArray, Object[] objectArray) {
        byte equationCode = 0;
        ArrayList<String> equationList = new ArrayList<>(Arrays.asList(equationArray));
        for (int i = 0; i < generalArray.length; i++) {
            if (!((Object) objectArray[i].getClass().getName() == "java.lang.Double")
                    && (equationList.contains(generalArray[i]))) {
                equationCode++;
            }
        }
        return equationCode;
    }


    public static void solverMain() {
        output();
        Object[] objectDoubleArray = toDouble(input(numberOfVariables));
        System.out.println(equationCodeDetermine(equation1, parametersStringArray, objectDoubleArray));
        System.out.println(equationCodeDetermine(equation2, parametersStringArray, objectDoubleArray));
    }

}
