package test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Calculator_test2 {


    //массив строчных литералов переменных.
    static String[] parametersStringArray = {"n", "n0", "t", "k", "tHalf"};


    static int numberOfVariables = parametersStringArray.length;


    //Массив equation-ов
    static String[][] multiEquationArray = {{"n", "n0", "t", "k"},{"k", "tHalf"}};



    /*Классы solver-ов*/
    public interface Solver {
        public Object[] solver(Object[] objectDoubleArray);
    }

    /* Solver - дополняет массив литералов, который содержит только одну неизвестную
     [-1394] -> [41394] по заранее прописанному алгоритму. ОБЯЗАТЕЛЬНО ДОЛЖЕН ВОЗВРАЩАТЬ DOUBLE!*/

    public static class Solver1Class implements Solver{
        public Object[] solver(Object[] objectDoubleArray) {

            return new Object[]{10d,10d,10d,10d};
        }
    }

    public static class Solver2Class implements Solver{
        public Object[] solver(Object[] objectDoubleArray) {

            return new Object[]{10d,10d};
        }
    }

    /*Классы solver-ов*/

    static Solver1Class solver1 = new Solver1Class();
    static Solver2Class solver2 = new Solver2Class();

    static Solver[] solverArray = {solver1, solver2};



    /*Функция вывода сообщения пользователю*/
    public static void output() {
        System.out.println("Рассчет реакций первого порядка \n" +
                "Введите значения через запятую: \n" +
                "n, n0, t, [k, t(1/2)]");
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



    /* Комбинация метода getOut и equationCodeDetermine. Не используется */
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
    public static Object[][] solverInitiatorLoop(Object[][] multiObjectArray, Solver[] solverArray, String[][] multiEquationArray) {
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



    public static void solverMain() {
        output();
        Object[] objectDoubleArray = toDouble(input(numberOfVariables));
        Object[][] multiArray = arrayGetOut(multiEquationArray, parametersStringArray, objectDoubleArray);
        Object[][] finalArray = solverInitiatorLoop(multiArray, solverArray, multiEquationArray);
        for (Object[] array : finalArray) {
            System.out.println(Arrays.asList(array));
        }

//        int[] byteArray = arrayEquationCodeDetermine(multiArray);
//        for (int i : byteArray) {
//            System.out.println(i);
//        }








//        for (int i = 0; i < multiArray.length; i++) {
//            System.out.println("-----");
//            for (int k = 0; k < multiArray[i].length; k++) {
//                System.out.println(multiArray[i][k]);
//            }
    }  /*Для проверки подлинности: Автор - Куницкий Андрей Владимирович, химический факультет.*/
//        System.out.println(Arrays.asList(getOut(equation1, parametersStringArray, objectDoubleArray)));
//        System.out.println(Arrays.asList(getOut(equation2, parametersStringArray, objectDoubleArray)));
}


