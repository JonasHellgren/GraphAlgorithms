package dynamic_programming.models;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
abstract public class  NodeAbstract {

    @ToString.Exclude
    protected final double INIT_VALUE=0;

    protected  String name="";
    protected  int depthIndex=0;
    protected double value;
    protected List<Edge> edges;

    public abstract void setEdges(List<Edge> edges);
    public abstract void setValue(double value);
}
