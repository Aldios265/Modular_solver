package test;
import java.util.ArrayList;

public class Finder {

    /* Метод find находит индексы, соответствующие одной и той же переменной
    в других массивах данного массива массивов. */

    public static String find(int positionOuter, int positionInner, String[][] parametersStringArray) {
        ArrayList<int[]> coordinates;
        String givenVariable = parametersStringArray[positionOuter][positionInner];
//        for (int i = 0; i< parametersStringArray.length; i++) {
//            if (i != positionOuter) {
//                for (String variable: parametersStringArray[i]) {
//                    if ()
//                }
//            }
//        }
        return givenVariable;
    }
}
