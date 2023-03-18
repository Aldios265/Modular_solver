package test;

public class MainMenuCartridge implements MenuCartridge {

    String treeName = "MainMenu";
    String[] tree = {"ChooseProgram", "Settings", "Info", "Back"};

    String name = "ModularSolver\nГлавное меню";
    String[] possibleOptions = {"Выбрать программу", "Настройки", "Информация", "Назад"};

    public String getTreeName() {
        return treeName;
    }

    public String[] getTree() {
        return tree;
    }

    public String getName() {
        return name;
    }

    public String[] getOptions() {
        return possibleOptions;
    }
}
