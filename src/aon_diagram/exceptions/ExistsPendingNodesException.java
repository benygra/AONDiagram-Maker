package aon_diagram.exceptions;

public class ExistsPendingNodesException extends Exception {
    private static final String MSG = "There are nodes added without predecessors, so the diagram cannot be ended.";

    public ExistsPendingNodesException() {
        super(MSG);
    }
}
