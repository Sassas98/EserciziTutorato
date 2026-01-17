
public class Tester {

    public static void main(String[] args) throws Exception {
        assertEquals(() -> 2, 3, "errore", new String[]{"5"});
        assertThrows(() -> {}, new Exception(), "no errore");
        assertNotThorws(() -> {throw new IllegalArgumentException();}, "errore");
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
    private final String[] input;
    private final Object output;

    public Tester(String description, java.util.function.Supplier<Object> function, String[] input, Object output) {
        this.description = description;
        this.function = function;
        this.input = input;
        this.output = output;
    }

    public Tester(String description, Runnable function, String[] input) {
        this(description, () -> { function.run(); return 0; }, input, 0);
    }

    public Tester(String description, Runnable function, Exception expectedException) {
        this.description = description;
        this.function = () -> { function.run(); return 0; };
        this.input = new String[0];
        this.output = expectedException;
    }

    public TestResult run() {
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


