package main;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Solver {


    /** Блок инициализации функций **/
    /*Функция вывода сообщения пользователю*/
    public static void output(String parametersString) {
        System.out.println("Введите значения через запятую: \n" + parametersString);
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
    преобразующиеся в double с момощью Double.pareDouble() метод записывает в массив как double datatype, остальные оставляет такими какие они есть */
    public static Object[] toDouble(String[] initialArray) {
        Object[] objectDoubleArray = new Object[initialArray.length];
        for (int i = 0; i < initialArray.length; i++) {
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
     * double-ы и только один не double то equationCode = 1, если 2 то 2 и.т.д */
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
     Данная функция по координатам только что рассчитанной неизвестной(одни могут быть найдены с помощью функции getPosition(), вызываемой
     перед действием solver-а. Возвращает она массив, в котором дополнены все те места которые соотвествтвуют рассчитанной переменной.*/
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
    public static Object[][] solverInitiatorLoop(Object[][] multiObjectArray, Calculator[] calculatorArray, String[][] multiEquationArray) {
        Object[][] solvedArray = multiObjectArray.clone();
        int[] equationCodeArray = arrayEquationCodeDetermine(multiObjectArray);
        while (IntStream.of(equationCodeArray).sum() != 0) {
            for (int i = 0; i < multiObjectArray.length; i++) {
                if (equationCodeArray[i] == 1) {
                    int k = getPosition(multiObjectArray[i]);
                    solvedArray[i] = calculatorArray[i].solver(multiObjectArray[i]);
                    solvedArray = findAndFill(i, k, multiEquationArray, solvedArray);
                    equationCodeArray = arrayEquationCodeDetermine(solvedArray);
                }
            }
        }
        return solvedArray;
    }



    /*Функция pack() преобразует решенный массив(solvedArray) в вид листа [12][34]+[abcd]+[ab][cd] = [1234]*/
    public static Object[] pack(String[] parametersStringArray, String[][] multiEquationArray, Object[][] solvedArray) {
        Object[] answerArray = new Object[parametersStringArray.length];
        for (int i = 0; i < parametersStringArray.length; i++) {
            for (int k = 0; k < multiEquationArray.length; k++) {
                for (int l = 0; l < multiEquationArray[k].length; l++) {
                    if (parametersStringArray[i] == multiEquationArray[k][l]) {
                        answerArray[i] = (double) solvedArray[k][l];
                    }
                }
            }
        }
        return answerArray;
    }



    //Преобразует массив переменных в строку с перечислением через запятую.
    public static String parseArray(String[] parametersArray) {
        return Arrays.stream(parametersArray).collect(StringBuilder::new, (x,y) -> x.append(", " + y),StringBuilder::append).substring(2);
    }




    /** Основная функция **/
    public static Object solverMain(Cartridge cartridge) {
        //Считывание картриджа.
        String[] parametersArray = cartridge.getParametersArray();
        String[][] equationArray = cartridge.getEquationArray();
        Calculator[] calculatorsArray = cartridge.getCalculatorArray();
        int numberOfUnknowns = parametersArray.length - equationArray.length;

        //Вызов методов.
        String parametersString = parseArray(parametersArray);
        output(parametersString);
        String[] userInput = input(parametersArray.length);
        Object[] objectDoubleArray = toDouble(userInput);
        Object[][] objectMultiArray = arrayGetOut(equationArray, parametersArray, objectDoubleArray);
        Object[][] answerMultiArray = solverInitiatorLoop(objectMultiArray, calculatorsArray, equationArray);
        return Arrays.asList(pack(parametersArray, equationArray, answerMultiArray));
    }
}























/* Автор - Куницкий Андрей Владимирович, химический факультет.*/


