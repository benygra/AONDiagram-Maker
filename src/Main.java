import aon_diagram.AONDiagram;
import aon_diagram.AONDiagramClass;

public class Main {
    public static void main(String[] args) {
        AONDiagram aon = new AONDiagramClass();

        aon.addNode("cook", 9);
        aon.addNode("pour", 2);
        aon.addNode("add", 1);
        aon.addNode("cream", 2);
        aon.addNode("cheese", 3);
        aon.addNode("melt", 2);
        aon.addNode("cut", 5);

        String[] empty = {};
        aon.addPredecessors("cook", empty);
        aon.addPredecessors("melt", empty);
        aon.addPredecessors("cut", empty);

        String[] pour = {"cook"};
        aon.addPredecessors("pour", pour);

        String[] add = {"melt", "pour"};
        aon.addPredecessors("add", add);

        String[] cream = {"cut", "add"};
        aon.addPredecessors("cream", cream);

        String[] cheese = {"cream"};
        aon.addPredecessors("cheese", cheese);

        aon.end();
    }
}
