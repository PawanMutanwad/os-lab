import java.util.Arrays;
import java.util.Scanner;

public class diskscheduling_look {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of disk requests: ");
        int n = sc.nextInt();

        System.out.print("Enter current head position: ");
        int head = sc.nextInt();

        System.out.print("Enter direction 1 for right, 0 for left: ");
        int direction = sc.nextInt();
        
        int[] req = new int[n];
        System.out.println("Enter track order sentence: ");
        for(int i = 0; i < n; i++){
            req[i] = sc.nextInt();
        }

        Arrays.sort(req);

        int totalMovement = 0;
        int pos = head;

        System.out.println("Order of service: ");
        System.out.print(head);

        if(direction == 1){
            for(int i = 0; i < n; i++){
                if(req[i] >= head){
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }
            for(int i = n - 1; i >= 0; i--){
                if(req[i] < head){
                    totalMovement += Math.abs(pos - req[i]);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }
        } else {
            for(int i = n - 1; i >= 0; i--){
                if(req[i] < head){
                    totalMovement += Math.abs(pos - req[i]);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }
            for(int i = 0; i < n; i++){
                if(req[i] >= head){
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print(" -> " + pos);
                }
            }
        }

        System.out.println("\nTotal head movement: " + totalMovement);
        sc.close();
    }
}
