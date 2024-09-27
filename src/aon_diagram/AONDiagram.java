package aon_diagram;

import aon_diagram.exceptions.*;

import java.util.Iterator;

/**
 * Represents an AON Diagram for Project Management (tasks specifically).
 * It can be modified by interactions with the user.
 */
public interface AONDiagram {

    /**
     * Evaluates if the diagram has ended.
     * @return true if the diagram has ended, false otherwise.
     */
    boolean hasEnded();

    /**
     * Creates a new node with the given name and duration, if successful.
     * If not, throws an exception - when already exists a node with the given name in the diagram.
     * @param name - the given node name.
     * @param duration - the given node duration.
     * @throws AlreadyExistsNodeException - if already exists a node with the given name in the diagram.
     * @throws EndedDiagramException - the diagram has already ended.
     */
    void addNode(String name, int duration) throws AlreadyExistsNodeException, EndedDiagramException;

    /**
     * Adds the following predecessors to the node with the given name, if successful.
     * If it is given an empty array, it is intended as a starting task.
     * @param nodeName - the given node name.
     * @param predecessors - the given array of node names to be predecessors of the node with the given name.
     * @throws NonexistentNodeException - if there isn't a node in the diagram with that name.
     * @throws AlreadyExistsPredecessorsException - if the node with the given name already has predecessors set.
     * @throws NonexistentPredecessorsException - if at least one predecessor does not exist.
     * @throws EndedDiagramException - the diagram has already ended.
     */
    void addPredecessors(String nodeName, String[] predecessors)
            throws NonexistentNodeException, AlreadyExistsPredecessorsException,
            NonexistentPredecessorsException, EndedDiagramException;

    /**
     * Returns an iterator over the critical path of the diagram, if it has already ended.
     * @return an iterator over the critical path of the diagram, if it has already ended.
     * @throws NonEndedDiagramException - if the diagram has not ended yet.
     */
    Iterator<AONNode> getCriticalPath() throws NonEndedDiagramException;

    /**
     * Ends the diagram.
     * It means that it cannot receive any more tasks, and the critical path is ready to be calculated.
     * @throws ExistsPendingNodesException - if there are nodes without predecessors, so the diagram cannot be ended.
     */
    void end() throws ExistsPendingNodesException;
}
