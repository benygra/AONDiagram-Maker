package aon_diagram;

public interface AONNode {

    /**
     * Returns the name of the node.
     * @return the name of the node.
     */
    String getName();

    /**
     * Returns the duration of the node.
     * @return the duration of the node.
     */
    int getDuration();

    /**
     * Evaluates if this node has predecessors.
     * @return true if this node has predecessors, false otherwise.
     */
    boolean hasPredecessors();

    /**
     * Evaluates if this node has successors.
     * @return true if this node has successors, false otherwise.
     */
    boolean hasSuccessors();

    /**
     * Adds the given predecessors to this node.
     * @param pNodes - the given predecessor nodes array.
     */
    void addPredecessors(AONNode[] pNodes);

    /**
     * Adds the given successor to this node.
     * @param n - the given successor node.
     */
    void addSuccessor(AONNode n);
}
