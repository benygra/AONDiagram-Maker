package aon_diagram.exceptions;

public class AlreadyExistsNodeException extends RuntimeException {
    private static final String MSG = "Already exists a node with that name.";

    public AlreadyExistsNodeException() {
        super(MSG);
    }
}
