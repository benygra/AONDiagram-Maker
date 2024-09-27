package aon_diagram;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AONNodeClass implements AONNode {

    private final String name;
    private final int duration;

    private boolean isFilledForward;
    private boolean isFilledBackwards;

    private int earlyStart; // this value is trash if !allPredecessorsFilledForward()
    private int earlyFinish;
    private int lateStart;
    private int lateFinish; // this value is trash if !allPredecessorsFilledForward()
    private int slack;

    private Map<String, AONNode> predecessors;
    private Map<String, AONNode> successors;

    public AONNodeClass(String name, int duration) {
        this.duration = duration;
        this.name = name;

        this.isFilledForward = false;
        this.isFilledBackwards = false;

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
    public boolean isFilledForward() {
        return isFilledForward;
    }

    @Override
    public boolean isFilledBackwards() {
        return isFilledBackwards;
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
    public void setAll(int earlyStart, int earlyFinish, int lateStart, int lateFinish, int slack) {
        this.setEarlyStart(earlyStart);
        this.setEarlyFinish(earlyFinish);
        this.setLateStart(lateStart);
        this.setLateFinish(lateFinish);
        this.setSlack(slack);
    }

    @Override
    public void setEarlyStart(int earlyStart) {
        this.earlyStart = earlyStart;
        this.isFilledForward = true;
    }

    @Override
    public void setEarlyFinish(int earlyFinish) {
        this.earlyFinish = earlyFinish;
        this.isFilledForward = true;
    }

    @Override
    public void setLateStart(int lateStart) {
        this.lateStart = lateStart;
        this.isFilledBackwards = true;
    }

    @Override
    public void setLateFinish(int lateFinish) {
        this.lateFinish = lateFinish;
        this.isFilledBackwards = true;
    }

    @Override
    public void setSlack(int slack) {
        this.slack = slack;
    }

    @Override
    public boolean hasPredecessors() {
        return getNumberOfPredecessors() > 0;
    }

    @Override
    public int getNumberOfPredecessors() {
        return predecessors.size();
    }

    @Override
    public boolean hasSuccessors() {
        return getNumberOfSuccessors() > 0;
    }

    @Override
    public int getNumberOfSuccessors() {
        return successors.size();
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
    public Iterator<AONNode> getPredecessors() {
        return predecessors.values().iterator();
    }

    @Override
    public Iterator<AONNode> getSuccessors() {
        return successors.values().iterator();
    }

    @Override
    public boolean allPredecessorsFilledForward() {
        Iterator<AONNode> it = getPredecessors();
        boolean ret = true;
        int max = Integer.MIN_VALUE;

        while (ret && it.hasNext()) {
            AONNode p = it.next();

            ret = ret && p.isFilledForward();

            int ef = p.getEarlyFinish();
            if (ef > max)
                max = ef;
        }

        if (ret)
            setEarlyStart(max);

        return ret;
    }

    @Override
    public boolean allSuccessorsFilledBackwards() {
        Iterator<AONNode> it = getSuccessors();
        boolean ret = true;
        int min = Integer.MAX_VALUE;

        while (ret && it.hasNext()) {
            AONNode s = it.next();

            ret = ret && s.isFilledBackwards();

            int ls = s.getLateStart();
            if (ls < min)
                min = ls;
        }

        if (ret)
            setLateFinish(min);

        return ret;
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
