package aon_diagram;

import aon_diagram.exceptions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AONDiagramClass implements AONDiagram {

    private static final String START = "Start";
    private static final String FINISH = "Finish";

    private Map<String, AONNode> nodes;
    private Map<String, AONNode> toEnd;

    private Set<AONNode> pending;

    private boolean ended;

    public AONDiagramClass() {
        this.nodes = new HashMap<>();
        this.toEnd = new HashMap<>();

        this.pending = new HashSet<>();

        this.ended = false;

        nodes.put(START, new AONNodeClass(START, 0));
        nodes.put(FINISH, new AONNodeClass(FINISH, 0));
    }

    @Override
    public boolean hasEnded() {
        return ended;
    }

    @Override
    public void addNode(String name, int duration) throws AlreadyExistsNodeException, EndedDiagramException {
        if (ended)
            throw new EndedDiagramException();

        if (nodes.containsKey(name))
            throw new AlreadyExistsNodeException();

        AONNode n = nodes.put(name, new AONNodeClass(name, duration));
        pending.add(n);
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

    /**
     * Adds the given node <code>n</code> as successor to each node in <code>pNodes</code> array.
     * Removes all those nodes with succesors from the pending ones.
     * @param n - the given successor node.
     * @param pNodes - the given array of nodes to add the successor node.
     */
    private void addSuccessors(AONNode n, AONNode[] pNodes) {
        for (AONNode p : pNodes) {
            p.addSuccessor(n);
            toEnd.remove(p.getName());
        }
    }

    @Override
    public void addPredecessors(String nodeName, String[] predecessors)
            throws NonexistentNodeException, AlreadyExistsPredecessorsException,
            NonexistentPredecessorsException, EndedDiagramException {

        if (ended)
            throw new EndedDiagramException();

        if (!nodes.containsKey(nodeName))
            throw new NonexistentNodeException();

        AONNode n = nodes.get(nodeName);
        if (n.hasPredecessors())
            throw new AlreadyExistsPredecessorsException();

        AONNode[] pNodes = convertNamesToANNodes(predecessors);
        n.addPredecessors(pNodes);
        addSuccessors(n, pNodes);

        toEnd.put(nodeName, n);
        pending.remove(n);
    }

    @Override
    public void end() throws ExistsPendingNodesException {
        if (pending.size() > 0)
            throw new ExistsPendingNodesException();

        ended = true;
    }
}
