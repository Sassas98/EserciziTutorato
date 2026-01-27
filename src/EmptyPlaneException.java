public class EmptyPlaneException extends RuntimeException {
    public EmptyPlaneException(String message){
        super(message);
    }
    public EmptyPlaneException(){
        super();
    }
}
