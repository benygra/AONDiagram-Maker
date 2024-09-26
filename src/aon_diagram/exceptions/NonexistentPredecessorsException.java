package aon_diagram.exceptions;

public class NonexistentPredecessorsException extends RuntimeException {
    private static final String MSG = "At least one predecessor does not exist.";

    public NonexistentPredecessorsException() {
        super(MSG);
    }
}
