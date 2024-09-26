package aon_diagram.exceptions;

public class NonexistentNodeException extends RuntimeException {
    private static final String MSG = "There isn't any node with that name.";

    public NonexistentNodeException() {
        super(MSG);
    }
}
