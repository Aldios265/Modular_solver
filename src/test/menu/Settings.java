package test.menu;

public class Settings implements Menu{

    String name = "Настройки";
    String[] possibleOptions = {"Сменить язык", "Назад"};
    String pastName = "Главное меню";


    public String getName() {
        return name;
    }

    public String[] getOptions() {
        return possibleOptions;
    }

    public String getPastFunction() {
        return pastName;
    }
}
