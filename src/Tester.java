public class Tester {

    public static void main(String[] args) throws Exception {
        // DECOMMENTA X TESTARE

        /* 
        assertEquals(() -> new PuntoFisso(3f, 4f).getX(), 3f, "PuntoFisso getX");
        assertEquals(() -> new PuntoFisso(3f, 4f).getY(), 4f, "PuntoFisso getY");

        assertEquals(() -> new CerchioNumerato(0f, 0f, 5f, 10L).getX(), 0f, "CerchioNumerato getX");
        assertEquals(() -> new CerchioNumerato(0f, 0f, 5f, 10L).getY(), 0f, "CerchioNumerato getY");
        assertEquals(() -> new CerchioNumerato(0f, 0f, 5f, 10L).getRaggio(), 5f, "CerchioNumerato getRaggio");

        assertThrows(() -> { new CerchioNumerato(0f, 0f, 0f, 1L); }, new IllegalArgumentException(), "Raggio zero");
        assertThrows(() -> { new CerchioNumerato(0f, 0f, -1f, 1L); }, new IllegalArgumentException(), "Raggio negativo");

        assertEquals(() -> new PuntoFisso(0f, 0f).distanza(new PuntoFisso(3f, 4f)), 5f, "Distanza 3-4-5");
        assertEquals(() -> new PuntoFisso(1f, 1f).distanza(new PuntoFisso(1f, 1f)), 0f, "Distanza zero");
        assertEquals(() -> new PuntoFisso(-1f, -1f).distanza(new PuntoFisso(2f, 3f)), 5f, "Distanza con negativi");
        assertEquals(() -> new CerchioNumerato(0f, 0f, 1f, 1L).distanza(new CerchioNumerato(0f, 0f, 2f, 2L)), 0f, "Distanza tra cerchi stesso centro");

        assertEquals(() -> new CerchioNumerato(0f, 0f, 10f, 1L).contains(new CerchioNumerato(0f, 0f, 2f, 1L)), true, "Contains stesso centro");
        assertEquals(() -> new CerchioNumerato(0f, 0f, 3f, 1L).contains(new CerchioNumerato(4f, 0f, 1f, 1L)), false, "Contains fuori");
        assertEquals(() -> new CerchioNumerato(0f, 0f, 5f, 1L).contains(new CerchioNumerato(3f, 4f, 0.1f, 1L)), false, "Contains oltre il bordo di tolleranza");
        assertEquals(() -> new CerchioNumerato(0f, 0f, 5f, 1L).contains(null), false, "Contains null");

        assertEquals(() -> {
            CerchioNumerato a = new CerchioNumerato(0f, 0f, 10f, 5L);
            CerchioNumerato b = new CerchioNumerato(0f, 0f, 2f, 7L);
            a.assorbi(b);
            return b.getNumero();
        }, 0L, "Assorbi azzera contenuto");

        assertEquals(() -> {
            CerchioNumerato a = new CerchioNumerato(0f, 0f, 10f, 5L);
            CerchioNumerato b = new CerchioNumerato(0f, 0f, 2f, 7L);
            a.assorbi(b);
            return a.getNumero();
        }, 12L, "Assorbi somma numeri");

        assertEquals(() -> {
            CerchioNumerato a = new CerchioNumerato(0f, 0f, 2f, 5L);
            CerchioNumerato b = new CerchioNumerato(10f, 0f, 2f, 7L);
            a.assorbi(b);
            return b.getNumero();
        }, 7L, "Assorbi non contenuto non azzera");

        assertEquals(() -> {
            CerchioNumerato a = new CerchioNumerato(0f, 0f, 10f, 5L);
            CerchioNumerato b = new CerchioNumerato(0f, 0f, 2f, 0L);
            a.assorbi(b);
            return a.getNumero();
        }, 5L, "Assorbi ignora numero 0");

        assertEquals(() -> {
            CerchioNumerato a = new CerchioNumerato(1f, 2f, 3f, 9L);
            a.setPosizione(new PuntoFisso(7f, 8f));
            return a.getX();
        }, 7f, "Setter posizione aggiorna X");

        assertEquals(() -> {
            CerchioNumerato a = new CerchioNumerato(1f, 2f, 3f, 9L);
            a.setPosizione(new PuntoFisso(7f, 8f));
            return a.getY();
        }, 8f, "Setter posizione aggiorna Y");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(0);
            return p.capacita();
        }, 1, "Costruttore max<=0 => 1");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            return p.capacita();
        }, 2, "Capacita normale");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            return p.aggiungi(null);
        }, false, "Aggiungi null");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            return p.aggiungi(new CerchioNumerato(0f, 0f, 1f, 0L));
        }, false, "Aggiungi numero 0");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            return p.aggiungi(new CerchioNumerato(0f, 0f, 1f, 3L));
        }, true, "Aggiungi ok");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(1);
            p.aggiungi(new CerchioNumerato(0f, 0f, 1f, 3L));
            return p.aggiungi(new CerchioNumerato(2f, 0f, 1f, 4L));
        }, false, "Aggiungi senza spazio");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(3);
            p.aggiungi(new CerchioNumerato(0f, 0f, 10f, 10L));
            p.aggiungi(new CerchioNumerato(0f, 0f, 1f, 5L));
            return p.size();
        }, 1, "Aggiungi assorbito e rimosso libera spazio");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(3);
            p.aggiungi(new CerchioNumerato(0f, 0f, 10f, 10L));
            p.aggiungi(new CerchioNumerato(0f, 0f, 1f, 5L));
            return p.get(0).getNumero();
        }, 15L, "Aggiungi: assorbimento somma");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(5);
            p.aggiungi(new CerchioNumerato(0f, 1f, 2f, 1L));
            p.aggiungi(new CerchioNumerato(5f, 0f, 1f, 2L));
            p.aggiungi(new CerchioNumerato(1f, 0f, 1f, 3L));
            return p.contaContenuti(new CerchioNumerato(0f, 0f, 3f, 9L));
        }, 2, "Conta contenuti");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(5);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 1L));
            p.aggiungi(new CerchioNumerato(5f, 0f, 1f, 2L));
            return p.contaContenuti(null);
        }, 0, "Conta contenuti con null");

        assertNotThorws(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 1L));
            p.sposta(-1, new PuntoFisso(1f, 1f));
        }, "Sposta indice negativo non fa nulla");

        assertNotThorws(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 1L));
            p.sposta(0, null);
        }, "Sposta punto null non fa nulla");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 1L));
            p.sposta(0, new PuntoFisso(7f, 8f));
            return p.get(0).getX();
        }, 7f, "Sposta aggiorna X");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(2);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 1L));
            p.sposta(0, new PuntoFisso(7f, 8f));
            return p.get(0).getY();
        }, 8f, "Sposta aggiorna Y");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(5);
            p.aggiungi(new CerchioNumerato(0f, 0f, 10f, 10L));
            p.aggiungi(new CerchioNumerato(20f, 0f, 2f, 3L));
            p.aggiungi(new CerchioNumerato(40f, 0f, 2f, 4L));
            p.sposta(1, new PuntoFisso(0f, 0f));
            return p.size();
        }, 2, "Sposta dentro altro: rimosso");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(5);
            p.aggiungi(new CerchioNumerato(0f, 0f, 10f, 10L));
            p.aggiungi(new CerchioNumerato(20f, 0f, 2f, 3L));
            p.sposta(1, new PuntoFisso(0f, 0f));
            return p.get(0).getNumero();
        }, 13L, "Sposta dentro altro: assorbito somma");

        assertArrayEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(5);
            p.aggiungi(new CerchioNumerato(10f, 0f, 1f, 10L));
            p.aggiungi(new CerchioNumerato(0f, 10f, 1f, 30L));
            p.aggiungi(new CerchioNumerato(0f, 0f, 1f, 20L));
            int[] a = p.daiNumeriOrdinati();
            return new Integer[]{a[0], a[1], a[2]};
        }, new Integer[]{30, 20, 10}, "Numeri ordinati desc");

        assertThrows(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(3);
            p.sommaCerchi();
        }, new EmptyPlaneException(), "Somma cerchi su piano vuoto");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(3);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 5L));
            p.aggiungi(new CerchioNumerato(10f, 0f, 1f, 7L));
            CerchioNumerato s = p.sommaCerchi();
            return s.getNumero();
        }, 12L, "Somma cerchi: numero totale");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(3);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 5L));
            p.aggiungi(new CerchioNumerato(10f, 0f, 1f, 7L));
            p.sommaCerchi();
            return p.size();
        }, 0, "Somma cerchi svuota il piano");

        assertEquals(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(3);
            p.aggiungi(new CerchioNumerato(0f, 0f, 2f, 5L));
            p.aggiungi(new CerchioNumerato(10f, 0f, 1f, 7L));
            CerchioNumerato s = p.sommaCerchi();
            return s.contains(new CerchioNumerato(0f, 0f, 2f, 1L)) && s.contains(new CerchioNumerato(10f, 0f, 1f, 1L));
        }, true, "Somma cerchi contiene tutti");

        assertNotThorws(() -> {
            PianoDeiCerchiNumerati p = new PianoDeiCerchiNumerati(4);
            p.aggiungi(new CerchioNumerato(0f, 0f, 5f, 1L));
            p.aggiungi(new CerchioNumerato(12f, 0f, 5f, 2L));
            CerchioNumerato s = p.sommaCerchi();
            s.contains(new CerchioNumerato(6f, 0f, 1f, 1L));
        }, "Somma cerchi non lancia in caso normale");
        */
        runAndPrintAll();
    }


    @SuppressWarnings("unused")
    private static void assertNotThorws(Runnable function, String description){
        assertNotThorws(function, description, new String[0]);
    }

    private static void assertNotThorws(Runnable function, String description, String[] input){
        testList.add(new Tester(description, function, input));
    }

    @SuppressWarnings("unused")
    private static void assertThrows(Runnable function, Exception expectedException, String description){
        testList.add(new Tester(description, function, expectedException));
    }

    @SuppressWarnings("unused")
    private static void assertArrayEquals(java.util.function.Supplier<Object[]> function, Object[] output, String description){
        assertArrayEquals(function, output, description, new String[0]);
    }

    private static void assertArrayEquals(java.util.function.Supplier<Object[]> function, Object[] output, String description, String[] input){
        testList.add(new Tester(description, function, input, output));
    }

    @SuppressWarnings("unused")
    private static void assertEquals(java.util.function.Supplier<Object> function, Object output, String description){
        assertEquals(function, output, description, new String[0]);
    }

    private static void assertEquals(java.util.function.Supplier<Object> function, Object output, String description, String[] input){
        testList.add(new Tester(description, function, input, output));
    }

    private static void runAndPrintAll(){
        Tester.printResults(testList.stream().map(x -> x.run()).toList());
    }

    private static java.util.List<Tester> testList = new java.util.ArrayList<>();

    private final String description;
    private final java.util.function.Supplier<Object> function;
    private final java.util.function.Supplier<Object[]> functionArray;
    private final String[] input;
    private final Object output;
    private final Object[] outputArray;

    public Tester(String description, java.util.function.Supplier<Object[]> function, String[] input, Object[] output) {
        this.description = description;
        this.functionArray = function;
        this.function = null;
        this.output = null;
        this.input = input;
        this.outputArray = output;
    }

    public Tester(String description, java.util.function.Supplier<Object> function, String[] input, Object output) {
        this.description = description;
        this.function = function;
        this.input = input;
        this.output = output;
        this.outputArray = null;
        this.functionArray = null;
    }

    public Tester(String description, Runnable function, String[] input) {
        this(description, () -> { function.run(); return 0; }, input, 0);
    }

    public Tester(String description, Runnable function, Exception expectedException) {
        this.description = description;
        this.function = () -> { function.run(); return 0; };
        this.input = new String[0];
        this.output = expectedException;
        this.outputArray = null;
        this.functionArray = null;
    }

    public TestResult run(){
        return function == null ? runArray() : runSingle();
    }

    public TestResult runArray() {
        try {
            Object[] out = functionArray.get();
            return outputArray == out || java.util.Arrays.deepEquals(out, outputArray) ? success() : failure(formatCall() + formatArray(out) + " != " + formatArray(outputArray));
        } catch (Exception e) {
            return error(e);
        }
    }

    public String formatArray(Object[] a){
        return "[" + String.join(", ", java.util.Arrays.asList(a).stream().map(x -> x.toString()).toList()) + "]";
    }

    public TestResult runSingle() {
        try {
            Object out = function.get();
            if (isExpectingException()) {
                return failure(description + ": Nessuna eccezione lanciata");
            }
            return output == out || java.util.Objects.equals(output, out) ? success() : failure(formatCall() + out + " != " + output);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private TestResult handleException(Exception e){
        if (!(output instanceof Throwable t)) return error(e);
        Class<?> expected = t.getClass();
        return expected.isInstance(e)
            ? success()
            : failure(description + ": Eccezione diversa: attesa "
                + expected.getName() + " ma ottenuta " + e.getClass().getName()
                + " (" + e.getMessage() + ")");
    }

    private boolean isExpectingException() {
        return (output instanceof Throwable)
                || (output instanceof Class<?> c && Throwable.class.isAssignableFrom(c));
    }

    private String formatCall() {
        StringBuilder sb = new StringBuilder(description).append(": ");
        for (int i = 0; i < input.length; i++) {
            sb.append("[").append(input[i]).append("]");
            sb.append(i + 1 == input.length ? " => " : " ");
        }
        return sb.toString();
    }

    private TestResult success() {
        return new TestResult(TestResultType.SUCCESS, null);
    }

    private TestResult failure(String msg) {
        return new TestResult(TestResultType.FAILURE, msg);
    }

    private TestResult error(Exception e) {
        return new TestResult(TestResultType.ERROR, description + ": [" + e.getClass().getName() + "] " + e.getMessage());
    }

    public static void printResults(java.util.List<TestResult> results){
        int tot = results.size();
        int successes = (int)results.stream().filter(x -> x.type() == TestResultType.SUCCESS).count();
        int failures = (int)results.stream().filter(x -> x.type() == TestResultType.FAILURE).count();
        int errors = (int)results.stream().filter(x -> x.type() == TestResultType.ERROR).count();
        System.out.println("Test superati [" + successes + "/" + tot + "]");
        if(failures > 0){
            System.out.println("Fallimenti: " + failures);
            for(TestResult tr : results){
                if(tr.type() == TestResultType.FAILURE)
                    System.out.println("- " + tr.message());
            }
        }
        if(errors > 0){
            System.out.println("Errori: " + errors);
            for(TestResult tr : results){
                if(tr.type() == TestResultType.ERROR)
                    System.out.println("- " + tr.message());
            }
        }
    }

    private record TestResult(TestResultType type, String message) { }

    private enum TestResultType {
        SUCCESS,
        FAILURE,
        ERROR
    }
}


