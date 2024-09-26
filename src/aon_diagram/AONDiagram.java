package aon_diagram;

import aon_diagram.exceptions.AlreadyExistsNodeException;
import aon_diagram.exceptions.AlreadyExistsPredecessorsException;
import aon_diagram.exceptions.NonexistentNodeException;
import aon_diagram.exceptions.NonexistentPredecessorsException;

/**
 * Represents an AON Diagram for Project Management (tasks specifically).
 * It can be modified by interactions with the user.
 */
public interface AONDiagram {

    /**
     * Creates a new node with the given name and duration, if successful.
     * If not, throws an exception - when already exists a node with the given name in the diagram.
     * @param name - the given node name.
     * @param duration - the given node duration.
     * @throws AlreadyExistsNodeException - if already exists a node with the given name in the diagram.
     */
    void addNode(String name, int duration) throws AlreadyExistsNodeException;

    /**
     * Adds the following predecessors to the node with the given name, if successful.
     * If it is given an empty array, it is intended as a starting task.
     * @param nodeName - the given node name.
     * @param predecessors - the given array of node names to be predecessors of the node with the given name.
     * @throws NonexistentNodeException - if there isn't a node in the diagram with that name.
     * @throws AlreadyExistsPredecessorsException - if the node with the given name already has predecessors set.
     * @throws NonexistentPredecessorsException - if at least one predecessor does not exist.
     */
    void addPredecessors(String nodeName, String[] predecessors)
            throws NonexistentNodeException, AlreadyExistsPredecessorsException,
            NonexistentPredecessorsException;
}
