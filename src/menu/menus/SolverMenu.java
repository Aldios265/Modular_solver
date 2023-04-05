package menu.menus;
import main.*;
import main.cartridges.*;


import menu.*;

public class SolverMenu {

    Function runSolverExample1 = new Function() {
        public void execute() {
            CartridgeExample1 cartridge1 = new CartridgeExample1();
            System.out.println(Solver.solverMain(cartridge1));
        }
    };

    Function infoSolver1 = new Function() {
        public void execute() {
            System.out.println("Описание:\n Данный картридж работает с переменными a,b,c,d,e,\n" +
                    "     решает уравнения:\n" +
                    "     1) a = b * c\n" +
                    "     2) c = 10^d\n" +
                    "     3) d = cos(e)");
        }
    };

    Function runSolverExample2 = new Function() {
        public void execute() {
            CartridgeExample2 cartridge2 = new CartridgeExample2();
            System.out.println(Solver.solverMain(cartridge2));
        }
    };

    Function infoSolver2 = new Function() {
        public void execute() {
            System.out.println("Описание:\n Данный картридж работает с переменными a,b,c,d,e,f,\n" +
                    "     решает уравнения:\n" +
                    "     1) c = 25 * b * ln(a)\n" +
                    "     2) e = cos(b * a * f)\n" +
                    "     3) d = 5 * e");
        }
    };

    Function infoSolverExample1 = new Function() {
        public void execute() {
            System.out.println("Информация о программе");
        }
    };

    Function whatIsThisFor = new Function() {
        public void execute() {
            System.out.println(DocumentationClass.whatIsThisFor);
        }
    };

    Function howToWork = new Function() {
        public void execute() {
            System.out.println(DocumentationClass.howToWork);
        }
    };




    public Object[][] menuArray = {{"Главное меню", "Главное меню", "Выбрать программу",  "Информация"},
                        {"Выбрать программу", "Главное меню", "SolverExample1", "Назад"},
                            {"SolverExample1", "Выбрать программу" , "Выполнить SolverExample1" , "Информация SolverExample1", "Назад"},
                                {"Выполнить SolverExample1" , runSolverExample1},
                                {"Информация SolverExample1", infoSolver1},
                            {"SolverExample1", "Выбрать программу" , "Выполнить SolverExample1" , "Информация SolverExample1", "Назад"},
                                {"Выполнить SolverExample2" , runSolverExample2},
                                {"Информация SolverExample2", infoSolver2},
                        {"Информация", "Главное меню", "Кратко о программе", "Как работать с программой", "Как создать собственный Solver", "Назад"},
                            {"Кратко о программе", whatIsThisFor},
                            {"Как работать с программой", howToWork}
    };
}
