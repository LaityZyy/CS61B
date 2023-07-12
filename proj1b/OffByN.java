import static java.lang.Math.abs;
public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int n) {
        N = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return abs(x - y) == N;
    }
}
