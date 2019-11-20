package examples.task3;

import org.junit.Before;
import org.junit.Test;

import utils.MyUtils;

import static org.junit.Assert.*;

public class Task3Test {
    private Task3 task;


    @Before
    public void setUp() {
        task = new Task3();
    }


    @Test
    public void check() {
        assertEquals(0, task.checkTest(1, 1));
        assertEquals(0, task.checkTest(599, 599));
        assertEquals(1, task.checkTest(200, 200));
        assertEquals(1, task.checkTest(500, 400));
    }

    @Test
    public void generate() {
    }

    @Test
    public void sampleTest() {
        var t1 = task.sampleTest(4);
        var copy = t1.clone();
        MyUtils.normalization(t1, false, 0, 599);
        MyUtils.normalization(t1, true, 0, 599);
        assertArrayEquals(copy, t1);
    }
}