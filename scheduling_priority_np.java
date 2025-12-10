import java.util.Scanner;

public class scheduling_priority_np {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n]; // process id
        int[] at  = new int[n]; // arrival time
        int[] bt  = new int[n]; // burst time
        int[] pr  = new int[n]; // priority (lower value = higher priority)
        int[] ct  = new int[n]; // completion time
        int[] tat = new int[n]; // turn around time
        int[] wt  = new int[n]; // waiting time
        boolean[] done = new boolean[n]; // completed flag

        // Input arrival times
        System.out.println("Enter arrival times:");
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("AT for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
        }

        // Input burst times
        System.out.println("Enter burst times:");
        for (int i = 0; i < n; i++) {
            System.out.print("BT for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
        }

        // Input priorities
        System.out.println("Enter priorities (lower number = higher priority):");
        for (int i = 0; i < n; i++) {
            System.out.print("Priority for P" + pid[i] + ": ");
            pr[i] = sc.nextInt();
        }

        int time = 0;       // current time
        int completed = 0;  // number of completed processes
        float sumTAT = 0;
        float sumWT = 0;

        // Priority Scheduling (Non-preemptive)
        while (completed < n) {
            int idx = -1;
            int bestPr = Integer.MAX_VALUE;

            // find the highest priority (lowest number) among arrived processes
            for (int i = 0; i < n; i++) {
                if (!done[i] && at[i] <= time && pr[i] < bestPr) {
                    bestPr = pr[i];
                    idx = i;
                }
                // optional tie-breaker: if same priority, choose earlier arrival, then lower PID
                else if (!done[i] && at[i] <= time && pr[i] == bestPr && idx != -1) {
                    if (at[i] < at[idx]) {
                        idx = i;
                    } else if (at[i] == at[idx] && pid[i] < pid[idx]) {
                        idx = i;
                    }
                }
            }

            if (idx == -1) {
                // no process is ready at this time → CPU idle
                time++;
            } else {
                // execute selected process non-preemptively (till completion)
                time += bt[idx];
                ct[idx] = time;
                tat[idx] = ct[idx] - at[idx];
                wt[idx] = tat[idx] - bt[idx];

                sumTAT += tat[idx];
                sumWT += wt[idx];

                done[idx] = true;
                completed++;
            }
        }

        // Output
        System.out.println();
        System.out.println("PID\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.printf("P%-2d\t%-2d\t%-2d\t%-2d\t%-2d\t%-2d\t%-2d%n",
                    pid[i], at[i], bt[i], pr[i], ct[i], tat[i], wt[i]);
        }

        System.out.println();
        System.out.printf("Average TAT: %.2f%n", sumTAT / n);
        System.out.printf("Average WT : %.2f%n", sumWT / n);

        sc.close();
    }
}
