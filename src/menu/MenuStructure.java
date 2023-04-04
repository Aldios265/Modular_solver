package menu;

import java.util.Scanner;


/* Данный класс выстраивает последовательность активации окон меню в зависимости от выбора пользователя.*/
public class MenuStructure {

    /* Основной и единственный метод данного класса*/
    public static void menuCycle(Object[][] menuArray) {

        /* Поле exit имеет по умолчанию значение false и, т.к на данный момент не реализована возможность
         * выхода из программы, данное значение не изменяется по ходу программы*/
        boolean exit = false;

        /* Поле functionFound используется для приостановки поиска в случае если окно меню или функциональное
         * окно были найдены*/

        boolean functionFound = false;
        /*Полю toExecute присваивается значение соответствующее выбору пользователя*/
        String toExecute = "Главное меню";

        /*Используется для возврата в меню после выполнения функции*/
        String pastExecution = "";

        /*Основная петля меню*/
        while (!exit) {
            functionFound = false;

            /*Поиск окна меню по полю name в массиве menuArray(массив объектов окон меню)*/
            for (Object[] i : menuArray) {
                if (i[0].equals(toExecute)) {

                    /*Данный блок кода проверяет, соотвествтвует ли данный подмассив окну меню или функциональной опции
                     * и вызывает соотвествующие методы в классах MenuExecutor и FunctionExecutor для активации данных
                     * подмассивов.*/
                    if (!MenuExecutor.checkIfOption(i)) {
                        /*Сохранение переменной toExecute перед присвоением ей нового значения для возможности возврата
                         * после выполнения функции*/
                        pastExecution = toExecute;
                        try {
                            toExecute = MenuExecutor.mainExecutor(i);
                        } catch (Exception e) {
                            System.out.println("Введен невеный символ или неверный номер опции.");
                        }

                    } else {
                        try {
                            Function function = (Function) i[1];
                            FunctionExecutor.execute(function);
                            toExecute = pastExecution;
                        } catch (Exception e) {
                            Function function = new Function() {
                                @Override
                                public void execute() {
                                    System.out.println("Функция не найдена");
                                }
                            };
                            FunctionExecutor.execute(function);
                            toExecute = pastExecution;
                        }

                    }
                    /*Реализация опции назад*/
                    if (toExecute.equals("Назад")) {
                        toExecute = i[1].toString();
                    }
                    functionFound = true;
                }
            }

            /*Поиск функциональной опции по полю name в массиве functionArray(массив объектов функциональных опций)*/


            /* В случае если опция не была найдена ни в одном из массивов по именам то выполняется следующий блок кода: */
            if (!functionFound) {
                System.out.println("\n\n\n");
                System.out.println("К сожалению, на данный момент данной опции не существует.");
                System.out.println("\n\n\n");
                System.out.println("Чтобы вернуться в меню введите любой символ:");
                Scanner scanner = new Scanner(System.in);
                scanner.next();
                toExecute = pastExecution;
            }
        }
    }
}

