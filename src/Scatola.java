public class Scatola {
    private final Scatola[] scatole;
    private int indice = 0;

    public Scatola(int max) {
        this.scatole = new Scatola[max];
    }

    public void aggiungi(Scatola scatola) {
        if (indice >= scatole.length) {
            throw new IllegalStateException("Scatola piena");
        }
        scatole[indice++] = scatola;
    }

    /**
     * Due scatole sono uguali se contengono lo stesso numero di scatole
     * e se tutte le scatole contenute sono uguali, anche non nell'ordine.
     * Es: [ [ ], [ [ ] ] ] è uguale a [ [ [ ] ], [ ] ]
     */
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    /*
        per l'hashCode, puoi usare la somma dei singoli hashCode delle scatole contenute e il numero di scatole contenute.
    */
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

}
