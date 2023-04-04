package menu;

import java.util.Scanner;

public class FunctionExecutor {
    public static void execute(Function function) {
        System.out.println("\n\n\n");
        function.execute();
        System.out.println("\n\n\n");
        System.out.println("Функция выполнена, для возврата введите любой символ.");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }
}
