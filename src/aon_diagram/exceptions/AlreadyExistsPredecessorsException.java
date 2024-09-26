package aon_diagram.exceptions;

public class AlreadyExistsPredecessorsException extends Exception {
    private static final String MSG = "This node already has predecessors";

    public AlreadyExistsPredecessorsException() {
        super(MSG);
    }
}
