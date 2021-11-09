package dynamic_programming.repo;

import dynamic_programming.models.Node;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NodeRepo implements  RepositoryInterface<Node>{

    HashMap<String, Node> nodes;

    public NodeRepo() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void add(Node node) {
        nodes.put(node.getName(),node);
    }

    @Override
    public Node get(String id) {
        return nodes.get(id);
    }

    @Override
    public List<Node> getAll() {
        Collection<Node> values = nodes.values();
        return new ArrayList<>(values);
    }

    @Override
    public boolean exists(String id) {
        return nodes.containsKey(id);
    }

    @Override
    public void remove(String id) {
        nodes.remove(id);

    }

    @Override
    public void clearAll() {
        nodes.clear();
    }

    @Override
    public int nofItems() {
        return nodes.size();
    }

    public int findDepthMax() {
        List<Integer> depthList = getDepthList();
        return Collections.max(depthList);
    }

    public int findDepthMin() {
        List<Integer> depthList = getDepthList();
        return Collections.min(depthList);
    }

    public List<Node> findNodesAtDepth(Integer depth) {
        List<Node> allNodes=getAll();
        List<Node> nodesAtDepth=new ArrayList<>();

        for (Node node:allNodes) {
            if (node.getDepthIndex()==depth)
                nodesAtDepth.add(node);
        }

        return nodesAtDepth;
    }

    @NotNull
    private List<Integer> getDepthList() {
        List<Node> nodeList=getAll();
        List<Integer> depthList=new ArrayList<>();
        for (Node node:nodeList) {
            depthList.add(node.getDepthIndex());
        }
        return depthList;
    }

    public String toStringNodeValues() {
        StringBuilder sb=new StringBuilder();

        for (int depth = findDepthMax(); depth >= findDepthMin(); depth--) {
            List<Node> nodesAtDepth = findNodesAtDepth(depth);
            sb.append("depth = "+depth);
            sb.append(System.getProperty("line.separator"));
            for (Node np : nodesAtDepth) {
                sb.append("["+np.getName()+", "+np.getValue()+"], ");
                sb.append(System.getProperty("line.separator"));
            }
        }
        return sb.toString();
    }


}
