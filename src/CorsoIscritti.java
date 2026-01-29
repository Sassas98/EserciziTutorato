public class CorsoIscritti {

    private final String nomeCorso;
    private final int capienza;
    private final String[] studenti;
    private int iscritti;

    public CorsoIscritti(String nomeCorso, int capienza) {
        this.nomeCorso = (nomeCorso == null || nomeCorso.trim().isEmpty()) ? "Corso" : nomeCorso.trim();
        this.capienza = capienza < 0 ? 0 : capienza;
        this.studenti = new String[this.capienza];
        this.iscritti = 0;
    }

    public boolean iscriviStudente(String nomeCognome) {
        if (nomeCognome == null) return false;
        String n = nomeCognome.trim();
        if (n.isEmpty()) return false;
        if (iscritti >= capienza) return false;

        for (int i = 0; i < iscritti; i++) {
            if (studenti[i].equals(n)) return false;
        }

        studenti[iscritti++] = n;
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
        return iscritti >= capienza;
    }

    public String getStudente(int index) {
        return (index < 0 || index >= iscritti) ? null : studenti[index];
    }

    public String[] elencoStudenti() {
        String[] copia = new String[iscritti];
        for (int i = 0; i < iscritti; i++) copia[i] = studenti[i];
        return copia;
    }
}
