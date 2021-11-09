package dynamic_programming.calculator;

import dynamic_programming.models.Edge;
import dynamic_programming.models.Node;
import dynamic_programming.repo.NodeRepo;

import java.util.ArrayList;
import java.util.List;

public class BellmanCalculator {

    NodeRepo nodeRepo;

    public BellmanCalculator(NodeRepo nodeRepo) {
        this.nodeRepo = nodeRepo;
    }

    public void setNodeValues(Strategy strategy) {
        int depthMax=nodeRepo.findDepthMax();
        int depthMin=nodeRepo.findDepthMin();
        List<Double> costList=new ArrayList<>();

        for (int depth = depthMax-1; depth >= depthMin; depth--) {

            List<Node> nodesAtDepth = nodeRepo.findNodesAtDepth(depth);

            for (Node np:nodesAtDepth) {
                costList.clear();
                List<Edge> edges=np.getEdges();
                for (Edge edge:edges) {
                    double cost=edge.cost+nodeRepo.get(edge.destinationNodeName).getValue();
                    costList.add(cost);
                }
                np.setValue(strategy.findBest(costList));
            }
      }

    }
}
