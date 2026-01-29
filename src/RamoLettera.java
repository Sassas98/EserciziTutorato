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

    /*
     TODO
     Implementa i metodi equals e hashCode in modo che due frami siano uguali
        se rappresentano la stessa parola.
    */

}
