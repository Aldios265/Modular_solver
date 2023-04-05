//package main.cartridges;
//
//import main.*;
//import java.lang.Math;
//
//
//public class Rate implements Cartridge {
//
//    /**Описание: Данный картридж работает с переменными c,c0,k,t,t(1/2),
//     решает уравнения:
//     1) ln(c/c0) = kt
//     2) k = ln2/t(1/2)
//     **/
//
//    /* Инициализация калькуляторов*/
//    public static class Calculator1Class implements Calculator {
//        public Object[] solver(Object[] objectDoubleArray) {
//            int position = getPosition(objectDoubleArray);
//            //Место для switch-блока
//            switch (position) {
//                case 0:
//                    %c = %k * %t - Math.log(%c);
//                    break;
//
//                case 1:
//                    %c0 = -%k * %t + %c;
//                    break;
//
//                case 2:
//                    %k = Math.log(%c / %c0) / %t;
//                    break;
//
//                case 3:
//                    %t = Math.log(%c / %c0) / %k;
//                    break;
//            }
//            //Место для switch-блока
//            return objectDoubleArray;
//        }
//    }
//
//    public static class Calculator2Class implements Calculator {
//        public Object[] solver(Object[] objectDoubleArray) {
//            int position = getPosition(objectDoubleArray);
//            //Место для switch-блока
//            switch (position) {
//                case 0:
//                    #k = Math.log(2) / #t(1/2);
//                    break;
//                case 1:
//                    #t(1/2) = Math.log(2) / #k;
//                    break;
//            }
//            //Место для switch-блока
//            return objectDoubleArray;
//        }
//    }
//
//
//
//
//    Calculator1Class calculator1 = new Calculator1Class();
//    Calculator2Class calculator2 = new Calculator2Class();
//
//
//    Calculator[] calculatorArray = {calculator1, calculator2};
//    String[] parametersArray = {"c", "c0", "k", "t", "t(1/2)"};
//    String[][] equationArray = {{"c", "c0", "k", "t"}, {"k", "t(1/2)"}};
//
//
//    /* Функции считывания картриджа */
//    public Calculator[] getCalculatorArray() {
//        return calculatorArray;
//    }
//
//    public String[] getParametersArray() {
//        return parametersArray;
//    }
//
//    public String[][] getEquationArray() {
//        return equationArray;
//    }
//
//}
//
//
