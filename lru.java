public class lru {
    public static void main(String[] args) {

        int pages[] = {1, 3, 0, 3, 5, 6, 3};
        int frames = 3;

        int f[] = new int[frames];
        int lastUsed[] = new int[frames];
        for (int i = 0; i < frames; i++) f[i] = -1;

        int time = 0, faults = 0;

        for (int p : pages) {
            time++;
            boolean hit = false;

            for (int j = 0; j < frames; j++) {
                if (f[j] == p) {
                    hit = true;
                    lastUsed[j] = time;
                    break;
                }
            }

            if (!hit) {
                int lru = 0;
                for (int j = 1; j < frames; j++) {
                    if (lastUsed[j] < lastUsed[lru]) lru = j;
                }

                f[lru] = p;
                lastUsed[lru] = time;
                faults++;
            }
        }

        System.out.println("Total Page Faults (LRU): " + faults);
    }
}
