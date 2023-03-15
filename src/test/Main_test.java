package test;

import java.util.Arrays;

public class Main_test {
    public static void main(String[] args) {

        String[] parametersStringArray = {"a", "b", "c", "d", "e", "f"};
        String[][] equationArray = {{"a", "b", "c"}, {"a", "b", "e", "f"}, {"d", "e"}};

        class Calculator1Class implements Calculator {
            public Object[] solver(Object[] objectDoubleArray) {
                int position = getPosition(objectDoubleArray);
                //Место для switch-блока
                switch (position) {
                    case 0:
                        objectDoubleArray[0] = Math.pow(Math.exp(1), (double) objectDoubleArray[2] / (25 * (double) objectDoubleArray[1]));
                        break;

                    case 1:
                        objectDoubleArray[1] = (double) objectDoubleArray[2] / (25 * Math.log((double) objectDoubleArray[0]));
                        break;

                    case 2:
                        objectDoubleArray[2] = 25 * (double) objectDoubleArray[1] * Math.log((double) objectDoubleArray[0]);
                        break;

                }
                //Место для switch-блока
                return objectDoubleArray;
            }
        }

        class Calculator2Class implements Calculator {
            public Object[] solver(Object[] objectDoubleArray) {
                int position = getPosition(objectDoubleArray);
                //Место для switch-блока
                switch (position) {
                    case 0:
                        objectDoubleArray[0] = Math.acos((double) objectDoubleArray[2]) / ((double) objectDoubleArray[1] * (double) objectDoubleArray[3]);
                        break;
                    case 1:
                        objectDoubleArray[1] = Math.acos((double) objectDoubleArray[2]) / ((double) objectDoubleArray[0] * (double) objectDoubleArray[3]);
                        break;

                    case 2:
                        objectDoubleArray[2] = Math.cos((double) objectDoubleArray[1] * (double) objectDoubleArray[0] * (double) objectDoubleArray[3]);
                        break;

                    case 3:
                        objectDoubleArray[3] = Math.acos((double) objectDoubleArray[2]) / ((double) objectDoubleArray[0] * (double) objectDoubleArray[1]);
                        break;
                }
                //Место для switch-блока
                return objectDoubleArray;
            }
        }

        class Calculator3Class implements Calculator {
            public Object[] solver(Object[] objectDoubleArray) {
                int position = getPosition(objectDoubleArray);
                //Место для switch-блока
                switch (position) {
                    case 0:
                        objectDoubleArray[0] = 5 * (double) objectDoubleArray[1];
                        break;
                    case 1:
                        objectDoubleArray[1] = (double) objectDoubleArray[0] / 5;
                        break;
                }
                //Место для switch-блока
                return objectDoubleArray;
            }
        }

        Calculator1Class calculator1 = new Calculator1Class();
        Calculator2Class calculator2 = new Calculator2Class();
        Calculator3Class calculator3 = new Calculator3Class();

        Calculator[] calculatorsArray = {calculator1, calculator2, calculator3};

        System.out.println(Arrays.asList(Solver_gen.solverMain(parametersStringArray, equationArray, calculatorsArray)));

        }
    }

