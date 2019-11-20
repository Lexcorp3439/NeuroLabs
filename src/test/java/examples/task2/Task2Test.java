package examples.task2;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import examples.task1.Task1;

import static org.junit.Assert.*;

public class Task2Test {


    private Task2 task;


    @Before
    public void setUp() {
        task = new Task2();
    }

    @Test
    public void check() {

        assertEquals(0, task.check(0, 0, 0, 0, 0));
        assertEquals(0, task.check(1, 1, 1, 1, 1));

        assertEquals(1, task.check(0, 0, 0, 0, 1));
        assertEquals(1, task.check(0, 0, 1, 0, 1));
        assertEquals(1, task.check(0, 1, 1, 0, 1));
        assertEquals(1, task.check(1, 0, 1, 1, 0));
        assertEquals(1, task.check(1, 1, 0, 0, 1));
        assertEquals(1, task.check(1, 1, 1, 1, 0));

    }

    @Test
    public void generate() {
        var expected = new int[]
                {
                        0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                        0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0
                };

        assertArrayEquals(expected, task.generate(task.sample()));

    }

}