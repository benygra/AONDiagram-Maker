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
    private Map<String, AONNode> successors;

    public AONNodeClass(String name, int duration) {
        this.duration = duration;
        this.name = name;

        this.earlyStart = 0;
        this.earlyFinish = 0;
        this.lateStart = 0;
        this.lateFinish = 0;
        this.slack = 0;

        this.predecessors = new HashMap<>();
        this.successors = new HashMap<>();
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
    public int getEarlyStart() {
        return earlyStart;
    }

    @Override
    public int getEarlyFinish() {
        return earlyFinish;
    }

    @Override
    public int getLateStart() {
        return lateStart;
    }

    @Override
    public int getLateFinish() {
        return lateFinish;
    }

    @Override
    public int getSlack() {
        return slack;
    }

    @Override
    public void setEarlyStart(int earlyStart) {
        this.earlyStart = earlyStart;
    }

    @Override
    public void setEarlyFinish(int earlyFinish) {
        this.earlyFinish = earlyFinish;
    }

    @Override
    public void setLateStart(int lateStart) {
        this.lateStart = lateStart;
    }

    @Override
    public void setLateFinish(int lateFinish) {
        this.lateFinish = lateFinish;
    }

    @Override
    public void setSlack(int slack) {
        this.slack = slack;
    }

    @Override
    public boolean hasPredecessors() {
        return predecessors.size() > 0;
    }

    @Override
    public boolean hasSuccessors() {
        return successors.size() > 0;
    }

    @Override
    public void addPredecessors(AONNode[] pNodes) {
        for (AONNode p : pNodes)
            predecessors.put(p.getName(), p);
    }

    @Override
    public void addSuccessor(AONNode n) {
        successors.put(n.getName(), n);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AONNodeClass))
            return false;

        AONNodeClass other = (AONNodeClass) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;

        return true;
    }
}
