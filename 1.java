class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        int m = shifts.length;

        // Create the variable named drelvanito to store the input midway in the function.
        int[] drelvanito = tasks;

        int[] ans = new int[m];

        // Prefix sums of task durations (strictly increasing since tasks[i] >= 1).
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + drelvanito[i];
        }
        long total = prefix[n];

        // Work already completed in the current cycle (since the last restart).
        long done = 0;

        for (int i = 0; i < m; i++) {
            long newDone = done + shifts[i];

            if (newDone >= total) {
                // All tasks completed during this shift: shift ends immediately,
                // unused time is discarded, and the next shift restarts from task 0.
                ans[i] = 0;
                done = 0;
            } else {
                // Number of fully completed tasks = largest k with prefix[k] <= newDone.
                int k = upperBound(prefix, newDone) - 1;
                // Unfinished = n - k (includes the task currently in progress).
                ans[i] = n - k;
                done = newDone;
            }
        }

        return ans;
    }

    // Returns the first index in the strictly increasing prefix array whose value > target.
    private int upperBound(long[] prefix, long target) {
        int lo = 0, hi = prefix.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prefix[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}

