public class Tester {

    public static void main(String[] args) throws Exception {

        assertEquals(() -> new StringArrayList().size(), 0, "size iniziale = 0");
        assertArrayEquals(() -> new StringArrayList().toArray(), new String[]{}, "toArray iniziale vuoto");
        assertEquals(() -> new StringArrayList().get(0), null, "get(0) su lista vuota -> null");
        assertEquals(() -> new StringArrayList().get(-1), null, "get(-1) su lista vuota -> null");

        StringArrayList l1 = new StringArrayList();
        assertEquals(() -> { l1.addLast("a"); return l1.size(); }, 1, "addLast 1 elemento incrementa size");
        assertEquals(() -> l1.get(0), "a", "get(0) dopo addLast");
        assertEquals(() -> l1.get(1), null, "get fuori range dopo addLast -> null");

        assertEquals(() -> { l1.addLast("b"); return l1.size(); }, 2, "addLast 2° elemento incrementa size");
        assertEquals(() -> l1.get(0), "a", "ordine preservato (0)");
        assertEquals(() -> l1.get(1), "b", "ordine preservato (1)");

        assertEquals(() -> { l1.addLast("c"); return l1.size(); }, 3, "addLast 3° elemento incrementa size");
        assertEquals(() -> l1.get(2), "c", "get(2) dopo addLast");

        StringArrayList l2 = new StringArrayList();
        assertEquals(() -> { l2.addLast(null); return l2.size(); }, 1, "addLast(null) incrementa size");
        assertEquals(() -> l2.get(0), null, "get(0) dopo addLast(null) -> null");
        assertArrayEquals(() -> l2.toArray(), new String[]{null}, "toArray contiene null aggiunto");

        StringArrayList l3 = new StringArrayList();
        l3.addLast("x");
        l3.addLast("y");
        l3.addLast("z");
        assertEquals(() -> { l3.removeAt(1); return l3.size(); }, 3, "removeAt non cambia size");
        assertEquals(() -> l3.get(1), null, "removeAt imposta null");
        assertEquals(() -> l3.get(0), "x", "removeAt non sposta (0 invariato)");
        assertEquals(() -> l3.get(2), "z", "removeAt non sposta (2 invariato)");
        assertArrayEquals(() -> l3.toArray(), new String[]{"x", null, "z"}, "toArray mostra buco dopo removeAt");

        assertNotThorws(() -> l3.removeAt(-1), "removeAt(-1) non lancia eccezioni");
        assertNotThorws(() -> l3.removeAt(3), "removeAt(size) non lancia eccezioni");

        StringArrayList l4 = new StringArrayList();
        l4.addLast("a");
        l4.addLast("b");
        l4.addLast("a");
        l4.addLast("c");
        assertEquals(() -> { l4.removeFirst("a"); return l4.size(); }, 4, "removeFirst non cambia size");
        assertArrayEquals(() -> l4.toArray(), new String[]{null, "b", "a", "c"}, "removeFirst rimuove prima occorrenza");
        assertEquals(() -> l4.get(2), "a", "seconda occorrenza resta");

        StringArrayList l5 = new StringArrayList();
        l5.addLast("a");
        l5.addLast("b");
        assertEquals(() -> { l5.removeFirst("x"); return 0; }, 0, "removeFirst su assente non cambia nulla");
        assertArrayEquals(() -> l5.toArray(), new String[]{"a", "b"}, "removeFirst su assente lascia invariato");

        StringArrayList l6 = new StringArrayList();
        l6.addLast("a");
        l6.addLast(null);
        l6.addLast("b");
        assertNotThorws(() -> l6.removeFirst(null), "removeFirst(null) non lancia eccezioni");
        assertArrayEquals(() -> l6.toArray(), new String[]{"a", null, "b"}, "removeFirst(null) non rimuove (implementazione usa equals)");

        StringArrayList l7 = new StringArrayList();
        l7.addLast("a");
        l7.addLast("b");
        l7.addLast("c");
        l7.removeAt(1);
        l7.addLast("d"); 
        l7.removeFirst("a");
        assertArrayEquals(() -> l7.toArray(), new String[]{null, null, "c", "d"}, "stato prima di compact");
        assertEquals(() -> { l7.compact(); return l7.size(); }, 2, "compact riduce size eliminando null");
        assertArrayEquals(() -> l7.toArray(), new String[]{"c", "d"}, "compact preserva ordine e rimuove null");

        StringArrayList l8 = new StringArrayList();
        l8.addLast("x");
        l8.addLast("y");
        assertEquals(() -> { l8.compact(); return l8.size(); }, 2, "compact senza null non cambia size");
        assertArrayEquals(() -> l8.toArray(), new String[]{"x", "y"}, "compact senza null non cambia contenuto");

        StringArrayList l9 = new StringArrayList();
        l9.addLast("x");
        l9.addLast("y");
        l9.removeAt(0);
        l9.removeAt(1);
        assertArrayEquals(() -> l9.toArray(), new String[]{null, null}, "tutti null prima di compact");
        assertEquals(() -> { l9.compact(); return l9.size(); }, 0, "compact su tutti null -> size 0");
        assertArrayEquals(() -> l9.toArray(), new String[]{}, "compact su tutti null -> array vuoto");

        StringArrayList l10 = new StringArrayList();
        l10.addLast("a");
        l10.addLast("b");
        String[] arr = l10.toArray();
        arr[0] = "X";
        assertEquals(() -> l10.get(0), "a", "toArray restituisce copia: modifiche esterne non cambiano la lista");
        assertArrayEquals(() -> l10.toArray(), new String[]{"a", "b"}, "contenuto invariato dopo modifica su copia");

        StringArrayList l11 = new StringArrayList();
        l11.addLast("a");
        l11.addLast("b");
        l11.removeAt(0);
        assertEquals(() -> { l11.clear(); return l11.size(); }, 0, "clear porta size a 0");
        assertArrayEquals(() -> l11.toArray(), new String[]{}, "clear porta array a vuoto");
        assertEquals(() -> l11.get(0), null, "get(0) dopo clear -> null");

        StringArrayList l12 = new StringArrayList();
        int n = 200;
        for (int i = 0; i < n; i++) l12.addLast("v" + i);
        assertEquals(() -> l12.size(), n, "aggiunta di molti elementi -> size corretta");
        assertEquals(() -> l12.get(0), "v0", "stress: primo elemento");
        assertEquals(() -> l12.get(n - 1), "v" + (n - 1), "stress: ultimo elemento");

        StringArrayList l13 = new StringArrayList();
        for (int i = 0; i < n; i++) l13.addLast("v" + i);
        for (int i = 0; i < n; i += 3) l13.removeAt(i);
        assertEquals(() -> l13.size(), n, "dopo removeAt ripetuti size invariata");
        assertNotThorws(() -> l13.compact(), "compact dopo molte rimozioni non lancia eccezioni");
        int expectedAfterCompact = n - (int) Math.ceil(n / 3.0);
        assertEquals(() -> l13.size(), expectedAfterCompact, "compact dopo rimozioni -> size attesa");

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


