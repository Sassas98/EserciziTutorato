import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CollisoreDiHash {
    private final int n1, n2;
    public CollisoreDiHash(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    @Override
    public int hashCode() {
        return 31 * (31 + n1) + n2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CollisoreDiHash other = (CollisoreDiHash) obj;
        return n1 == other.n1 && n2 == other.n2;
    }

    /**
     * Restituisce un array di CollisoriDiHash di lunghezza 'tot',
     * contente collisori di hash distinti sia tra loro che da this, tutti con lo stesso valore di hashCode.
     * @param tot
     * @return
     */
    public CollisoreDiHash[] getTotCollisioni(int tot) {
        if (tot < 0) throw new IllegalArgumentException("tot deve essere >= 0");

        CollisoreDiHash[] res = new CollisoreDiHash[tot];
        int baseHash = this.hashCode();

        for (int idx = 0, t = 1; idx < tot; t++) {
            // qui si ricorre ad un po' di matematica
            int i = n1 + t;
            int j = n2 - 31 * t;
            CollisoreDiHash c = new CollisoreDiHash(i, j);
            if (c.hashCode() == baseHash) {
                res[idx++] = c;
            }
        }
        return res;
    }
    
}
