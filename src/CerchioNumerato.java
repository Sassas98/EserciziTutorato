/*
    Implementa i metodi dell'interfaccia, in piu':
    1) la classe ha un solo costruttore che richiede x, y, raggio e numero. 
        quest'ultimo non è un float, ma un long.
        il raggio non può essere 0 o negativo, in quel caso lancia una IllegalArgumentException
    2) esponi le coordinate x e y con un solo setter che prende in input un Punto
    3) esponi il metodo void assorbi(CerchioNumerato c)
        se il numero del cerchio c è diverso da 0 e quest'ultimo è contenuto in this, diventa 0 e sommalo al cerchio attuale.
    
    Genera la classe PuntoFisso e implementa Punto
    ha un costruttore che accetta qualsiasi x e y e ha per ciascuno i getter, come vuole l'interfaccia. 
    non ha altro.

    Genera la classe PianoDeiCerchiNumerati
    1) il costruttore accetta solo un max. il numero massimo di cerchi numerati che può contenere
        se questo numero è minore o uguale a 0, diventa 1.
    2) esponi il metodo boolean aggiungi(CerchioNumerato c) 
        lo aggiunge se c'è spazio, se è diverso da null e se il suo numero è diverso da 0, e torna true. 
        in tutti gli altri casi non lo aggiunge e torna false.
        se lo aggiunge, controlla se è completamente contenuto da un altro cerchio gia' presente,
        in quel caso, deve essere assorbito dal cerchio già presente ed essere immediatamente rimosso, liberando spazio.
    3) esponi il metodo int contaContenuti(Cerchio c)
        torna il numero dei cerchi presenti nel piano, che sono anche completamente contenuti
        se l'argomento c è null, torna 0
    4) esponi il metodo void sposta(int index, Punto p)
        sposta il CerchioNumerato in posizione index nel punto p, tramite l'apposito setter.
        se l'indice è sbagliato o p è null, non fare nulla.
        l'indice segue l'ordine di inserimento.
        se, nella sua nuova posizione, il cerchio numerato è completamente contenuto da un altro cerchio, 
        va assorbito dal cerchio più grande e rimosso. si ricorda di fare spazio in modo ordinato.
    5) esponi il metodo long[] daiNumeriOrdinati()
        ritorna il numero personale di ciascun cerchio ordinati dal maggiore al minore.
    6) esponi il metodo CerchioNumerato sommaCerchi()
        questo ritorna un cerchio che, grazie alla posizione e al raggio, e' in grado di contenere tutti i cerchi presenti
        in modo perfetto, cioè senza prendere piu' raggio del minimo indispensabile. (avvicinati il piu' possibile)
        chiamando questo metodo, tutti i cerchi attualmente presenti vanno a 0 e vengono rimossi, mentre il cerchio in output
        possiede un numero pari alla loro somma.
        Sse quando viene chiamato questo metodo non ci sono cerchi, viene lanciata una EmptyPlaneException.
        Questa è un'eccezione custom che estende RuntimeException e che deve avere due costruttori: 
        uno che accetta un messaggio e lo da a super, e l'altro no.

    e' vietato usare classi all'infuori di String, Object e i filgi di Exception
    e' vietato esporre metodi e instanze pubbliche non indicate
*/

public class CerchioNumerato implements Cerchio {

    @Override
    public float getX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }

    @Override
    public float getY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getY'");
    }

    @Override
    public float getRaggio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRaggio'");
    }
    
}
