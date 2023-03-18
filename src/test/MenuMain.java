package test;
import test.functions.ChangeLanguage;
import test.functions.Function;
import test.menu.*;

public class MenuMain {

    static test.menu.MainMenu mainMenu = new test.menu.MainMenu();
    static test.menu.Settings settings = new test.menu.Settings();
    static Menu[] menuArray = {mainMenu, settings};

    static ChangeLanguage changeLanguage = new ChangeLanguage();
    static Function[] functionArray = {changeLanguage};

    public static void menuCycle(Menu[] menuArray) {
        boolean exit = false;
        boolean functionFound = false;
        String toExecute = "Главное меню";
        String pastExecution = "Главное меню";
        while (!exit) {
            functionFound = false;
            for (Menu i : menuArray) {
                if (i.getName().equals(toExecute)) {
                    if (pastExecution != toExecute) {
                        pastExecution = toExecute;
                    }
                    toExecute = MenuExecutor.mainExecutor(i);
                    if (toExecute == "Назад") {
                        toExecute = pastExecution;
                    }
                    functionFound = true;
                }
            }
            if (!functionFound) {
                for (Function k : functionArray) {
                    if (k.getName().equals(toExecute)) {
                        k.execute();
                        toExecute = pastExecution;
                        functionFound = true;
                    }
                }
            }

            if (!functionFound) {
                System.out.println("(убрать) функция не найдена");
            }
            System.out.println("1");
        }
    }

    public static void main(String[] args) {
        menuCycle(menuArray);
    }
}
