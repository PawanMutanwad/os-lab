public class scheduling_priority_p {
    public static void main(String[] args) {

        int at[] = {0, 1, 2};
        int bt[] = {5, 3, 4};
        int pr[] = {2, 1, 3};

        int n = at.length;
        int rt[] = bt.clone();
        int ct[] = new int[n];
        int completed = 0, time = 0;

        while (completed < n) {
            int idx = -1, best = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && pr[i] < best) {
                    best = pr[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                time++;
                continue;
            }

            rt[idx]--;
            time++;

            if (rt[idx] == 0) {
                ct[idx] = time;
                completed++;
            }
        }

        System.out.println("PID AT BT PR CT TAT WT");
        for (int i = 0; i < n; i++) {
            int tat = ct[i] - at[i];
            int wt = tat - bt[i];
            System.out.println("P" + (i+1) + "  " +
                    at[i] + "  " + bt[i] + "  " + pr[i] + "  " +
                    ct[i] + "  " + tat + "   " + wt);
        }
    }
}
