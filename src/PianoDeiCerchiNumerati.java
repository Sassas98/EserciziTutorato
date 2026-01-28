public class PianoDeiCerchiNumerati {
    private final CerchioNumerato[] a;
    private int size;

    public PianoDeiCerchiNumerati(int max) {
        if (max <= 0) max = 1;
        this.a = new CerchioNumerato[max];
        this.size = 0;
    }

    public int capacita() {
        return a.length;
    }

    public int size() {
        return size;
    }

    public CerchioNumerato get(int index) {
        if (index < 0 || index >= size) return null;
        return a[index];
    }

    public boolean aggiungi(CerchioNumerato c) {
        if (c == null) return false;
        if (c.getNumero() == 0) return false;
        if (size >= a.length) return false;

        a[size] = c;
        size++;

        int container = trovaContenitorePiuGrande(size - 1);
        if (container != -1) {
            a[container].assorbi(c);
            if (c.getNumero() == 0) rimuoviInPosizione(size - 1);
        }
        return true;
    }

    public int contaContenuti(Cerchio c) {
        if (c == null) return 0;
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(a[i])) cnt++;
        }
        return cnt;
    }

    public void sposta(int index, Punto p) {
        if (index < 0 || index >= size) return;
        if (p == null) return;

        CerchioNumerato moved = a[index];
        moved.setPosizione(p);

        int container = trovaContenitorePiuGrande(index);
        if (container != -1) {
            a[container].assorbi(moved);
            if (moved.getNumero() == 0) rimuoviInPosizione(index);
        }
    }

    public long[] daiNumeriOrdinati() {
        long[] out = new long[size];
        for (int i = 0; i < size; i++) {
            out[i] = a[i].getNumero();
            for (int j = i-1; j >= 0; j--) {
                if (out[j] < out[j+1]) {
                    long temp = out[j+1];
                    out[j+1] = out[j];
                    out[j] = temp;
                }
            }
        }
        return out;
    }

    private int trovaContenitorePiuGrande(int idx) {
        CerchioNumerato target = a[idx];
        int best = -1;
        float bestR = -1f;
        for (int i = 0; i < size; i++) {
            if (i == idx) continue;
            CerchioNumerato cur = a[i];
            if (cur.contains(target)) {
                float r = cur.getRaggio();
                if (r > bestR) {
                    bestR = r;
                    best = i;
                }
            }
        }
        return best;
    }

    private void rimuoviInPosizione(int index) {
        if (index < 0 || index >= size) return;
        for (int i = index; i < size - 1; i++) a[i] = a[i + 1];
        a[size - 1] = null;
        size--;
    }

    public CerchioNumerato sommaCerchi() {
        if (size == 0) throw new EmptyPlaneException();
        Punto p = daiPuntoMedio();
        float r = allarga(p);
        r = restringi(p, r);
        Cerchio c = controllaTutteLeDirezioni(p, r);
        CerchioNumerato result = new CerchioNumerato(c.getX(), c.getY(), c.getRaggio(), 0);
        for (int i = 0; i < size; i++) {
            result.assorbi(a[i]);
            a[i] = null;
        }
        size = 0;
        return result;
    }

    private float allarga(Punto p){
        float result;
        for(result = 1f; !contieneTutti(daiCerchio(p, result)); result*=2);
        return result;
    }

    public static final float EPS = 1e-6f;

    private float restringi(Punto p, float start){
        float max = start, min = 0, mid = start/2, champion = start;
        while(max-mid >= EPS){
            if(contieneTutti(daiCerchio(p, mid))){
                champion = mid;
                max = mid;
                mid = (mid+min) / 2;
            } else{
                min = mid;
                mid = (max+mid) / 2;
            }
        }
        return champion;
    }

    private Cerchio controllaTutteLeDirezioni(Punto p, float start){
        Cerchio c = daiCerchio(p, start);
        Punto[] directions = generaDirezioni();
        for (int i = 0; i < directions.length; i++) {
            p = controllaDirezione(c, directions[i]);
            c = daiCerchio(p, restringi(p, c.getRaggio()));
        }
        return c;
    }

    private Punto controllaDirezione(Cerchio c, Punto vector){
        Punto base = c;
        float mol = EPS;
        Cerchio test = daiCerchio(somma(c, moltiplica(vector, mol)), c.getRaggio());
        if(contieneTutti(test)){
            float max = c.getRaggio(), min = 0, mid = EPS;
            while(max-mid >= EPS){
                test = daiCerchio(somma(c, moltiplica(vector, mid)), c.getRaggio());
                if(contieneTutti(test)){
                    mol = mid;
                    min = mid;
                    mid = (max+mid) / 2;
                } else{
                    max = mid;
                    mid = (mid+min) / 2;
                }
            }
            test = daiCerchio(somma(c, moltiplica(vector, mol)), c.getRaggio());
            base = media(base, test);
        }
        return base;
    }

    private Punto[] generaDirezioni(){
        float[][] directions = {
            {1f, 0f}, {0.85f, 0.35f}, {0.7f, 0.7f}, 
            {0.35f, 0.85f}, {0f, 1f}
        };
        float[][] result = new float[directions.length*4][2];
        for (int i = 0; i < directions.length; i++) {
            result[i] = directions[i];
            result[i+directions.length] = new float[]{directions[i][1], -directions[i][0]};
            result[i+2*directions.length] = new float[]{-directions[i][0], -directions[i][1]};
            result[i+3*directions.length] = new float[]{-directions[i][1], directions[i][0]};
        }
        return generaDirezioni(result);
    }

    private Punto[] generaDirezioni(float[][] dirs){
        Punto[] points = new Punto[dirs.length];
        for (int i = 0; i < dirs.length; i++) {
            points[i] = new PuntoFisso(dirs[i][0], dirs[i][1]);
        }
        return points;
    }

    private Punto media(Punto p1, Punto p2){
        float x = (p1.getX() + p2.getX()) / 2;
        float y = (p1.getY() + p2.getY()) / 2;
        return new PuntoFisso(x, y);
    }

    private Punto somma(Punto p1, Punto p2){
        return new PuntoFisso(p1.getX()+p2.getX(), p1.getY()+p2.getY());
    }

    private Punto moltiplica(Punto p1, float mol){
        return new PuntoFisso(p1.getX()*mol, p1.getY()*mol);
    }

    private Cerchio daiCerchio(Punto p, float r){
        return new CerchioNumerato(p.getX(), p.getY(), r, 0);
    }

    private boolean contieneTutti(Cerchio c){
        for (int i = 0; i < size; i++) {
            if(!c.contains(a[i]))
                return false;
        }
        return true;
    }

    private Punto daiPuntoMedio(){
        float x = 0, y = 0;
        for (int i = 0; i < size; i++) {
            x += a[i].getX();
            y += a[i].getY();
        }
        return size > 0 ? new PuntoFisso(x/size, y/size) : new PuntoFisso(x, y);
    }

}
