/*
Genera la classe Prodotto con le seguenti proprieta', entrambe definite dal costruttore
final String ID
int unita
se l'unita' è minore di 0, diviene 0. se ID è nullo o di dimensione diversa da 10,
lancia una eccezione IllegalArgumentException
definire i getter per entrambe le proprieta' ed esporre questi due metodi

boolean presente()
torna true se le unita' del prodotto sono maggiori di 0

boolean consuma(int qta)
se qta e' negativa lancia una IllegalArgumentException
se ci sono almeno le unita' del prodotto richieste dalla quantita', le toglie e torna true
altrimenti non diminuisce nulla e torna false.

Genera la classe Magazzino.
Un magazzino è capace di registrare, conservare e prelevare i prodotti in modo intelligente.
il costruttore ha un solo paramentro, int max, 
che indica il numero massimo di diversi prodotti che puo' accettare.
espone poi i seguenti metodi:

boolean aggiungi(Prodotto p)
se p e' null o non contiene unita', torna false
se c'e' gia' un prodotto con lo stesso id, semplicemente somma la loro quantita'
altrimenti, se c'e' spazio lo aggiunge e torna true
se il prodotto e' nuovo e non c'e' spazio torna false

boolean consumaUnita(String id, int num)
se num e' negativo lancia una IllegalArgumentException
se non c'e' nessun prodotto con quell'id, torna false
se il prodotto non ha abbastanza unita per soddisfare il num, torna false
altrimenti, diminuisci le unita sottraendo num e torna true
se cio' fa scendere le unita' a 0, la memoria del magazzino deve essere eliminata
in modo tale da liberare spazio per altri eventuali nuovi articoli
*/

public class Prodotto {
    private final String ID;
    private int unita;

    public Prodotto(String ID, int unita){
        if(ID == null || ID.length() != 10)
            throw new IllegalArgumentException();
        this.ID = ID;
        this.unita = unita > 0 ? unita : 0;
    }

    public String getID() {
        return ID;
    }

    public int getUnita() {
        return unita;
    }

    public boolean presente(){
        return unita > 0;
    }

    public boolean consuma(int qta){
        if(qta < 0)
            throw new IllegalArgumentException();
        if(qta > unita) return false;
        unita -= qta;
        return true;
    }

}
