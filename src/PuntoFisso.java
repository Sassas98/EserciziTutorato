public class PuntoFisso implements Punto{

    private float x, y;

    public PuntoFisso(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

}
