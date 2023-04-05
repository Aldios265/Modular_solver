package main;

public class DocumentationClass {
    public static String whatIsThisFor = "Кратко о программе.\n" +
            "Modular_solver - программа для решения простых математических задач естественно-научного содержания.\n" +
            "Предположим, что нам необходимо решить некоторую естественно-научную задачу множество раз используя различные начальные параметры.\n" +
            "\n" +
            "Чтобы понять, для каких целей данная программа может быть применима рассмотрим простой пример:\n" +
            "Предположим, нам нужно рассчитать время, за которое распадется 70% радиоактивного изотопа, зная его время полупревращения. Нам известны 2 формулы:\n" +
            "Первая, связывающая константу скорости данного процесса со временем и количеством вещества \n" +
            "Ln(N/N0) = kt;\n" +
            "N – количество вещества в момент времени t\n" +
            "N0 – начальное количество вещества \n" +
            "k – константа\n" +
            "t – время, за которое успело распасться N вещества\n" +
            "\n" +
            "И вторая, связывающая константу скорости со временем полупревращения:\n" +
            "k = ln2/t(1/2);\n" +
            "t(1/2) – период полураспада. \n" +
            "\n" +
            "Для того, чтобы решить данную задачу нам нужно для начала найти константу скорости по второй формуле, а затем используя найденную\n константу скорости по первой формуле выразить t. Как, можно видеть, задача очень простая. Однако предположим, что нам нужно решить данную\n задачу многократно, постоянно используя различные времена полупревращения. Конечно, для этого проще всего было бы сразу же подставить k из первой формулы по вторую и используя Microsoft EXCEL рассчитать все значения. \n" +
            "Однако, предположим теперь, что нам требуется решать не однотипные задачи на множество обратных друг другу задач\n, например найти время полупревращения если за какое-то время распалось определенное количество вещества, или найти начальное количество вещества по времени\n полупревращения, времени и количеству оставшегося вещества и.т.д. \n" +
            "Именно для этих целей и предназначена программа Modular_solver. Она позволяет автоматически проводить расчеты в определенном \"пространстве уравнений”. Сами эти пространства можно создавать и редактировать по самостоятельно, о чем будет идти речь в последующих разделах.\n Для этого для каждого пространства равнений в пакете main.cartridges находятся так называемые “картриджи”(далее без кавычек). Картридж – это по сути класс настроек, или же класс инструкция для программы, который и задает пространство уравнений. Чтобы задать свое собственное пространство уравнений достаточно всего лишь отредактировать готовый орбазец картриджа, в исходном коде и в следующих разделах инструкции подробно описан данный процесс. \nКратко о программе.\n" +
            "Modular_solver - программа для решения простых математических задач естественно-научного содержания.\n" +
            "Предположим, что нам необходимо решить некоторую естественно-научную задачу множество раз используя различные начальные параметры.\n" +
            "\n" +
            "Чтобы понять, для каких целей данная программа может быть применима рассмотрим простой пример:\n" +
            "Предположим, нам нужно рассчитать время, за которое распадется 70% радиоактивного изотопа, зная его время полупревращения. Нам известны 2 формулы:\n" +
            "Первая, связывающая константу скорости данного процесса со временем и количеством вещества \n" +
            "Ln(N/N0) = kt;\n" +
            "N – количество вещества в момент времени t\n" +
            "N0 – начальное количество вещества \n" +
            "k – константа\n" +
            "t – время, за которое успело распасться N вещества\n" +
            "\n" +
            "И вторая, связывающая константу скорости со временем полупревращения:\n" +
            "k = ln2/t(1/2);\n" +
            "t(1/2) – период полураспада. \n" +
            "\n" +
            "Для того, чтобы решить данную задачу нам нужно для начала найти константу скорости по второй формуле,\n а затем используя найденную константу скорости по первой формуле выразить t. Как, можно видеть, задача очень простая. Однако предположим, что нам нужно решить\n данную задачу многократно, постоянно используя различные времена полупревращения. Конечно, для этого проще всего было бы сразу же подставить k \nиз первой формулы по вторую и используя Microsoft EXCEL рассчитать все значения. \n" +
            "Однако, предположим теперь, что нам требуется решать не однотипные задачи на множество обратных друг другу задач,\n например найти время полупревращения если за какое-то время распалось определенное количество вещества, или найти начальное количество вещества\n по времени полупревращения, времени и количеству оставшегося вещества и.т.д. \n" +
            "Именно для этих целей и предназначена программа Modular_solver. Она позволяет автоматически проводить расчеты в определенном \"пространстве уравнений”. Сами эти пространства можно создавать и редактировать по самостоятельно, о чем будет идти речь в последующих\n разделах. Для этого для каждого пространства равнений в пакете main.cartridges находятся так называемые “картриджи”(далее без кавычек).\n Картридж – это по сути класс настроек, или же класс инструкция для программы, который и задает пространство уравнений. Чтобы задать свое собственное пространство\n уравнений достаточно всего лишь отредактировать готовый орбазец картриджа, в исходном коде и в следующих разделах\n инструкции подробно описан данный процесс. \n" +
            "Куницкий Андрей химический факультет 3 курс";

}
