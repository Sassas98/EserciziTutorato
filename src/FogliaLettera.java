public class FogliaLettera implements ParteLettera {
    private final char testo;

    public FogliaLettera(char testo) {
        this.testo = testo;
    }

    @Override
    public String toString() {
        return testo + "";
    }

    /*
     TODO
     Implementa i metodi equals e hashCode in modo che due foglie siano uguali
     se contengono lo stesso carattere.
    */

}
