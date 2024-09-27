package aon_diagram;

import java.util.Iterator;

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
     * Returns true if this node is filled with forward way information, false otherwise.
     * @return true if this node is filled with forward way information, false otherwise.
     */
    boolean isFilledForward();

    /**
     * Returns true if this node is filled with backwards way information, false otherwise.
     * @return true if this node is filled with backwards way information, false otherwise.
     */
    boolean isFilledBackwards();

    /**
     * Returns the early start value.
     * @return the early start value.
     */
    int getEarlyStart();

    /**
     * Returns the early finish value.
     * @return the early finish value.
     */
    int getEarlyFinish();

    /**
     * Returns the late start value.
     * @return the late start value.
     */
    int getLateStart();

    /**
     * Returns the late finish value.
     * @return the late finish value.
     */
    int getLateFinish();

    /**
     * Returns the slack value.
     * @return the slack value.
     */
    int getSlack();

    /**
     * Sets all the parameters given.
     * @param earlyStart - the early start value.
     * @param earlyFinish - the early finish value.
     * @param lateStart - the late start value.
     * @param lateFinish - the late finish value.
     * @param slack - the slack value.
     */
    void setAll(int earlyStart, int earlyFinish, int lateStart, int lateFinish, int slack);

    /**
     * Sets the early start to the given value.
     * @param earlyStart - the given early start value.
     */
    void setEarlyStart(int earlyStart);

    /**
     * Sets the early finish to the given value.
     * @param earlyFinish - the given early finish value.
     */
    void setEarlyFinish(int earlyFinish);

    /**
     * Sets the late start to the given value.
     * @param lateStart - the given late start value.
     */
    void setLateStart(int lateStart);

    /**
     * Sets the late finish to the given value.
     * @param lateFinish - the given late finish value.
     */
    void setLateFinish(int lateFinish);

    /**
     * Sets the slack to the given value.
     * @param slack - the given slack value.
     */
    void setSlack(int slack);

    /**
     * Evaluates if this node has predecessors.
     * @return true if this node has predecessors, false otherwise.
     */
    boolean hasPredecessors();

    /**
     * Returns the number of predecessors.
     * @return the number of predecessors.
     */
    int getNumberOfPredecessors();

    /**
     * Returns the number of successors.
     * @return the number of successors.
     */
    int getNumberOfSuccessors();

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

    /**
     * Returns an iterator over all predecessor AONNode objects of this node.
     * @return an iterator over all predecessor AONNode objects of this node.
     */
    Iterator<AONNode> getPredecessors();

    /**
     * Returns an iterator over all successor AONNode objects of this node.
     * @return an iterator over all successor AONNode objects of this node.
     */
    Iterator<AONNode> getSuccessors();

    /**
     * Evaluates if all predecessors are filled forward.
     * If it's true, updates the earlyStart variable of this node, because all theirs predecessors are filled forward.
     * @return true if all predecessors are filled forward, false otherwise.
     */
    boolean allPredecessorsFilledForward();

    /**
     * Evaluates if all successors are filled backwards.
     * If it's true, updates the lateFinish variable of this node, because all theirs predecessors are filled forward.
     * @return true if all successors are filled backwards, false otherwise.
     */
    boolean allSuccessorsFilledBackwards();
}
