public class FogliaLettera implements ParteLettera {
    private final char testo;

    public FogliaLettera(char testo) {
        this.testo = testo;
    }

    @Override
    public String toString() {
        return testo + "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + testo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FogliaLettera other))
            return false;
        if (testo != other.testo)
            return false;
        return true;
    }

}
