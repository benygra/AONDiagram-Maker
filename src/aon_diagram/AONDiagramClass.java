package aon_diagram;

import aon_diagram.exceptions.AlreadyExistsNodeException;

import java.util.HashMap;
import java.util.Map;

public class AONDiagramClass implements AONDiagram {

    private Map<String, AONNode> nodes;

    public AONDiagramClass() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void add(String name, int duration) {
        if (nodes.containsKey(name))
            throw new AlreadyExistsNodeException();

        nodes.put(name, new AONNodeClass(name, duration));
    }
}
