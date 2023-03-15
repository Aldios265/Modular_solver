package main;



public class CartridgeExample1 implements Cartridge {

    /**Описание: Данный картридж работает с переменными a,b,c,d,e,
     решает уравнения:
     1) a = b * c
     2) c = 10^d
     3) d = cos(e)
     **/

    /* Инициализация калькуляторов*/
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
                    objectDoubleArray[0] = Math.pow(10, ((double) objectDoubleArray[1]));
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


    Calculator1Class calculator1 = new Calculator1Class();
    Calculator2Class calculator2 = new Calculator2Class();
    Calculator3Class calculator3 = new Calculator3Class();

    Calculator[] calculatorArray = {calculator1, calculator2, calculator3};
    String[] parametersArray = {"a", "b", "c", "d", "e"};
    String[][] equationArray = {{"a", "b", "c"}, {"c", "d"}, {"d", "e"}};


    /* Функции считывания картриджа */
    public Calculator[] getCalculatorArray() {
        return calculatorArray;
    }

    public String[] getParametersArray() {
        return parametersArray;
    }

    public String[][] getEquationArray() {
        return equationArray;
    }

}


