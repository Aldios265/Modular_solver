package Menu;

import menu.menus.*;


/** Главные класс данного проекта, в нем создаются все объекты окон меню и функций и добавляются в массив
 меню и функций соответственно. Данный класс содержит функцию main()**/
public class MenuMain {
    public static void main(String[] args) {
        MenuExample2 menuExample = new MenuExample2();
        MenuStructure.menuCycle(menuExample.menuArray);
    }


}
























//Куницкий Андрей Владимирович