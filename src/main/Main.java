package main;

public class Main {
    public static void main(String[] args) {

        CartridgeExample2 cartridge2 = new CartridgeExample2();
        CartridgeExample1 cartridge1 = new CartridgeExample1();

        System.out.println(Solver.solverMain(cartridge1));
    }
}
