import java.util.Scanner;

public class scheduling_ {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n]; // process id
        int[] at  = new int[n]; // arrival time
        int[] bt  = new int[n]; // burst time (original)
        int[] rt  = new int[n]; // remaining time
        int[] ct  = new int[n]; // completion time
        int[] tat = new int[n]; // turn around time
        int[] wt  = new int[n]; // waiting time

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
            rt[i] = bt[i]; // initially remaining time = burst time
        }

        int time = 0;        // current time
        int completed = 0;   // number of completed processes
        float sumTAT = 0;
        float sumWT = 0;

        // SJF Preemptive (SRTF) scheduling
        while (completed < n) {

            int idx = -1;
            int minRT = Integer.MAX_VALUE;

            // find process with minimum remaining time among arrived processes
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && rt[i] < minRT) {
                    minRT = rt[i];
                    idx = i;
                }
                // optional tie-breaker: if same remaining time, choose earlier arrival / lower PID
                else if (at[i] <= time && rt[i] > 0 && rt[i] == minRT && idx != -1) {
                    if (at[i] < at[idx]) {
                        idx = i;
                    } else if (at[i] == at[idx] && pid[i] < pid[idx]) {
                        idx = i;
                    }
                }
            }

            if (idx == -1) {
                // no process is ready at this time, CPU idle
                time++;
            } else {
                // run the chosen process for 1 time unit (preemptive)
                rt[idx]--;
                time++;

                // if this process finished now
                if (rt[idx] == 0) {
                    completed++;
                    ct[idx] = time;               // completion time
                    tat[idx] = ct[idx] - at[idx]; // turnaround time
                    wt[idx] = tat[idx] - bt[idx]; // waiting time

                    sumTAT += tat[idx];
                    sumWT += wt[idx];
                }
            }
        }

        // Output results
        System.out.println();
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.printf("P%-2d\t%-2d\t%-2d\t%-2d\t%-2d\t%-2d%n",
                    pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }

        System.out.println();
        System.out.printf("Average TAT: %.2f%n", sumTAT / n);
        System.out.printf("Average WT : %.2f%n", sumWT / n);

        sc.close();
    }
}
