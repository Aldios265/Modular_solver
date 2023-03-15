package test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Solver_test_abc {

    /** Блок настроек **/

    //массив строчных литералов переменных.
    static String[] parametersStringArray = {"a", "b", "c", "d", "e"};
    static String parametersString = "a, b, c, d, e";
    static String parametersStringGrouped1 = "[a, b, c], [c, d], [d, e]";
    static String parametersStringGrouped2 = "(a, b, [c), {d], e}";

    static int numberOfVariables = parametersStringArray.length;

    //Массив equation-ов
    static String[][] multiEquationArray = {{"a", "b", "c"}, {"c", "d"}, {"d", "e"}};


    /*Интерфейс для работы calculator-ов*/
    public interface Calculator {
        Object[] solver(Object[] objectDoubleArray);
    }


    /** Calculator **/
    /* - дополняет массив литералов, который содержит только одну неизвестную
     [-1394] -> [41394] по заранее прописанному алгоритму. ОБЯЗАТЕЛЬНО ДОЛЖЕН ВОЗВРАЩАТЬ DOUBLE!. Заменить
     тип возвращаемой переменной нельзя т.к он работает с одним из типов Object[][]*/

    public static class Calculator1Class implements Calculator {
        public Object[] solver(Object[] objectDoubleArray) {
            int position = getPosition(objectDoubleArray);
            //Место для switch-блока
                switch (position) {
                    case 0:
                        objectDoubleArray[0] = (double) objectDoubleArray[1] * (double) objectDoubleArray[2];
                        break;

                    case 1:
                        objectDoubleArray[1] = (double) objectDoubleArray[0] / (double) objectDoubleArray[2];
                        break;

                    case 2:
                        objectDoubleArray[2] = (double) objectDoubleArray[0] / (double) objectDoubleArray[1];
                        break;
                }
            //Место для switch-блока
            return objectDoubleArray;
        }
    }

    public static class Calculator2Class implements Calculator {
        public Object[] solver(Object[] objectDoubleArray) {
            int position = getPosition(objectDoubleArray);
            //Место для switch-блока
                switch (position) {
                    case 0:
                        objectDoubleArray[0] = Math.pow(10, ((double) objectDoubleArray[0]));
                        break;
                    case 1:
                        objectDoubleArray[1] = Math.log10((double) objectDoubleArray[0]);
                        break;
                }
            //Место для switch-блока
            return objectDoubleArray;
        }
    }

    public static class Calculator3Class implements Calculator {
        public Object[] solver(Object[] objectDoubleArray) {
            int position = getPosition(objectDoubleArray);
            //Место для switch-блока
                switch (position) {
                    case 0:
                        objectDoubleArray[0] = Math.cos((double) objectDoubleArray[1]);
                        break;
                    case 1:
                        objectDoubleArray[1] = Math.acos((double) objectDoubleArray[0]);
                        break;
                }
            //Место для switch-блока
            return objectDoubleArray;
        }
    }





    /* Преобразование Calculator-классов в соответствующий массив объектов */
    static Calculator1Class calculator1 = new Calculator1Class();
    static Calculator2Class calculator2 = new Calculator2Class();
    static Calculator3Class calculator3 = new Calculator3Class();

    static Calculator[] solverArray = {calculator1, calculator2, calculator3};

    /** Блок настроек **/





    /** Блок инициализации функций **/

    /*Функция вывода сообщения пользователю*/
    public static void output() {
        System.out.println("Введите значения через запятую: \n" +
                parametersStringGrouped1 + "\n" +
                parametersString);
    }


    /*метод input() преобразует строку, введенную пользователем в массив строк, разделенных запятой
    а также проводит валидацию строки, длинна строки должна соответствовать аргументу функции*/
    public static String[] input(int numberOfVariables) {
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


    /*Метод getOut преобразует массив объектов в сокращенный массив объектов соответствующий
    заданным массиву строк и массиву соответствующей сокращенной строки(накладывает маску)
    [1234]{abcd} + {bd} = [2,4]   ,{bd} - equation array, {abcd} - general array*/
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


    /*Функция arrayEquationCodeDetermine определяет equationCode и выводит его в виде массива для
     * каждого из уравнений*/
    public static int[] arrayEquationCodeDetermine(Object[][] multiObjectArray) {
        int[] equationCodeArray = new int[multiObjectArray.length];
        for (int i = 0; i < multiObjectArray.length; i++) {
            equationCodeArray[i] = equationCodeDetermine(multiObjectArray[i]);
        }
        return equationCodeArray;
    }


    /*функция getPosition определяет индекс элемента массива объектов с equationCode = 1
     * который соответствует неизвестной переменной*/
    public static int getPosition(Object[] objectDoubleArray) {
        byte position = -1;
        for (byte i = 0; i < objectDoubleArray.length; i++) {
            if (!((Object) objectDoubleArray[i].getClass().getName() == "java.lang.Double")) {
                position = i;
            }
        }
        return position;
    }


    /* функция findAndFill() - используется после действия функции solver() на одно из уравнений с equationCode = 1
     * Данная функция по координатам только что рассчитанной неизвестной(одни могут быть найдены с помощью функции getPosition(), вызываемой
     * перед действием solver-а. Возвращает она массив, в котором дополнены все те места которые соотвествтвуют рассчитанной переменной.*/
    public static Object[][] findAndFill(int positionOuter, int positionInner, String[][] multiEquationArray, Object[][] doubleObjectArray) {
        Object[][] finalDoubleObjectArray = doubleObjectArray.clone();
        String givenVariable = multiEquationArray[positionOuter][positionInner];
        double givenValue = (double) doubleObjectArray[positionOuter][positionInner];
        for (int outer = 0; outer < multiEquationArray.length; outer++) {
            if (outer != positionOuter) {
                for (int inner = 0; inner < multiEquationArray[outer].length; inner++) {
                    if (multiEquationArray[outer][inner].equals(givenVariable)) {
                        finalDoubleObjectArray[outer][inner] = givenValue;
                    }
                }
            }
        }
        return finalDoubleObjectArray;
    }


    /*Данный метод представляет из себя петлю while, которая выполняется до тех пор, пока все элементы массива equationCodeArray не обнуляться,
    Для этого в данной петле присутствует другая пется, которая на каждом цикле петли while проходит по массиву equationCodeArray, созданному в самом начале
    метода, находит в нем единицы и вызывает для соответствующего массива объектов из массива массивов метод solver() из массива объектов
    класса Solver, имплементирующего одноименный интерфейс.*/
    public static Object[][] solverInitiatorLoop(Object[][] multiObjectArray, Calculator[] solverArray, String[][] multiEquationArray) {
        Object[][] solvedArray = multiObjectArray.clone();
        int[] equationCodeArray = arrayEquationCodeDetermine(multiObjectArray);
        while (IntStream.of(equationCodeArray).sum() != 0) {
            for (int i = 0; i < multiObjectArray.length; i++) {
                if (equationCodeArray[i] == 1) {
                    int k = getPosition(multiObjectArray[i]);
                    solvedArray[i] = solverArray[i].solver(multiObjectArray[i]);
                    solvedArray = findAndFill(i, k, multiEquationArray, solvedArray);
                    equationCodeArray = arrayEquationCodeDetermine(solvedArray);
                }
            }
        }
        return solvedArray;
    }
    /** Блок инициализации функций **/



    /** Основная функция **/
    public static void solverMain() {
        output();
        Object[] objectDoubleArray = toDouble(input(numberOfVariables));
        Object[][] multiArray = arrayGetOut(multiEquationArray, parametersStringArray, objectDoubleArray);
        Object[][] finalArray = solverInitiatorLoop(multiArray, solverArray, multiEquationArray);
        for (Object[] array : finalArray) {
            System.out.println(Arrays.asList(array));
        }
    }
}
    /** Основная функция **/





/* Образец Calculator-а

public static class Calculator1Class implements Calculator {
        public Object[] solver(Object[] objectDoubleArray) {
            int position = getPosition(objectDoubleArray);
            //Место для switch-блока

            //Место для switch-блока
            return objectDoubleArray;
        }
    }
 */




/* Шаблон switch для calculator-а

    switch (position) {
            case 0:

                break;
            case 1:

                break;

            case 2:

                break;
        }
    */




/* Автор - Куницкий Андрей Владимирович, химический факультет.*/


