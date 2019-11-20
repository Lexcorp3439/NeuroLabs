package examples.task1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Task1Test {


    private Task1 task;


    @Before
    public void setUp() {
        task = new Task1();
    }

    @Test
    public void check() {
        assertEquals(0, task.checkTest(0.33, 0.33));
        assertEquals(1, task.checkTest(1.33, 1.33));
        assertEquals(0, task.checkTest(0.33, 1.33));
        assertEquals(1, task.checkTest(1.33, 0.33));
    }

    @Test
    public void generate() {

        var expected = new int[]
                {
                        0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1
                };
        assertArrayEquals(expected, task.generate(task.sample()));
    }
}