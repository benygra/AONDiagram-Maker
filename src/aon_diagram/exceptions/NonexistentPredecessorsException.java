package aon_diagram.exceptions;

public class NonexistentPredecessorsException extends Exception {
    private static final String MSG = "At least one predecessor does not exist.";

    public NonexistentPredecessorsException() {
        super(MSG);
    }
}
