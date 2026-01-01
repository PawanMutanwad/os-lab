import java.util.Scanner;

public class scheduling_roundrobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] at  = new int[n];
        int[] bt  = new int[n];
        int[] rt  = new int[n];
        int[] ct  = new int[n];
        int[] tat = new int[n];
        int[] wt  = new int[n];

        System.out.println("Enter arrival times:");
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("AT for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
        }

        System.out.println("Enter burst times:");
        for (int i = 0; i < n; i++) {
            System.out.print("BT for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        int time = 0;
        int completed = 0;
        float sumTAT = 0;
        float sumWT = 0;

        while (completed < n) {
            boolean progress = false;

            for (int i = 0; i < n; i++) {

                if (rt[i] > 0 && at[i] <= time) {

                    progress = true;

                    int run = Math.min(tq, rt[i]);

                    time += run;
                    rt[i] -= run;

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

            if (!progress) {
                time++;
            }
        }

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