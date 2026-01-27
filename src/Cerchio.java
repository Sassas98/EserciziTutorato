public interface Cerchio extends Punto {

    public float getRaggio();
    
    public default boolean contains(Cerchio c){
        return c != null && this.distanza(c) + c.getRaggio() <= this.getRaggio();
    }
}
