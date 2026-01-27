public interface Punto {
    
    public float getX();

    public float getY();

    public default float distanza(Punto p){
        float x = Math.abs(getX() - p.getX());
        float y = Math.abs(getY() - p.getY());
        x *= x;
        y *= y;
        return (float)Math.sqrt(x + y);
    }
}
