public class Biblioteca {
    private Libro[] libri;
    private boolean[] prestiti;
    private float penalePerGiornoExtra;
    private int counter;
    public Biblioteca(int max, float penale){
        if(max < 1 || penale <= 0 || (penale*100 - (int)(penale*100)) > 0)
            throw new IllegalArgumentException();
        penalePerGiornoExtra = penale;
        libri = new Libro[max];
        prestiti = new boolean[max];
    }

    public boolean aggiungiLibro(Libro l){
        if(l == null) throw new IllegalArgumentException();
        if(counter >= libri.length) return false;
        libri[counter++] = l;
        return true;
    }

    public String[][] daiTutti(){
        String[][] array = new String[counter][2];
        for(int i = 0; i < counter; i++){
            array[i] = new String[]{libri[i].getTitolo(), libri[i].getAutore()};
        }
        return array;
    }

    public boolean prestaLibro(String titolo, String autore){
        Libro l = new Libro(titolo, autore);
        for(int i = 0; i < counter; i++){
            if(libri[i].equals(l) && !prestiti[i]){
                prestiti[i] = true;
                return true;
            }
        }
        return false;
    }

    public float restituisciLibro(String titolo, String autore, int giorniDiRitardo){
        if(giorniDiRitardo < 0) throw new IllegalArgumentException();
        Libro l = new Libro(titolo, autore);
        for(int i = 0; i < counter; i++){
            if(libri[i].equals(l) && prestiti[i]){
                prestiti[i] = false;
                return giorniDiRitardo * penalePerGiornoExtra;
            }
        }
        return -1;
    }
}
