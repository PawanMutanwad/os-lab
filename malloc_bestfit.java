public class malloc_bestfit {
    public static void main(String[] args) {

        int blockSize[] = {100, 500, 200, 300, 600};
        int processSize[] = {212, 417, 112, 426};

        int m = blockSize.length;
        int n = processSize.length;

        int allocation[] = new int[n]; 
        for (int i = 0; i < n; i++) allocation[i] = -1;

        for (int i = 0; i < n; i++) {
            int bestIdx = -1;

            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("Process  Size   Block");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i+1) + "\t " + processSize[i] + "\t ");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }
}
