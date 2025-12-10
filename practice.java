import java.util.Scanner;

public class practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n]; // process id
        int[] at  = new int[n]; // arrival time
        int[] bt  = new int[n]; // burst time
        int[] ct  = new int[n]; // completion time
        int[] tat = new int[n]; // turn around time
        int[] wt  = new int[n]; // waiting time
        boolean[] done = new boolean[n]; // completed flag

        for(int i = 0; i < n; i++){
            pid[i] = i + 1;
            System.out.print("AT for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            System.out.print("BT for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
        }

        int time = 0, completed = 0;
        float sumTAT = 0, sumWT = 0;

        while(completed < n){
            int idx = -1;
            int minBT = Integer.MAX_VALUE;

            for(int i = 0; i < n; i++){
                if(!done[i] && at[i] <= time && bt[i] <= minBT){
                    minBT = bt[i];
                    idx = i;
                } else if (!done[i] && at[i] <= time && bt[i] == minBT){
                    if(at[i] > at[idx]){
                        idx = i;
                    }
                }
            }

            if (idx == -1){
                time++;
            } else {
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

        // output
        System.out.println();
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for(int i = 0; i < n; i++){
            System.out.printf("P%-2d\t%-2d\t%-2d\t%-2d\t%-2d\t%-2d\t%n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }
        
        System.out.println();

        System.out.println("Avg TAT = " + sumTAT/n);
        System.out.println("Avg WT = " + sumWT/n);


        sc.close();
    }
}