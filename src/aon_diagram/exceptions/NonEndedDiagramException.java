package aon_diagram.exceptions;

public class NonEndedDiagramException extends RuntimeException {
    private static final String MSG = "The diagram has not ended yet.";

    public NonEndedDiagramException() {
        super(MSG);
    }
}
