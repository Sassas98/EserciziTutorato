public class Tester {

    public static void main(String[] args) throws Exception {
        assertEquals(() -> 2, 3, "errore", new String[]{"5"});
        assertArrayEquals(() -> new Integer[]{1,2,3}, new Integer[]{1,1,3}, "test array");
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

    private final static int REPEATS = 4;

    private static void runAndPrintAll(){
        Tester.printResults(testList.stream().map(x -> {
            TestResult result = x.run();
            long time = result.time/REPEATS;
            long memory = result.memory/REPEATS;
            for (int i = 1; i < REPEATS; i++) {
                result = x.run();
                time += result.time/REPEATS;
                memory += result.memory/REPEATS;
            }
            return new TestResult(result.type(), result.message(), memory, time);
        }).toList());
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
        long startTime = System.nanoTime();
        try {
            Object[] out = functionArray.get();
            Runtime rt = Runtime.getRuntime();
            long memory = rt.totalMemory() - rt.freeMemory();
            long time = System.nanoTime() - startTime;
            return outputArray == out || java.util.Arrays.deepEquals(out, outputArray) ? success(memory, time) : failure(formatCall() + formatArray(out) + " != " + formatArray(outputArray), memory, time);
        } catch (Exception e) {
            Runtime rt = Runtime.getRuntime();
            long memory = rt.totalMemory() - rt.freeMemory();
            long time = System.nanoTime() - startTime;
            return error(e, memory, time);
        }
    }

    public String formatArray(Object[] a){
        return "[" + String.join(", ", java.util.Arrays.asList(a).stream().map(x -> x.toString()).toList()) + "]";
    }

    public TestResult runSingle() {
        long startTime = System.nanoTime();
        try {
            Object out = function.get();
            Runtime rt = Runtime.getRuntime();
            long memory = rt.totalMemory() - rt.freeMemory();
            long time = System.nanoTime() - startTime;
            if (isExpectingException()) {
                return failure(description + ": Nessuna eccezione lanciata", memory, time);
            }
            return output == out || java.util.Objects.equals(output, out) ? success(memory, time) : failure(formatCall() + out + " != " + output, memory, time);
        } catch (Exception e) {
            Runtime rt = Runtime.getRuntime();
            long memory = rt.totalMemory() - rt.freeMemory();
            long time = System.nanoTime() - startTime;
            return handleException(e, memory, time);
        }
    }

    private TestResult handleException(Exception e, long memory, long time) {
        if (!(output instanceof Throwable t)) return error(e, memory, time);
        Class<?> expected = t.getClass();
        return expected.isInstance(e)
            ? success(memory, time)
            : failure(description + ": Eccezione diversa: attesa "
                + expected.getName() + " ma ottenuta " + e.getClass().getName()
                + " (" + e.getMessage() + ")", memory, time);
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

    private TestResult success(long memory, long time) {
        return new TestResult(TestResultType.SUCCESS, null, memory, time);
    }

    private TestResult failure(String msg, long memory, long time) {
        return new TestResult(TestResultType.FAILURE, msg, memory, time);
    }

    private TestResult error(Exception e, long memory, long time) {
        return new TestResult(TestResultType.ERROR, description + ": [" + e.getClass().getName() + "] " + e.getMessage(), memory, time);
    }

    public static void printResults(java.util.List<TestResult> results){
        int tot = results.size();
        int successes = (int)results.stream().filter(x -> x.type() == TestResultType.SUCCESS).count();
        int failures = (int)results.stream().filter(x -> x.type() == TestResultType.FAILURE).count();
        int errors = (int)results.stream().filter(x -> x.type() == TestResultType.ERROR).count();
        long maxMemory = results.stream().mapToLong(TestResult::memory).max().orElse(0L);
        System.out.println("Test superati [" + successes + "/" + tot + "]");
        System.out.println("Picco memoria [" + results.stream().map(x -> x.memory).reduce(0L, (x, y) -> x > y ? x : y) + "]");
        System.out.println("Tempo esecuzione [" + results.stream().map(x -> x.time).reduce(0L, (x, y) -> x + y) + "]");
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

    private record TestResult(TestResultType type, String message, long memory, long time) { }

    private enum TestResultType {
        SUCCESS,
        FAILURE,
        ERROR
    }
}


