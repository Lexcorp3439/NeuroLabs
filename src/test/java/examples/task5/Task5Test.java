package examples.task5;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Task5Test {
    private Task5 task;

    @Before
    public void setUp() throws Exception {
        task = new Task5();
    }

    @Test
    public void check() {
        System.out.println(task.check(10));
        System.out.println(task.check(-10));
    }

    @Test
    public void generate() {
        var test = task.sample(5);
        var actual = new double[] {0, -10, 10, 2, -7};
        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(task.generate(test)));
        System.out.println("--------------------------");
        System.out.println(Arrays.toString(actual));
        System.out.println(Arrays.toString(task.generate(actual)));
    }

    @Test
    public void sample() {
        System.out.println(Arrays.toString(task.sample(5)));
    }
}