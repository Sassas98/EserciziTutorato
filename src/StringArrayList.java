/*
Genera la classe StringArrayList che implementa una lista di stringhe con i metodi specificati.
addLast(String value): aggiunge la stringa value alla fine della lista, anche se value è null.
get(int index): restituisce la stringa all'indice index della lista. Se l'indice è fuori dai limiti, restituisce null.
removeAt(int index): rimuove la stringa all'indice index della lista, impostando quella posizione a null. Se l'indice è fuori dai limiti, non fa nulla.
removeFirst(String s): rimuove la prima occorrenza della stringa s nella lista, impostando quella posizione a null. Se la stringa non è presente, non fa nulla.
size(): restituisce il numero di elementi attualmente presenti nella lista.
toArray(): restituisce un array di stringhe contenente tutti gli elementi della lista, inclusi i null. Non esporre direttamente l'array interno.
compact(): rimuove tutti i null dalla lista, riducendo la dimensione della lista di conseguenza.
clear(): rimuove tutti gli elementi dalla lista, rendendola vuota.
Ragionare sul concetto di array ridimensionabile, con una logica di copia degli elementi in un nuovo array quando necessario.
Come sempre, è vietato utilizzare classi esterne all'infuori di String e Object.
*/


/**
 * Rappresenta una lista dinamica di stringhe implementata
 * esclusivamente tramite un array di {@code String}.
 * <p>
 * La struttura consente inserimenti, accessi e rimozioni logiche
 * (tramite valori {@code null}). La rimozione fisica degli elementi
 * avviene solo tramite una compattazione esplicita.
 */
public class StringArrayList {

    /**
     * Array interno che memorizza gli elementi della lista.
     * Può contenere valori {@code null}.
     */
    private String[] list;

    /**
     * Costruisce una lista vuota.
     * L'array interno viene inizializzato con lunghezza zero.
     */
    public StringArrayList() {
        this.list = new String[0];
    }

    /**
     * Aggiunge una stringa in fondo alla lista.
     * <p>
     * L'operazione crea un nuovo array di dimensione maggiore
     * e copia manualmente tutti gli elementi esistenti.
     *
     * @param value la stringa da aggiungere in fondo alla lista
     */
    public void addLast(String value) {
        String[] newList = new String[list.length + 1];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        newList[list.length] = value;
        list = newList;
    }

    /**
     * Restituisce l'elemento presente all'indice specificato.
     *
     * @param index la posizione dell'elemento da ottenere
     * @return la stringa in posizione {@code index}, oppure {@code null}
     *         se l'indice non è valido
     */
    public String get(int index) {
        return index < 0 || index >= list.length ? null : list[index];
    }

    /**
     * Rimuove logicamente l'elemento all'indice specificato.
     * <p>
     * La rimozione avviene impostando la posizione a {@code null}.
     * L'array non viene ridimensionato e gli altri elementi non
     * vengono spostati.
     *
     * @param index la posizione dell'elemento da rimuovere
     */
    public void removeAt(int index) {
        if (index >= 0 && index < list.length) {
            list[index] = null;
        }
    }

    /**
     * Rimuove la prima occorrenza della stringa specificata.
     * <p>
     * La rimozione avviene impostando a {@code null} la prima
     * posizione che contiene una stringa uguale al valore fornito.
     *
     * @param s la stringa da rimuovere
     */
    public void removeFirst(String s) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(s)) {
                list[i] = null;
                return;
            }
        }
    }

    /**
     * Restituisce la dimensione dell'array interno.
     * <p>
     * Il valore restituito rappresenta la lunghezza dell'array
     * e non necessariamente il numero di elementi non nulli.
     *
     * @return la lunghezza dell'array interno
     */
    public int size() {
        return list.length;
    }

    /**
     * Restituisce una copia dell'array interno.
     * <p>
     * Le modifiche all'array restituito non influenzano la lista.
     * L'array può contenere valori {@code null}.
     *
     * @return una copia dell'array interno
     */
    public String[] toArray() {
        String[] result = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = list[i];
        }
        return result;
    }

    /**
     * Compatta la lista rimuovendo tutti i valori {@code null}.
     * <p>
     * Dopo la compattazione, l'array interno contiene solo
     * elementi non nulli e l'ordine relativo è preservato.
     */
    public void compact() {
        StringArrayList newList = new StringArrayList();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                newList.addLast(list[i]);
            }
        }
        this.list = newList.toArray();
    }

    /**
     * Rimuove tutti gli elementi dalla lista.
     * <p>
     * L'array interno viene reimpostato a una lunghezza pari a zero.
     */
    public void clear() {
        this.list = new String[0];
    }
}
