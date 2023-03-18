package test.menu;

public class MainMenu implements Menu {

    String name = "Главное меню";
    String[] possibleOptions = {"Выбрать программу", "Настройки", "Информация", "Назад"};



    public String getName() {
        return name;
    }

    public String[] getOptions() {
        return possibleOptions;
    }
}
