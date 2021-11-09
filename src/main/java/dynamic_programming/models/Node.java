package dynamic_programming.models;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Node {

    @ToString.Exclude
    private final double INIT_VALUE=0;

    private final String name;
    private final int depthIndex;
    private double value;
    private List<Edge> edges;


    public Node(String name, int depthIndex) {
        this.name = name;
        this.depthIndex = depthIndex;
        this.value=INIT_VALUE;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void setValue(double value) {
        this.value=value;

    }


}
