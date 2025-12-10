import java.util.Arrays;
import java.util.Scanner;

public class diskscheduling_clook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disk requests: ");
        int n = sc.nextInt();

        int[] req = new int[n];

        System.out.print("Enter current head position: ");
        int head = sc.nextInt();

        System.out.println("Enter request sequence:");
        for (int i = 0; i < n; i++) {
            req[i] = sc.nextInt();
        }

        System.out.print("Enter direction (1 = right/up, 0 = left/down): ");
        int direction = sc.nextInt();

        Arrays.sort(req); // sorted list

        int totalMovement = 0;
        int pos = head;

        System.out.println("\nOrder of service:");
        System.out.print(head);

        // ---- CLOOK: Move Right First ----
        if (direction == 1) {
            // Serve all requests >= head
            for (int i = 0; i < n; i++) {
                if (req[i] >= head) {
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }

            // Jump to smallest request (circular movement)
            for (int i = 0; i < n; i++) {
                if (req[i] < head) {
                    totalMovement += Math.abs(pos - req[0]);
                    pos = req[0];
                    System.out.print(" -> " + pos);
                    break;
                }
            }

            // Serve remaining (smaller) requests
            for (int i = 1; i < n; i++) {
                if (req[i] < head) {
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }
        }

        // ---- CLOOK: Move Left First ----
        else {

            // Serve all requests <= head (from rightmost to leftmost)
            for (int i = n - 1; i >= 0; i--) {
                if (req[i] <= head) {
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }

            // Jump to largest request (circular jump)
            for (int i = n - 1; i >= 0; i--) {
                if (req[i] > head) {
                    totalMovement += Math.abs(pos - req[n - 1]);
                    pos = req[n - 1];
                    System.out.print(" -> " + pos);
                    break;
                }
            }

            // Serve remaining (higher) requests
            for (int i = n - 2; i >= 0; i--) {
                if (req[i] > head) {
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }
        }

        System.out.println("\n\nTotal Head Movement = " + totalMovement + " cylinders");

        sc.close();
    }   
}
