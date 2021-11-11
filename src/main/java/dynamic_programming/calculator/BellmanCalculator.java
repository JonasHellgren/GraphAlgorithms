package dynamic_programming.calculator;

import dynamic_programming.models.Edge;
import dynamic_programming.models.Node;
import dynamic_programming.repo.NodeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BellmanCalculator {
    private static final Logger logger = Logger.getLogger(BellmanCalculator.class.getName());

    NodeRepo nodeRepo;
    Strategy strategy;
    double discountFactor;
    int maxDepth;
    int minDepth;
    List<Node> nodesOnOptPath;

    public BellmanCalculator(NodeRepo nodeRepo,Strategy strategy, double discountFactor) {
        this.nodeRepo = nodeRepo;
        this.strategy=strategy;
        this.discountFactor = discountFactor;
        this.maxDepth = nodeRepo.findDepthMax();
        this.minDepth = nodeRepo.findDepthMin();
    }

    public List<Node> getNodesOnOptPath() {
        return nodesOnOptPath;
    }

    public void setNodeValues() {

        for (int depth = nodeRepo.findDepthMax() - 1; depth >= nodeRepo.findDepthMin(); depth--) {
            List<Node> nodesAtDepth = nodeRepo.findNodesAtDepth(depth);
            for (Node np : nodesAtDepth) {
                List<Double> costs = findCostCandidatesForNode(np);
                np.setValue(strategy.findBestInList(costs));
            }
        }

    }

    private List<Double> findCostCandidatesForNode(Node np) {

        List<Double> costList = new ArrayList<>();

        if (np.getEdges().size() == 0) {
            logger.warning("No edges for node:" + np.getName());
            return costList;
        }

        for (Edge edge : np.getEdges()) {
            if (!nodeRepo.exists(edge.destinationNodeName)) {
                logger.warning("For node " + np.getName() + ", is the destination node not defined: " + edge.destinationNodeName);
            } else {
                double cost = calcLongCost(np, edge);
                costList.add(cost);
            }
        }
        return costList;
    }



    public double calcDiscountFactorPowerDepth(int depth) {
        return Math.pow(discountFactor, (depth - minDepth) + 1);
    }


    public List<Node> findNodesOnOptimalPath(Node startNode) {
        nodesOnOptPath = new ArrayList<>();

        addBestNodeAndFindNewBestNode(startNode);
        return nodesOnOptPath;
    }

    public void addBestNodeAndFindNewBestNode(Node bestNode) {

        nodesOnOptPath.add(bestNode);

        Node newBestNode = null;
        double costBest = strategy.badNumber();
        for (Edge edge : bestNode.getEdges()) {
            if (!nodeRepo.exists(edge.destinationNodeName)) {
                logger.warning("For node " + bestNode.getName() + ", is the destination node not defined: " + edge.destinationNodeName);
            } else {
                double cost = calcLongCost(bestNode, edge);
                if (strategy.isFirstBetterThanSecond(cost,costBest)) {
                    costBest = cost;
                    newBestNode = nodeRepo.get(edge.destinationNodeName);
                }
            }
        }

        if (newBestNode == null && bestNode.getDepthIndex() < this.maxDepth) {
            logger.warning("No destination node for node:" + bestNode.getName());
        }

        if (newBestNode != null) {
            addBestNodeAndFindNewBestNode(newBestNode);
        }
    }

    private double calcLongCost(Node np, Edge edge) {
        double dfpd = calcDiscountFactorPowerDepth(np.getDepthIndex());
        return edge.cost + dfpd * nodeRepo.get(edge.destinationNodeName).getValue();
    }
}


