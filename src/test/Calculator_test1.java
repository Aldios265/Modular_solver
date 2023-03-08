package test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

    //Переменная equationCode имеет стандартное значение 0, значения соответствуют описанным ниже случаям.
    static int equationCode1 = 0;
    static int equationCode2 = 0;

    /*Массивы литералов главных переменных по уравнениям*/
    /*--Для написания нового калькулятора--*/
    static String[] equation1 = {"n", "n0", "t", "k"};
    final byte equationIdentifier1 = 1;
    /*------------------------------------*/
    static String[] equation2 = {"k", "tHalf"};
    final byte equationIdentifier2 = 2;
    /*------------------------------------*/
    //Массив equation-ов
    static String[][] multiEquationArray = {{"n", "n0", "t", "k"},{"k", "tHalf"}};
    //Массив порядковых номеров уравнений.
    static byte[] equationIndexArray = {0,1};
    //Массив equationCode-ов
    static byte[] equationCodeArray = new byte[2];


    /*Классы solver-ов*/
    /*Классы solver-ов*/
    /*Классы solver-ов*/
    public interface Solver {
        public Object[] solver(Object[] objectDoubleArray);
    }

    public class Solver1Class implements Solver{
        public Object[] solver(Object[] objectDoubleArray) {

            return new Object[]{10,10,10,10};
        }
    }

    public class Solver2Class implements Solver{
        public Object[] solver(Object[] objectDoubleArray) {

            return new Object[]{10,10};
        }
    }
    /*Классы solver-ов*/
    /*Классы solver-ов*/
    /*Классы solver-ов*/

    Solver1Class solver1 = new Solver1Class();
    Solver2Class solver2 = new Solver2Class();

    Solver[] solverArray = {solver1, solver2};
    ArrayList<Solver> solverArrayList = new ArrayList<>(Arrays.asList(solverArray));


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

    /*Для проверки подлинности: Автор - Куницкий Андрей Владимирович, химический факультет.*/


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


/*Метод getOut преобразует массив объектов в сокращенный массив объектов соответствующий
заданным массиву строк и массиву соответствующей сокращенной строки(накладывает маску)
[1234]{abcd} + {bd} = [2,4], {bd} - equation array, {abcd} - general array*/
    public static Object[] getOut(String[] equationArray, String[] generalArray, Object[] objectArray) {
        Object[] tightObjectArray = new Object[equationArray.length];
        // счетчик для tightObjectArray;
        byte k = 0;
        ArrayList<String> equationList = new ArrayList<>(Arrays.asList(equationArray));
        for (int i = 0; i < generalArray.length; i++) {
            if (equationList.contains(generalArray[i])) {
                tightObjectArray[k] = objectArray[i];
                k++;
            }
        }
        return tightObjectArray;
    }

    /*Функция arrayGetOut() является аналогом функции getOut но только вместо шаблона массивов типа [a,b] берет шаблон
     массивов массивов типа [[a,b],[c,d]] и возвращает не [1,2] а [[1,2],[3,4]]
     Итого [[1,2],[3,4]]{a,b,c,d} + [[a,b],[c,d]] = [[1,2],[3,4]]
      [[a,b],[c,d]] - multiEquationArray, {a,b,c,d} - general array
      данный метод зависим от метода getOut т.к содержит его в себе*/
    public static Object[][] arrayGetOut(String[][] multiEquationArray, String[] generalArray, Object[] objectArray) {
        Object[][] multiObjectArray = new Object[multiEquationArray.length][];
        for (int k = 0; k < multiEquationArray.length; k++) {
            multiObjectArray[k] = getOut(multiEquationArray[k], generalArray, objectArray);
        }
        return multiObjectArray;
    }

/*Данный метод рассчитывает equationCode массива объектов, если массив содержит все
* double-ы и только один не double то quationCode = 1, если 2 то 2 и.т.д */
    public static int equationCodeDetermine(Object[] objectArray) {
        int equationCode = 0;
        for (int i = 0; i < objectArray.length; i++) {
            if (!((Object) objectArray[i].getClass().getName() == "java.lang.Double")) {
                equationCode++;
            }
        }
        return equationCode;
    }

    public static int[] arrayEquationCodeDetermine(Object[][] multiObjectArray) {
        int[] equationCodeArray = new int[multiObjectArray.length];
        for (int i = 0; i < multiObjectArray.length; i++) {
            equationCodeArray[i] = equationCodeDetermine(multiObjectArray[i]);
        }
        return equationCodeArray;
    }
    /*Куницкий Андрей Владимирович, Химический факультет, 03.08.2023 солнечная погода*/

/* Комбинация метода getOut и equationCodeDetermine */
    public static int equationFullCodeDetermine(String[] equationArray, String[] generalArray, Object[] objectArray) {
        int equationCode = 0;
        ArrayList<String> equationList = new ArrayList<>(Arrays.asList(equationArray));
        for (int i = 0; i < generalArray.length; i++) {
            if (!((Object) objectArray[i].getClass().getName() == "java.lang.Double")
                    && (equationList.contains(generalArray[i]))) {
                equationCode++;
            }
        }
        return equationCode;
    }
/*Данный метод представляет из себя петлю while, которая выполняется до тех пор, пока все элементы массива equationCodeArray не обнуляться,
Для в данной петле работает другая петля, которая на каждом цикле петли while проходит по массиву equationCodeArray, созданному в самом начале
метода, находит в нем единицы и вызывает для соответствующего массива объектов из массива массивов метод solver() из массива объектов
класса Solver, имплементирующего одноименный интерфейс.*/
    public static Object[][] solverInitiatorLoop(Object[][] multiObjectArray, Solver[] solverArray, String[][] multiEquationArray) {
        Object[][] anwserArray = new Object[multiObjectArray.length][];
        int[] equationCodeArray = arrayEquationCodeDetermine(multiObjectArray);
        while (IntStream.of(equationCodeArray).sum() != 0) {
            for (int i = 0; i < multiObjectArray.length; i++) {
                if (equationCodeArray[i] == 1) {
                    anwserArray[i] = solverArray[i].solver(multiObjectArray[i]);
                }
                /*Проверка на наличие одинаковых переменных в других уравнениях для того чтобы их приравнять.
                 Скорее всего нужно будет написать отдельные метод для этих целей*/
                for (int k = 1; i < multiEquationArray.length; k++) {
                    for (int m = 0; m < multiEquationArray.length; m++) {
                        if (m != k) {
                            for (int n = 0; n < multiEquationArray.length; n++) {
                                if (multiEquationArray[k].equals(multiEquationArray[m][n])) {
                                    multiObjectArray[m][n] = multiObjectArray[k];
                                }
                            }
                        }
                    }
                }
            }
        }
        return anwserArray;
    }

    public static void solverMain() {
        output();
        Object[] objectDoubleArray = toDouble(input(numberOfVariables));
        Object[][] multiArray = arrayGetOut(multiEquationArray, parametersStringArray, objectDoubleArray);
        int[] byteArray = arrayEquationCodeDetermine(multiArray);
        for (int i : byteArray) {
            System.out.println(i);
        }






//        for (int i = 0; i < multiArray.length; i++) {
//            System.out.println("-----");
//            for (int k = 0; k < multiArray[i].length; k++) {
//                System.out.println(multiArray[i][k]);
//            }
        }
//        System.out.println(Arrays.asList(getOut(equation1, parametersStringArray, objectDoubleArray)));
//        System.out.println(Arrays.asList(getOut(equation2, parametersStringArray, objectDoubleArray)));
    }


