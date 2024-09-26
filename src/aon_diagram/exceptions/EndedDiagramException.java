package aon_diagram.exceptions;

public class EndedDiagramException extends RuntimeException {
    private static final String MSG = "The diagram has ended.";

    public EndedDiagramException() {
        super(MSG);
    }
}
