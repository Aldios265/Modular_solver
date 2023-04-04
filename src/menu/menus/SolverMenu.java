package menu.menus;


import menu.*;

public class SolverMenu {

    Function runSolverExample1 = new Function() {
        public void execute() {
            System.out.println("выполнение программы...");
        }
    };
    Function infoSolverExample1 = new Function() {
        public void execute() {
            System.out.println("Информация о программе");
        }
    };



    public Object[][] menuArray = {{"Главное меню", "Главное меню", "Выбрать программу",  "Информация"},
                        {"Выбрать программу", "Главное меню", "SolverExample1", "Назад"},
                            {"SolverExample1", "Выбрать программу" , "Выполнить" , "Информация", "Назад"},
                                {"Выполнить" , runSolverExample1},
                                {"Информация", infoSolverExample1}
    };
}
