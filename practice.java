import java.util.Arrays;

public class practice {

    public static void main(String[] args) {
        int n = 3;
        int head = 23;
        int req[] = {1,2,3};

        int direction = 1;
        int max = 199;

        int totalMovement = 0;
        int pos = head;

        Arrays.sort(req);

        if (direction == 1){
            for(int i = 0; i < n; i++){
                if(req[i] >= head){
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print("->" + pos);
                }
            }

            totalMovement += Math.abs(max - pos);
            pos = max;
            System.out.print("->" + pos);

            for(int i = 0; i < n; i++){
                if(req[i] <= head){
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print("->" + pos);
                }
            }    
        } else {
            for(int i = 0; i < n; i++){
                if(req[i] <= head){
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print("->" + pos);
                }
            }

            totalMovement += Math.abs(pos - 0);
            pos = 0;
            System.out.println("->" + pos);

            for(int i = 0; i < n; i++){
                if(req[i] >= head){
                    totalMovement += Math.abs(req[i] - pos);
                    pos = req[i];
                    System.out.print("->" + pos);
                }
            }
        }

        System.out.println(totalMovement);
    }
}