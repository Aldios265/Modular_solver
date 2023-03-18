package test.menu;

public class Settings implements Menu{
    String treeName = "Settings";
    String[] tree = {"SelectLanguage", "Select"};

    String name = "Настройки";
    String[] possibleOptions = {"Выбрать язык", "Выбрать что-то еще"};

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
