/*
    Questa classe ha due metodi.
    String getParola(ParteLettera p): dato un albero di lettere rappresentato dall'interfaccia ParteLettera,
    restituisce la parola corrispondente.
    SE l'oggetto è null, restituisce null

    ParteLettera costruisciAlbero(String parola): dato una parola, costruisce un albero di lettere
    rappresentato dall'interfaccia ParteLettera. 
    SE la parola è vuota o null, restituisce null.
    SE la parola è dispari, l'ultimo nodo a destra conterrà una foglia con il carattere speciale '_'.
*/

public class CostruttoreDiParole {

    public String getParola(ParteLettera p) {
        return p == null ? null : p.toString();
    }

    public ParteLettera costruisciAlbero(String parola) {
        if(parola == null || parola.isEmpty()) 
            return null;
        if(parola.length() % 2 == 1) 
            parola += "_";
        return costruisciAlberoRicorsivo(parola);
    }
    
    private ParteLettera costruisciAlberoRicorsivo(String parola) {
        ParteLettera foglia = new FogliaLettera(parola.charAt(0));
        if(parola.length() == 1) {
            return foglia;
        } else {
            int len = parola.length();
            String resto = parola.substring(1, len);
            ParteLettera ramo = costruisciAlberoRicorsivo(resto);
            return new RamoLettera(foglia, ramo);
        }
    }
}
