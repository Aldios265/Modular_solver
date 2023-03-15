package test;

import java.util.Arrays;

public class Calculator_test {
    public static Object[] solveabc(Object[] objectDoubleArray) {

        /*Определение позиции неизвестного элемента*/
        byte position = -1;
        for (byte i = 0; i < objectDoubleArray.length; i++) {
            if (!((Object) objectDoubleArray[i].getClass().getName() == "java.lang.Double")) {
                position = i;
            }
        }
        /*-------------------------------------------*/

        /*САМА ФУНКЦИЯ*/
        /* a = b * c  */
        /*[a, -, c]*/
        /*для написание нового solver-а необходимо на вручную прописать каждый кейс в виде %a = %b * %c, %b = %a / %c а затем
        * выполнить замену %a на (double) objectDoubleArray[0] и слева от знака = убрать (double)*/
        switch (position) {
            case 0:
                objectDoubleArray[0] = (double) objectDoubleArray[1] *  (double) objectDoubleArray[2];
                break;
            case 1:
                objectDoubleArray[1] = (double) objectDoubleArray[0] / (double) objectDoubleArray[2];
                break;

            case 2:
                objectDoubleArray[2] = (double) objectDoubleArray[0] / (double) objectDoubleArray[1];
                break;
        }
        return objectDoubleArray;
    }
    /*САМА ФУНКЦИЯ*/
    /* a = b * c  */
    /*[a, b, c]*/
    public static void main(String[] args) {
        System.out.println(Arrays.asList(solveabc(new Object[]{100d,"-",4d})));

    }
}
