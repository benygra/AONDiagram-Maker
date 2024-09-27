package aon_diagram;

import aon_diagram.exceptions.*;

import java.util.*;

public class AONDiagramClass implements AONDiagram {

    private static final String START = "Start";
    private static final String FINISH = "Finish";

    private Map<String, AONNode> nodes;
    private Map<String, AONNode> toEnd;

    private Set<AONNode> pending;

    private boolean ended;

    private List<AONNode> criticalPath;

    public AONDiagramClass() {
        this.nodes = new HashMap<>();
        this.toEnd = new HashMap<>();

        this.pending = new HashSet<>();

        this.ended = false;

        this.criticalPath = new ArrayList<>();

        addStartNode();
        nodes.put(FINISH, new AONNodeClass(FINISH, 0));
    }

    private void addStartNode() {
        nodes.put(START, new AONNodeClass(START, 0));
        AONNode start = nodes.get(START);
        start.setAll(0, 0, 0, 0, 0);
    }

    @Override
    public boolean hasEnded() {
        return ended;
    }

    @Override
    public void addNode(String name, int duration) throws AlreadyExistsNodeException, EndedDiagramException {
        if (hasEnded())
            throw new EndedDiagramException();

        if (nodes.containsKey(name))
            throw new AlreadyExistsNodeException();

        nodes.put(name, new AONNodeClass(name, duration));
        AONNode n = nodes.get(name);
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

        if (hasEnded())
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

    // TODO
    @Override
    public Iterator<AONNode> getCriticalPath() throws NonEndedDiagramException {
        if (!hasEnded())
            throw new NonEndedDiagramException();

        if (!criticalPath.isEmpty())
            return criticalPath.iterator();

        //nodes.get(START);
        return null;
    }

    @Override
    public void end() throws ExistsPendingNodesException {
        if (!pending.isEmpty())
            throw new ExistsPendingNodesException();

        this.addPredecessors(FINISH, toEnd.keySet().toArray(new String[toEnd.size()]));

        Stack<AONNode> stack = new Stack<>();
        forwardSetter(stack);
        backwardsSetter(stack);

        ended = true;
    }

    private void pushAllInto(Stack<AONNode> stack, Iterator<AONNode> it) {
        while (it.hasNext())
            stack.push(it.next());
    }

    private void forwardSetter(Stack<AONNode> stack) {
        pushAllInto(stack, nodes.get(START).getSuccessors());

        AONNode n = stack.pop();
        boolean allPredFF = n.allPredecessorsFilledForward();
        while (!(n.equals(nodes.get(FINISH)) && allPredFF)) {
            if (allPredFF) {
                // early start is already set due to allPredecessorsFilledForward()
                n.setEarlyFinish(n.getEarlyStart() + n.getDuration());

                pushAllInto(stack, n.getSuccessors());
            }

            n = stack.pop();
            allPredFF = n.allPredecessorsFilledForward();
        }

        n.setEarlyFinish(n.getEarlyStart()); // because it's the finish node...
        n.setLateStart(n.getEarlyStart());
        n.setSlack(0);
        n.setLateFinish(n.getEarlyFinish());
    }

    private void backwardsSetter(Stack<AONNode> stack) {
        pushAllInto(stack, nodes.get(FINISH).getPredecessors());

        AONNode n = stack.pop();
        boolean allSucFB = n.allSuccessorsFilledBackwards();
        while (!(n.equals(nodes.get(START)) && allSucFB)) {
            if (allSucFB) {
                // late finish is already set due to allSuccessorsFilledBackwards()
                n.setLateStart(n.getLateFinish() - n.getDuration());
                n.setSlack(n.getLateFinish() - n.getEarlyFinish());

                pushAllInto(stack, n.getPredecessors());
            }

            n = stack.pop();
            allSucFB = n.allSuccessorsFilledBackwards();
        }
    }
}
