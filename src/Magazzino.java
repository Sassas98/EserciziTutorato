public class Magazzino {
    private Prodotto[] prodotti;
    private int counter;
    public Magazzino(int max){
        this.prodotti = new Prodotto[max];
    }

    private int trovaIndiceProdottoDaID(String id){
        for (int i = 0; i < counter; i++) {
            if(prodotti[i].getID().equals(id))
                return i;
        }
        return -1;
    }

    public boolean aggiungi(Prodotto p){
        if(p == null || !p.presente())
            return false;
        int index = trovaIndiceProdottoDaID(p.getID());
        if(index == -1 && counter >= prodotti.length) 
            return false;
        if(index != -1)
            prodotti[index] = new Prodotto(p.getID(), p.getUnita() + prodotti[index].getUnita());
        else prodotti[counter++] = p;
        return true;
    }

    public boolean consumaUnita(String id, int num){
        if(num < 0)
            throw new IllegalArgumentException();
        int index = trovaIndiceProdottoDaID(id);
        if(index == -1) return false;
        if(!prodotti[index].consuma(num))
            return false;
        if(!prodotti[index].presente())
            elimina(index);
        return true;
    }

    private void elimina(int index){
        for (int i = index; i < prodotti.length-1; i++) {
            prodotti[i] = prodotti[i+1];
        }
        prodotti[prodotti.length-1] = null;
        counter--;
    }
}
