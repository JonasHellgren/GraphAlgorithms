package dynamic_programming.models;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Node extends NodeAbstract {


    public Node(String name, int depthIndex) {
        this.name = name;
        this.depthIndex = depthIndex;
        this.value=this.INIT_VALUE;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void setValue(double value) {
        this.value=value;
    }

}
