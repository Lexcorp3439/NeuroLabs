package examples.task2;

public class Task2 {
    private int[] exm = new int[]
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0};


    public int check(int x1, int x2, int x3, int x4, int x5) {
        int tmp = 0;
        if (x5 == 1) tmp += 1;
        if (x4 == 1) tmp += 2;
        if (x3 == 1) tmp += 4;
        if (x2 == 1) tmp += 8;
        if (x1 == 1) tmp += 16;
        return exm[tmp];
    }

    public int[] generate(int[][] test) {
        var res = new int[test.length];
        for (int i = 0; i < test.length; i++) {
            res[i] = check(test[i][0], test[i][1], test[i][2], test[i][3], test[i][4]);
        }
        return res;
    }

    public int[][] sample() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
        };
    }
}
