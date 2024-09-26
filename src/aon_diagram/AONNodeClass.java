package aon_diagram;

import java.util.HashMap;
import java.util.Map;

public class AONNodeClass implements AONNode {

    private final String name;
    private final int duration;

    private int earlyStart;
    private int earlyFinish;
    private int lateStart;
    private int lateFinish;
    private int slack;

    private Map<String, AONNode> predecessors;
    private Map<String, AONNode> children;

    public AONNodeClass(String name, int duration) {
        this.duration = duration;
        this.name = name;

        this.earlyStart = 0;
        this.earlyFinish = 0;
        this.lateStart = 0;
        this.lateFinish = 0;
        this.slack = 0;

        this.predecessors = new HashMap<>();
        this.children = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public boolean hasPredecessors() {
        return predecessors.size() > 0;
    }

    @Override
    public void addPredecessors(AONNode[] pNodes) {
        for (AONNode p : pNodes)
            predecessors.put(p.getName(), p);
    }
}
