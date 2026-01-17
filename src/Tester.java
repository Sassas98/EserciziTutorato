public class Tester {

    public static void main(String[] args) throws Exception {
        // DECOMMENTA PER TESTARE

        /* 
        Sudoku9x9 vuoto = new Sudoku9x9();

        assertEquals(() -> vuoto.daiStato().length, 9, "costruttore vuoto: numero righe");
        assertEquals(() -> vuoto.daiStato()[0].length, 9, "costruttore vuoto: numero colonne");
        assertEquals(() -> vuoto.daiStato()[0][0], 0, "costruttore vuoto: celle inizializzate a zero", new String[]{"(1,1)"});
        assertEquals(() -> vuoto.daiStato()[8][8], 0, "costruttore vuoto: celle inizializzate a zero", new String[]{"(9,9)"});

        assertEquals(() -> {
            int[][] s1 = vuoto.daiStato();
            int[][] s2 = vuoto.daiStato();
            return s1 == s2;
        }, false, "daiStato: restituisce una nuova matrice");

        assertEquals(() -> {
            int[][] s1 = vuoto.daiStato();
            s1[0][0] = 9;
            return vuoto.daiStato()[0][0];
        }, 0, "daiStato: la copia non modifica lo stato interno");

        assertEquals(() -> vuoto.checkRiga(1), true, "checkRiga: riga vuota valida", new String[]{"1"});
        assertEquals(() -> vuoto.checkColonna(1), true, "checkColonna: colonna vuota valida", new String[]{"1"});
        assertEquals(() -> vuoto.checkArea(1), true, "checkArea: area vuota valida", new String[]{"1"});

        int[][] inizialeValido = new int[][]{
            new int[]{5,3,0,0,7,0,0,0,0},
            new int[]{6,0,0,1,9,5,0,0,0},
            new int[]{0,9,8,0,0,0,0,6,0},
            new int[]{8,0,0,0,6,0,0,0,3},
            new int[]{4,0,0,8,0,3,0,0,1},
            new int[]{7,0,0,0,2,0,0,0,6},
            new int[]{0,6,0,0,0,0,2,8,0},
            new int[]{0,0,0,4,1,9,0,0,5},
            new int[]{0,0,0,0,8,0,0,7,9}
        };

        Sudoku9x9 sValido = new Sudoku9x9(inizialeValido);
        assertEquals(() -> sValido.checkRiga(1), true, "costruttore con matrice: riga valida", new String[]{"1"});
        assertEquals(() -> sValido.checkColonna(1), true, "costruttore con matrice: colonna valida", new String[]{"1"});
        assertEquals(() -> sValido.checkArea(1), true, "costruttore con matrice: area valida", new String[]{"1"});

        assertEquals(() -> sValido.daiStato()[0][0], 5, "costruttore con matrice: valore iniziale presente", new String[]{"(1,1)"});
        assertEquals(() -> sValido.daiStato()[0][2], 0, "costruttore con matrice: zeri ammessi", new String[]{"(1,3)"});

        assertEquals(() -> sValido.addNumber(1, 3, 4), true, "addNumber: inserimento valido", new String[]{"x=1", "y=3", "n=4"});
        assertEquals(() -> sValido.daiStato()[0][2], 4, "addNumber: valore inserito correttamente", new String[]{"(1,3)"});

        assertEquals(() -> sValido.addNumber(1, 4, 5), false, "addNumber: rifiuta conflitto di riga", new String[]{"x=1", "y=4", "n=5"});
        assertEquals(() -> sValido.addNumber(7, 1, 5), false, "addNumber: rifiuta conflitto di colonna", new String[]{"x=7", "y=1", "n=5"});
        assertEquals(() -> sValido.addNumber(2, 2, 9), false, "addNumber: rifiuta conflitto di area", new String[]{"x=2", "y=2", "n=9"});

        assertThrows(() -> sValido.addNumber(1, 3, 6), new IllegalStateException(), "addNumber: lancia eccezione se casella occupata");
        assertThrows(() -> sValido.addNumber(0, 1, 1), new IllegalArgumentException(), "addNumber: lancia eccezione su indice fuori range (x)");
        assertThrows(() -> sValido.addNumber(1, 0, 1), new IllegalArgumentException(), "addNumber: lancia eccezione su indice fuori range (y)");
        assertThrows(() -> sValido.addNumber(1, 1, 0), new IllegalArgumentException(), "addNumber: lancia eccezione su valore fuori range (n)");
        assertThrows(() -> sValido.checkRiga(0), new IllegalArgumentException(), "checkRiga: lancia eccezione su indice fuori range");
        assertThrows(() -> sValido.checkColonna(10), new IllegalArgumentException(), "checkColonna: lancia eccezione su indice fuori range");
        assertThrows(() -> sValido.checkArea(0), new IllegalArgumentException(), "checkArea: lancia eccezione su indice fuori range");

        assertThrows(() -> new Sudoku9x9(null), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su input nullo");

        assertThrows(() -> new Sudoku9x9(new int[][]{ new int[]{0} }), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su dimensioni errate (righe/colonne)");

        int[][] dimensioneErrata = new int[9][];
        for (int i = 0; i < 9; i++) dimensioneErrata[i] = new int[8];
        assertThrows(() -> new Sudoku9x9(dimensioneErrata), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su dimensioni errate (colonne)");

        int[][] valoriFuoriRange = new int[9][9];
        valoriFuoriRange[0][0] = 10;
        assertThrows(() -> new Sudoku9x9(valoriFuoriRange), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su valori fuori range");

        int[][] conflittoRiga = new int[9][9];
        conflittoRiga[0][0] = 1;
        conflittoRiga[0][1] = 1;
        assertThrows(() -> new Sudoku9x9(conflittoRiga), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su conflitto di riga");

        int[][] conflittoColonna = new int[9][9];
        conflittoColonna[0][0] = 2;
        conflittoColonna[1][0] = 2;
        assertThrows(() -> new Sudoku9x9(conflittoColonna), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su conflitto di colonna");

        int[][] conflittoArea = new int[9][9];
        conflittoArea[0][0] = 3;
        conflittoArea[1][1] = 3;
        assertThrows(() -> new Sudoku9x9(conflittoArea), new IllegalArgumentException(), "costruttore con matrice: lancia eccezione su conflitto di area");

        int[][] base = new int[9][9];
        Sudoku9x9 s = new Sudoku9x9(base);
        assertEquals(() -> s.addNumber(1, 1, 1), true, "sequenza inserimenti: primo inserimento valido", new String[]{"(1,1)=1"});
        assertEquals(() -> s.addNumber(1, 2, 2), true, "sequenza inserimenti: secondo inserimento valido", new String[]{"(1,2)=2"});
        assertEquals(() -> s.addNumber(2, 1, 3), true, "sequenza inserimenti: terzo inserimento valido", new String[]{"(2,1)=3"});
        assertEquals(() -> s.addNumber(2, 2, 4), true, "sequenza inserimenti: quarto inserimento valido", new String[]{"(2,2)=4"});
        assertEquals(() -> s.checkArea(1), true, "sequenza inserimenti: area 1 valida dopo inserimenti", new String[]{"area=1"});
        assertEquals(() -> s.checkRiga(1), true, "sequenza inserimenti: riga 1 valida dopo inserimenti", new String[]{"riga=1"});
        assertEquals(() -> s.checkColonna(1), true, "sequenza inserimenti: colonna 1 valida dopo inserimenti", new String[]{"colonna=1"});

        runAndPrintAll();
        */
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


