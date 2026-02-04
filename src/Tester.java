public class Tester {

    public static void main(String[] args) throws Exception {
        assertThrows(() -> { new Prodotto(null, 1); }, new IllegalArgumentException(), "Prodotto: ID null");
        assertThrows(() -> { new Prodotto("123", 1); }, new IllegalArgumentException(), "Prodotto: ID corto");
        assertThrows(() -> { new Prodotto("12345678901", 1); }, new IllegalArgumentException(), "Prodotto: ID lungo");
        assertThrows(() -> { new Prodotto("", 1); }, new IllegalArgumentException(), "Prodotto: ID vuoto");
        assertThrows(() -> { new Prodotto("123456789", 1); }, new IllegalArgumentException(), "Prodotto: ID 9");
        assertThrows(() -> { new Prodotto("1234567890 ", 1); }, new IllegalArgumentException(), "Prodotto: ID 11 con spazio");
        assertThrows(() -> { new Prodotto("aaaaaaaaa", 1); }, new IllegalArgumentException(), "Prodotto: ID 9 lettere");
        assertNotThorws(() -> { new Prodotto("1234567890", 1); }, "Prodotto: costruttore valido");
        assertNotThorws(() -> { new Prodotto("AAAAAAAAAA", 1); }, "Prodotto: ID 10 lettere");
        assertNotThorws(() -> { new Prodotto("          ", 1); }, "Prodotto: ID 10 spazi");
        assertEquals(() -> new Prodotto("1234567890", 5).getID(), "1234567890", "Prodotto: getID");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", 5).getUnita(), 5, "Prodotto: getUnita positiva");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", 0).getUnita(), 0, "Prodotto: unita 0 rimane 0");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", -1).getUnita(), 0, "Prodotto: unita negativa diventa 0");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", -999).getUnita(), 0, "Prodotto: unita molto negativa diventa 0");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", 1).presente(), true, "Prodotto: presente true");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", 0).presente(), false, "Prodotto: presente false con 0");
        assertEquals(() -> new Prodotto("ABCDEFGHIJ", -3).presente(), false, "Prodotto: presente false con negativo");
        assertThrows(() -> { new Prodotto("ABCDEFGHIJ", 3).consuma(-1); }, new IllegalArgumentException(), "Prodotto: consuma qta negativa");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); return p.consuma(0); }, true, "Prodotto: consuma 0 true");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); p.consuma(0); return p.getUnita(); }, 3, "Prodotto: consuma 0 non cambia");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); return p.consuma(2); }, true, "Prodotto: consuma meno");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); p.consuma(2); return p.getUnita(); }, 1, "Prodotto: unita dopo consumo");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); return p.consuma(3); }, true, "Prodotto: consuma esatto");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); p.consuma(3); return p.getUnita(); }, 0, "Prodotto: unita va a 0");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); p.consuma(3); return p.presente(); }, false, "Prodotto: presente false dopo zero");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); return p.consuma(4); }, false, "Prodotto: consuma troppo false");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 3); p.consuma(4); return p.getUnita(); }, 3, "Prodotto: consuma troppo non cambia");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 1); p.consuma(1); return p.consuma(1); }, false, "Prodotto: doppio consumo oltre");
        assertEquals(() -> { Prodotto p = new Prodotto("ABCDEFGHIJ", 1); p.consuma(1); return p.getUnita(); }, 0, "Prodotto: dopo consumo a zero rimane zero");

        assertNotThorws(() -> { new Magazzino(0); }, "Magazzino: costruttore max 0");
        assertNotThorws(() -> { new Magazzino(1); }, "Magazzino: costruttore max 1");
        assertNotThorws(() -> { new Magazzino(10); }, "Magazzino: costruttore max 10");

        assertEquals(() -> new Magazzino(1).aggiungi(null), false, "Magazzino: aggiungi null false");
        assertEquals(() -> new Magazzino(1).aggiungi(new Prodotto("ABCDEFGHIJ", 0)), false, "Magazzino: aggiungi prodotto senza unita false");
        assertEquals(() -> new Magazzino(1).aggiungi(new Prodotto("ABCDEFGHIJ", -3)), false, "Magazzino: aggiungi prodotto negativo (0) false");
        assertEquals(() -> { Magazzino m = new Magazzino(1); return m.aggiungi(new Prodotto("ABCDEFGHIJ", 1)); }, true, "Magazzino: aggiungi primo true");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 1)); return m.aggiungi(new Prodotto("KLMNOPQRST", 1)); }, false, "Magazzino: aggiungi secondo diverso senza spazio false");
        assertEquals(() -> { Magazzino m = new Magazzino(2); m.aggiungi(new Prodotto("ABCDEFGHIJ", 1)); return m.aggiungi(new Prodotto("KLMNOPQRST", 1)); }, true, "Magazzino: aggiungi secondo diverso con spazio true");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 2)); return m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); }, true, "Magazzino: aggiungi stesso ID somma true");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 2)); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); return m.consumaUnita("ABCDEFGHIJ", 5); }, true, "Magazzino: somma e consuma tutto");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 2)); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); return m.consumaUnita("ABCDEFGHIJ", 6); }, false, "Magazzino: somma ma consumo troppo");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 2)); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); m.consumaUnita("ABCDEFGHIJ", 6); return m.consumaUnita("ABCDEFGHIJ", 5); }, true, "Magazzino: consumo troppo non cambia, poi consuma ok");

        assertThrows(() -> { new Magazzino(1).consumaUnita("ABCDEFGHIJ", -1); }, new IllegalArgumentException(), "Magazzino: consumaUnita num negativo");
        assertEquals(() -> new Magazzino(1).consumaUnita("ABCDEFGHIJ", 1), false, "Magazzino: consumaUnita su id inesistente false");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); return m.consumaUnita("KLMNOPQRST", 1); }, false, "Magazzino: consumaUnita id diverso false");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); return m.consumaUnita("ABCDEFGHIJ", 4); }, false, "Magazzino: consumaUnita oltre disponibilita false");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); m.consumaUnita("ABCDEFGHIJ", 4); return m.consumaUnita("ABCDEFGHIJ", 3); }, true, "Magazzino: consumo oltre non cambia, poi consuma tutto");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); return m.consumaUnita("ABCDEFGHIJ", 0); }, true, "Magazzino: consumaUnita 0 true");
        assertEquals(() -> { Magazzino m = new Magazzino(1); m.aggiungi(new Prodotto("ABCDEFGHIJ", 3)); m.consumaUnita("ABCDEFGHIJ", 0); return m.consumaUnita("ABCDEFGHIJ", 3); }, true, "Magazzino: consuma 0 poi consuma tutto");

        assertEquals(() -> {
            Magazzino m = new Magazzino(1);
            m.aggiungi(new Prodotto("ABCDEFGHIJ", 1));
            m.consumaUnita("ABCDEFGHIJ", 1);
            return m.aggiungi(new Prodotto("KLMNOPQRST", 1));
        }, true, "Magazzino: libera spazio quando unita va a 0");

        assertEquals(() -> {
            Magazzino m = new Magazzino(2);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            m.aggiungi(new Prodotto("BBBBBBBBBB", 1));
            m.consumaUnita("AAAAAAAAAA", 1);
            return m.aggiungi(new Prodotto("CCCCCCCCCC", 1));
        }, true, "Magazzino: elimina primo e consente nuovo inserimento");

        assertEquals(() -> {
            Magazzino m = new Magazzino(2);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            m.aggiungi(new Prodotto("BBBBBBBBBB", 2));
            m.consumaUnita("BBBBBBBBBB", 2);
            return m.aggiungi(new Prodotto("CCCCCCCCCC", 1));
        }, true, "Magazzino: elimina secondo e consente nuovo inserimento");

        assertEquals(() -> {
            Magazzino m = new Magazzino(2);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            m.aggiungi(new Prodotto("BBBBBBBBBB", 1));
            m.consumaUnita("AAAAAAAAAA", 1);
            m.consumaUnita("BBBBBBBBBB", 1);
            return m.aggiungi(new Prodotto("CCCCCCCCCC", 1));
        }, true, "Magazzino: elimina entrambi e consente nuovo inserimento");

        assertEquals(() -> {
            Magazzino m = new Magazzino(2);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 5));
            m.aggiungi(new Prodotto("BBBBBBBBBB", 5));
            m.consumaUnita("AAAAAAAAAA", 3);
            m.consumaUnita("BBBBBBBBBB", 2);
            return m.consumaUnita("AAAAAAAAAA", 2);
        }, true, "Magazzino: consumi multipli fino a zero");

        assertEquals(() -> {
            Magazzino m = new Magazzino(2);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 5));
            m.consumaUnita("AAAAAAAAAA", 5);
            return m.consumaUnita("AAAAAAAAAA", 1);
        }, false, "Magazzino: dopo eliminazione id non trovato");

        assertEquals(() -> {
            Magazzino m = new Magazzino(3);
            m.aggiungi(new Prodotto("1111111111", 1));
            m.aggiungi(new Prodotto("2222222222", 2));
            m.aggiungi(new Prodotto("3333333333", 3));
            m.consumaUnita("2222222222", 2);
            return m.aggiungi(new Prodotto("4444444444", 4));
        }, true, "Magazzino: elimina in mezzo e shift corretto");

        assertEquals(() -> {
            Magazzino m = new Magazzino(3);
            m.aggiungi(new Prodotto("1111111111", 1));
            m.aggiungi(new Prodotto("2222222222", 2));
            m.aggiungi(new Prodotto("3333333333", 3));
            m.consumaUnita("2222222222", 2);
            return m.consumaUnita("3333333333", 3);
        }, true, "Magazzino: dopo shift, ultimo ancora accessibile");

        assertEquals(() -> {
            Magazzino m = new Magazzino(3);
            m.aggiungi(new Prodotto("1111111111", 1));
            m.aggiungi(new Prodotto("2222222222", 2));
            m.aggiungi(new Prodotto("3333333333", 3));
            m.consumaUnita("2222222222", 2);
            return m.consumaUnita("1111111111", 1);
        }, true, "Magazzino: dopo shift, primo ancora accessibile");

        assertEquals(() -> {
            Magazzino m = new Magazzino(1);
            m.aggiungi(new Prodotto("ZZZZZZZZZZ", 10));
            m.aggiungi(new Prodotto("ZZZZZZZZZZ", 1));
            return m.consumaUnita("ZZZZZZZZZZ", 11);
        }, true, "Magazzino: somma su ID uguale e consuma totale");

        assertEquals(() -> {
            Magazzino m = new Magazzino(1);
            m.aggiungi(new Prodotto("ZZZZZZZZZZ", 10));
            m.aggiungi(new Prodotto("ZZZZZZZZZZ", 1));
            m.consumaUnita("ZZZZZZZZZZ", 11);
            return m.aggiungi(new Prodotto("YYYYYYYYYY", 1));
        }, true, "Magazzino: dopo consumo totale libera slot per nuovo");

        assertEquals(() -> {
            Magazzino m = new Magazzino(2);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            return m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
        }, true, "Magazzino: aggiungi stesso ID con spazio comunque true");

        assertEquals(() -> {
            Magazzino m = new Magazzino(1);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            return m.aggiungi(new Prodotto("AAAAAAAAAA", 2));
        }, true, "Magazzino: aggiungi stesso ID senza spazio (non serve spazio)");

        assertEquals(() -> {
            Magazzino m = new Magazzino(1);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            m.aggiungi(new Prodotto("AAAAAAAAAA", 2));
            return m.consumaUnita("AAAAAAAAAA", 2);
        }, true, "Magazzino: somma e consumo parziale");

        assertEquals(() -> {
            Magazzino m = new Magazzino(1);
            m.aggiungi(new Prodotto("AAAAAAAAAA", 1));
            m.aggiungi(new Prodotto("AAAAAAAAAA", 2));
            m.consumaUnita("AAAAAAAAAA", 2);
            return m.consumaUnita("AAAAAAAAAA", 2);
        }, false, "Magazzino: dopo consumo parziale non abbastanza");

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


