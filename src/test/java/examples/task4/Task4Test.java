package examples.task4;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import utils.MyUtils;

import static org.junit.Assert.*;

public class Task4Test {

    private Task4 task;

    @Before
    public void setUp() throws Exception {
        task = new Task4();
    }

    @Test
    public void check() {

    }

    @Test
    public void checkTest() {
        assertEquals(0, task.checkTest(50, 50));
        assertEquals(1,task.checkTest(180, 50));
        assertEquals(2,task.checkTest(300, 50));
        assertEquals(3,task.checkTest(420, 50));
        assertEquals(4,task.checkTest(540, 50));
        assertEquals(5,task.checkTest(100, 400));
        assertEquals(6,task.checkTest(599, 599));
    }

    @Test
    public void generate() {
        var sample4 = task.sample(10);

        var expected4 = task.generate(sample4);
        System.out.println(Arrays.toString(expected4));
    }

    @Test
    public void sample() {
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