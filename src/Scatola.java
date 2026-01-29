public class Scatola {
    private final Scatola[] scatole;
    private int indice = 0;

    public Scatola(int max) {
        if(max < 0) throw new IllegalArgumentException();
        this.scatole = new Scatola[max];
    }

    public void aggiungi(Scatola scatola) {
        if (indice >= scatole.length) {
            throw new IllegalStateException("Scatola piena");
        }
        scatole[indice++] = scatola;
        aggiustaScatole();
    }

    private void aggiustaScatole(){
        for (int i = indice-1; i > 0; i--) {
            if(scatole[i].indice > scatole[i-1].indice){
                Scatola temp = scatole[i];
                scatole[i] = scatole[i-1];
                scatole[i-1] = temp;
            }
        }
    }

    /**
     * Due scatole sono uguali se contengono lo stesso numero di scatole
     * e se tutte le scatole contenute sono uguali, anche non nell'ordine.
     * Es: [ [ ], [ [ ] ] ] è uguale a [ [ [ ] ], [ ] ]
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof Scatola other))
            return false;
        if(this.indice != other.indice)
            return false;
        for (int i = 0; i < indice; i++) {
            if(!this.scatole[i].equals(other.scatole[i]))
                return false;
        }
        return true;
    }

    /*
        per l'hashCode, puoi usare la somma dei singoli hashCode delle scatole contenute e il numero di scatole contenute.
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < indice; i++) {
            result = prime * result + scatole[i].hashCode();
        }
        return result;
    }

}
