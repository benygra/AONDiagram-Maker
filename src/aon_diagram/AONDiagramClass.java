package aon_diagram;

import aon_diagram.exceptions.AlreadyExistsNodeException;
import aon_diagram.exceptions.AlreadyExistsPredecessorsException;
import aon_diagram.exceptions.NonexistentNodeException;
import aon_diagram.exceptions.NonexistentPredecessorsException;

import java.util.HashMap;
import java.util.Map;

public class AONDiagramClass implements AONDiagram {

    private static final String START = "Start";
    private static final String FINISH = "Finish";

    private Map<String, AONNode> nodes;

    public AONDiagramClass() {
        this.nodes = new HashMap<>();

        nodes.put(START, new AONNodeClass(START, 0));
        nodes.put(FINISH, new AONNodeClass(FINISH, 0));
    }

    @Override
    public void addNode(String name, int duration) throws AlreadyExistsNodeException {
        if (nodes.containsKey(name))
            throw new AlreadyExistsNodeException();

        nodes.put(name, new AONNodeClass(name, duration));
    }

    /**
     * Converts the array of predecessors nodes names to it's corresponding AONNode object, if they all exist...
     * Otherwise, if at least one doesn't exist in the diagram, throws an exception.
     * @param predecessors - the given array of node names to be predecessors of a node.
     * @return the array of AONNode converted.
     */
    private AONNode[] convertNamesToANNodes(String[] predecessors) throws NonexistentPredecessorsException {
        if (predecessors.length == 0) {
            AONNode[] pNodes = new AONNode[1];
            pNodes[0] = nodes.get(START);
            return pNodes;
        }

        AONNode[] pNodes = new AONNode[predecessors.length];

        for (int i = 0; i < predecessors.length; i++) {
            if (!nodes.containsKey(predecessors[i]))
                throw new NonexistentPredecessorsException();

            pNodes[i] = nodes.get(predecessors[i]);
        }

        return pNodes;
    }

    @Override
    public void addPredecessors(String nodeName, String[] predecessors)
            throws NonexistentNodeException, AlreadyExistsPredecessorsException,
            NonexistentPredecessorsException {

        if (!nodes.containsKey(nodeName))
            throw new NonexistentNodeException();

        AONNode n = nodes.get(nodeName);
        if (n.hasPredecessors())
            throw new AlreadyExistsPredecessorsException();

        n.addPredecessors(convertNamesToANNodes(predecessors));
    }
}
