public class fifo {
    public static void main(String[] args) {

        int pages[] = {1, 3, 0, 3, 5, 6, 3};
        int frames = 3;

        int f[] = new int[frames];
        for (int i = 0; i < frames; i++) f[i] = -1;

        int pointer = 0, faults = 0;

        for (int p : pages) {
            boolean hit = false;

            for (int j = 0; j < frames; j++) {
                if (f[j] == p) { hit = true; break; }
            }

            if (!hit) {
                f[pointer] = p;
                pointer = (pointer + 1) % frames;
                faults++;
            }
        }

        System.out.println("Total Page Faults (FIFO): " + faults);
    }
}
