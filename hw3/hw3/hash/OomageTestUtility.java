package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        int N = oomages.size();
        for (Oomage x : oomages) {
            int h = (x.hashCode() & 0x7FFFFFFF) % M;
            bucket[h]++;
        }
        for (int x : bucket) {
            if (x < N / 50 || x > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
