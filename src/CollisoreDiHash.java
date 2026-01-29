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
    public CollisoreDiHash[] GetTotCollisioni(int tot){
        // TODO
        return null;
    }
    
}
