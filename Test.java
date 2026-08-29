import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: expected [0, 2, 1]
        int[] tasks1 = {1, 4, 4};
        int[] shifts1 = {9, 1, 4};
        System.out.print("Example 1: ");
        print(sol.countTasks(tasks1, shifts1));

        // Example 2: expected [0, 2, 0]
        int[] tasks2 = {2, 3, 4};
        int[] shifts2 = {20, 4, 5};
        System.out.print("Example 2: ");
        print(sol.countTasks(tasks2, shifts2));

        // Example 3: expected [2, 0, 2]
        int[] tasks3 = {4, 2};
        int[] shifts3 = {3, 6, 1};
        System.out.print("Example 3: ");
        print(sol.countTasks(tasks3, shifts3));

        // Edge case: single task, completes exactly on the second shift
        int[] tasks4 = {5};
        int[] shifts4 = {3, 2};
        System.out.print("Edge (single task): ");
        print(sol.countTasks(tasks4, shifts4));

        // Edge case: shift ends exactly when all tasks complete
        int[] tasks5 = {2, 3};
        int[] shifts5 = {5, 5};
        System.out.print("Edge (exact finish): ");
        print(sol.countTasks(tasks5, shifts5));

        // Worst-case TLE scenario: n = m = 100000, each shift completes all tasks.
        // Old O(n*m) approach would need ~1e10 ops; optimized is O(m log n).
        int n = 100000, m = 100000;
        int[] bigTasks = new int[n];
        int[] bigShifts = new int[m];
        Arrays.fill(bigTasks, 1);
        Arrays.fill(bigShifts, n); // each shift exactly finishes all tasks

        long start = System.currentTimeMillis();
        int[] bigAns = sol.countTasks(bigTasks, bigShifts);
        long elapsed = System.currentTimeMillis() - start;

        boolean allZero = true;
        for (int v : bigAns) {
            if (v != 0) {
                allZero = false;
                break;
            }
        }
        System.out.println("Stress (n=m=100000): all zeros = " + allZero
                + ", time = " + elapsed + " ms");
    }

    static void print(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(arr[i]);
        }
        sb.append("]");
        System.out.println(sb);
    }
}

