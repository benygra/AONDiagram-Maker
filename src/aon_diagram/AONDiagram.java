package aon_diagram;

import aon_diagram.exceptions.AlreadyExistsNodeException;

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
    void add(String name, int duration) throws AlreadyExistsNodeException;
}
