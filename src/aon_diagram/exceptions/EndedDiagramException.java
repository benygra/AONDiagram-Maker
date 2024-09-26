package aon_diagram.exceptions;

public class EndedDiagramException extends Exception {
    private static final String MSG = "The diagram has ended.";

    public EndedDiagramException() {
        super(MSG);
    }
}
