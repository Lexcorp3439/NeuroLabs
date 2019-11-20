package labs.lab0;

import labs.lab0.parts.Part4;
import utils.Kfold;
import examples.task1.Task1;
import examples.task2.Task2;
import examples.task3.Task3;
import examples.task4.Task4;
import examples.task5.Task5;
import labs.lab0.parts.Part2;
import labs.lab0.parts.Part3;

public class Lab0 {
    private Task1 task1 = new Task1();
    private Task2 task2 = new Task2();
    private Task3 task3 = new Task3();
    private Task4 task4 = new Task4();
    private Task5 task5 = new Task5();

    public void all() {
        part2();
        part3();
        part4();
    }

    public void part2() {
        new Part2(task1, task3, task4, task5).build();
    }

    public void part3() {
        new Part3(task3, task4, task5).build();
    }

    public void part4() {
        new Part4(task3).build();
    }
}
