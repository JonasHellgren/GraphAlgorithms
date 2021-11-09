package dynamic_programming.models;

import lombok.ToString;


public class Edge {
    public String destinationNodeName;
    public Double cost;

    public Edge(String destinationNodeName, Double cost) {
        this.destinationNodeName = destinationNodeName;
        this.cost = cost;
    }

    @Override
    public String toString() {
       return "("+ destinationNodeName+", "+ cost+")";

    }

}
