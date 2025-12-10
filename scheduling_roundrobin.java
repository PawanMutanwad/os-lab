import java.util.Scanner;

public class scheduling_roundrobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];  // process id
        int[] at  = new int[n];  // arrival time
        int[] bt  = new int[n];  // burst time
        int[] rt  = new int[n];  // remaining time
        int[] ct  = new int[n];  // completion time
        int[] tat = new int[n];  // turn around time
        int[] wt  = new int[n];  // waiting time

        // Input AT
        System.out.println("Enter arrival times:");
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("AT for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
        }

        // Input BT
        System.out.println("Enter burst times:");
        for (int i = 0; i < n; i++) {
            System.out.print("BT for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i]; // remaining time initially = burst time
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        int time = 0;        // current clock time
        int completed = 0;   // number of processes completed
        float sumTAT = 0;
        float sumWT = 0;

        // Round Robin loop continues until all processes complete
        while (completed < n) {
            boolean progress = false;

            for (int i = 0; i < n; i++) {

                if (rt[i] > 0 && at[i] <= time) {

                    progress = true; // at least one process executed

                    // run the process for min(time quantum, remaining time)
                    int run = Math.min(tq, rt[i]);

                    time += run;     // CPU time increments
                    rt[i] -= run;    // reduce remaining time

                    // if process finishes
                    if (rt[i] == 0) {
                        ct[i] = time;
                        tat[i] = ct[i] - at[i];
                        wt[i] = tat[i] - bt[i];

                        sumTAT += tat[i];
                        sumWT += wt[i];

                        completed++;
                    }
                }
            }

            // If no process could run (i.e., all arrive later), move time forward
            if (!progress) {
                time++;
            }
        }

        // Output
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