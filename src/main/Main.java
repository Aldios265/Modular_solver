package main;
import menu.*;
import menu.menus.SolverMenu;

public class Main {
    public static void main(String[] args) {

        SolverMenu solverMenu = new SolverMenu();
        MenuStructure.menuCycle(solverMenu.menuArray);
//        CartridgeExample2 cartridge2 = new CartridgeExample2();
//        CartridgeExample1 cartridge1 = new CartridgeExample1();
//
//        System.out.println(Solver.solverMain(cartridge1));
    }
}
