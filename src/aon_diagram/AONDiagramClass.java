package aon_diagram;

import aon_diagram.exceptions.AlreadyExistsNodeException;
import aon_diagram.exceptions.AlreadyExistsPredecessorsException;
import aon_diagram.exceptions.NonexistentNodeException;
import aon_diagram.exceptions.NonexistentPredecessorsException;

import java.util.HashMap;
import java.util.Map;

public class AONDiagramClass implements AONDiagram {

    private Map<String, AONNode> nodes;

    public AONDiagramClass() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void addNode(String name, int duration) {
        if (nodes.containsKey(name))
            throw new AlreadyExistsNodeException();

        nodes.put(name, new AONNodeClass(name, duration));
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

        AONNode[] pNodes = new AONNode[predecessors.length];
        for (int i = 0; i < predecessors.length; i++) {
            if (!nodes.containsKey(predecessors[i]))
                throw new NonexistentPredecessorsException();

            pNodes[i] = nodes.get(predecessors[i]);
        }

        n.addPredecessors(pNodes);
    }
}
