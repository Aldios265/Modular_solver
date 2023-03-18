package test;

public class MenuMain {
    public static void main(String[] args) {
        test.menu.MainMenu mainMenu = new test.menu.MainMenu();
        test.menu.Settings settings = new test.menu.Settings();
        MenuExecutor.mainMenu(mainMenu);
    }
}
