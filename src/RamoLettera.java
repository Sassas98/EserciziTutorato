public class RamoLettera implements ParteLettera {
    private final ParteLettera sinistro;
    private final ParteLettera destro;

    public RamoLettera(ParteLettera sinistro, ParteLettera destro) {
        if(sinistro == null || destro == null)
            throw new IllegalArgumentException("I rami non possono essere nulli");
        this.sinistro = sinistro;
        this.destro = destro;
    }

    @Override
    public String toString() {
        return sinistro.toString() + destro.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sinistro.hashCode();
        result = prime * result + destro.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RamoLettera other = (RamoLettera) obj;
        return this.destro.equals(other.destro)
            && this.sinistro.equals(other.sinistro);
    }

    

}
