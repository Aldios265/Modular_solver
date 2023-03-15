package test;

public interface Calculator {
    Object[] solver(Object[] objectDoubleArray);

    default int getPosition(Object[] objectDoubleArray) {
        byte position = -1;
        for (byte i = 0; i < objectDoubleArray.length; i++) {
            if (!((Object) objectDoubleArray[i].getClass().getName() == "java.lang.Double")) {
                position = i;
            }
        }
        return position;
    }
}