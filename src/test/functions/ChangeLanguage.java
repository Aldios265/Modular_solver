package test.functions;

public class ChangeLanguage implements Function {
    String name = "Сменить язык";

    public void execute() {
        System.out.println("К сожалению эта функция пока не реализована");
    }
    public String getName() {
        return name;
    }
}
