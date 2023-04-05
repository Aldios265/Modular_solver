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




    public Object[][] menuArray = {{"Главное меню", "Главное меню", "Выбрать программу",  "Информация"},
                        {"Выбрать программу", "Главное меню", "SolverExample1", "Назад"},
                            {"SolverExample1", "Выбрать программу" , "Выполнить" , "Информация", "Назад"},
                                {"Выполнить" , runSolverExample1},
                        {"Информация", "Главное меню", "Кратко о программе", "Как работает программа", "Как создать собственный Solver", "Назад"},
                            {"Кратко о программе", whatIsThisFor}
    };
}
