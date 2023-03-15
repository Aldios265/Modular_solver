package test;

import java.util.Arrays;

public class Main_test {
    public static void main(String[] args) {

        CartridgeSample_1 cartridge1 = new CartridgeSample_1();
        System.out.println(Arrays.asList(Solver_gen.solverMain(cartridge1)));

        }
    }

