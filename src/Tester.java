public class Tester {

    public static void main(String[] args) throws Exception {
        assertEquals(() -> new FogliaLettera('a').equals(new FogliaLettera('a')), true, "FogliaLettera equals stesso char");
        assertEquals(() -> new FogliaLettera('a').equals(new FogliaLettera('b')), false, "FogliaLettera equals char diverso");
        assertEquals(() -> new FogliaLettera('a').equals(null), false, "FogliaLettera equals null");
        assertEquals(() -> new FogliaLettera('a').equals("a"), false, "FogliaLettera equals tipo diverso");
        assertEquals(() -> new FogliaLettera('x').hashCode() == new FogliaLettera('x').hashCode(), true, "FogliaLettera hashCode coerente");
        assertEquals(() -> new FogliaLettera('x').hashCode() == new FogliaLettera('y').hashCode(), false, "FogliaLettera hashCode diverso (probabile)");

        assertEquals(
                () -> new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i'))
                        .equals(new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i'))),
                true,
                "RamoLettera equals stessa parola"
        );
        assertEquals(
                () -> new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i'))
                        .equals(new RamoLettera(new FogliaLettera('c'), new FogliaLettera('a'))),
                false,
                "RamoLettera equals parola diversa"
        );
        assertEquals(
                () -> new RamoLettera(
                        new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')),
                        new RamoLettera(new FogliaLettera('a'), new FogliaLettera('o'))
                ).equals(
                        new RamoLettera(
                                new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')),
                                new RamoLettera(new FogliaLettera('a'), new FogliaLettera('o'))
                        )
                ),
                true,
                "RamoLettera equals struttura annidata stessa parola"
        );
        assertEquals(
                () -> new RamoLettera(
                        new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')),
                        new RamoLettera(new FogliaLettera('a'), new FogliaLettera('o'))
                ).equals(
                        new RamoLettera(
                                new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')),
                                new RamoLettera(new FogliaLettera('a'), new FogliaLettera('x'))
                        )
                ),
                false,
                "RamoLettera equals struttura annidata parola diversa"
        );
        assertEquals(
                () -> new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')).equals(null),
                false,
                "RamoLettera equals null"
        );
        assertEquals(
                () -> new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')).equals("ci"),
                false,
                "RamoLettera equals tipo diverso"
        );
        assertEquals(
                () -> new RamoLettera(new FogliaLettera('z'), new FogliaLettera('z')).hashCode()
                        == new RamoLettera(new FogliaLettera('z'), new FogliaLettera('z')).hashCode(),
                true,
                "RamoLettera hashCode coerente"
        );

        assertEquals(() -> new CostruttoreDiParole().getParola(null), null, "CostruttoreDiParole.getParola null");
        assertEquals(() -> new CostruttoreDiParole().getParola(new FogliaLettera('a')), "a", "CostruttoreDiParole.getParola foglia");
        assertEquals(
                () -> new CostruttoreDiParole().getParola(new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i'))),
                "ci",
                "CostruttoreDiParole.getParola ramo semplice"
        );
        assertEquals(
                () -> new CostruttoreDiParole().getParola(
                        new RamoLettera(
                                new RamoLettera(new FogliaLettera('c'), new FogliaLettera('i')),
                                new RamoLettera(new FogliaLettera('a'), new FogliaLettera('o'))
                        )
                ),
                "ciao",
                "CostruttoreDiParole.getParola ramo annidato"
        );

        assertEquals(() -> new CostruttoreDiParole().costruisciAlbero(null), null, "CostruttoreDiParole.costruisciAlbero null");
        assertEquals(() -> new CostruttoreDiParole().costruisciAlbero(""), null, "CostruttoreDiParole.costruisciAlbero vuota");
        assertEquals(() -> new CostruttoreDiParole().costruisciAlbero("a").toString(), "a_", "costruisciAlbero dispari 1 char => underscore");
        assertEquals(() -> new CostruttoreDiParole().costruisciAlbero("ciao").toString(), "ciao", "costruisciAlbero pari");
        assertEquals(() -> new CostruttoreDiParole().costruisciAlbero("cia").toString(), "cia_", "costruisciAlbero dispari");
        assertEquals(() -> new CostruttoreDiParole().getParola(new CostruttoreDiParole().costruisciAlbero("ciao")), "ciao", "roundtrip pari");
        assertEquals(() -> new CostruttoreDiParole().getParola(new CostruttoreDiParole().costruisciAlbero("cia")), "cia_", "roundtrip dispari");
        assertEquals(() -> new CostruttoreDiParole().getParola(new CostruttoreDiParole().costruisciAlbero("supercalifragilistichespiralitoso")), "supercalifragilistichespiralitoso_", "roundtrip dispari");

        assertEquals(() -> {
            Scatola s1 = new Scatola(2);
            s1.aggiungi(new Scatola(0));
            Scatola inner = new Scatola(1);
            inner.aggiungi(new Scatola(0));
            s1.aggiungi(inner);

            Scatola s2 = new Scatola(2);
            Scatola inner2 = new Scatola(1);
            inner2.aggiungi(new Scatola(0));
            s2.aggiungi(inner2);
            s2.aggiungi(new Scatola(0));

            return s1.equals(s2);
        }, true, "Scatola equals ignorando ordine");

        assertEquals(() -> {
            Scatola s1 = new Scatola(2);
            s1.aggiungi(new Scatola(0));
            s1.aggiungi(new Scatola(0));

            Scatola s2 = new Scatola(1);
            s2.aggiungi(new Scatola(0));

            return s1.equals(s2);
        }, false, "Scatola equals numero diverso di contenuti");

        assertEquals(() -> {
            Scatola s1 = new Scatola(1);
            Scatola inner = new Scatola(1);
            inner.aggiungi(new Scatola(0));
            s1.aggiungi(inner);

            Scatola s2 = new Scatola(1);
            s2.aggiungi(new Scatola(0));

            return s1.equals(s2);
        }, false, "Scatola equals struttura diversa");

        assertEquals(() -> {
            Scatola s1 = new Scatola(2);
            s1.aggiungi(new Scatola(0));
            Scatola inner = new Scatola(1);
            inner.aggiungi(new Scatola(0));
            s1.aggiungi(inner);

            Scatola s2 = new Scatola(2);
            Scatola inner2 = new Scatola(1);
            inner2.aggiungi(new Scatola(0));
            s2.aggiungi(inner2);
            s2.aggiungi(new Scatola(0));

            return s1.hashCode() == s2.hashCode();
        }, true, "Scatola hashCode coerente con equals (caso ordine diverso)");

        assertEquals(() -> new CollisoreDiHash(1, 2).equals(new CollisoreDiHash(1, 2)), true, "CollisoreDiHash equals");
        assertEquals(() -> new CollisoreDiHash(1, 2).equals(new CollisoreDiHash(1, 3)), false, "CollisoreDiHash equals diverso");

        assertEquals(() -> {
            CollisoreDiHash base = new CollisoreDiHash(10, 20);
            CollisoreDiHash[] arr = base.GetTotCollisioni(5);
            return arr != null && arr.length == 5;
        }, true, "GetTotCollisioni lunghezza");

        assertEquals(() -> {
            CollisoreDiHash base = new CollisoreDiHash(10, 20);
            CollisoreDiHash[] arr = base.GetTotCollisioni(6);
            if (arr == null || arr.length != 6) return false;
            int h = base.hashCode();
            for (CollisoreDiHash c : arr) {
                if (c == null) return false;
                if (c.equals(base)) return false;
                if (c.hashCode() != h) return false;
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].equals(arr[j])) return false;
                }
            }
            return true;
        }, true, "GetTotCollisioni distinti e stesso hashCode");

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


