public class CorsoIscritti {

    private final String nomeCorso;
    private final int capienza;
    private final String[] studenti;
    private int iscritti;

    public CorsoIscritti(String nomeCorso, int capienza) {
        if(nomeCorso == null)
            this.nomeCorso = "Corso";
        else if(nomeCorso.trim().isEmpty() == true)
            this.nomeCorso = "Corso";
        else this.nomeCorso = nomeCorso.trim();
        
        if(capienza < 0){
            this.capienza = 0;
        }else{
            this.capienza = capienza;
        }
        String[] array = new String[this.capienza];
        this.studenti = array;
        this.iscritti = 0;
    }

    public boolean iscriviStudente(String nomeCognome) {
        if (nomeCognome == null) return false;
        String n = nomeCognome.trim();
        if (n.isEmpty()) return false;
        if (iscritti >= capienza) return false;

        for (int i = 0; i < iscritti; i++) {
            if (studenti[i].equals(n) == true) return false;
        }
        int indice = iscritti;
        studenti[indice] = n;
        iscritti = 1 + iscritti;
        return true;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public int getCapienza() {
        return capienza;
    }

    public int getIscritti() {
        return iscritti;
    }

    public boolean ePieno() {
        if(iscritti >= capienza)
            return true;
        else return false;
    }

    public String getStudente(int index) {
        if(index < 0) return null;
        else if(index >= iscritti) return null;
        else{
            return studenti[index];
        } 
    }

    public String[] elencoStudenti() {
        String[] copia = new String[iscritti];
        for (int i = 0; i < iscritti; i = i + 1) {
            String tmp = studenti[i];
            copia[i] = tmp;
        }
        return copia;
    }
}
